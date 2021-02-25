package com.yx.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.yx.demo.enums.BizCodeEnum;
import com.yx.demo.enums.CouponCategoryEnum;
import com.yx.demo.enums.CouponPublishEnum;
import com.yx.demo.enums.CouponStateEnum;
import com.yx.demo.exception.MyException;
import com.yx.demo.mapper.CouponMapper;
import com.yx.demo.mapper.CouponRecordMapper;
import com.yx.demo.model.entity.Coupon;
import com.yx.demo.model.entity.CouponRecord;
import com.yx.demo.service.CouponService;
import com.yx.demo.utils.JsonData;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author yangxi
 * @version 1.0
 */
@Service
public class CouponServiceImpl implements CouponService {

    private static final Logger log = LoggerFactory.getLogger(CouponServiceImpl.class);

    @Autowired
    private CouponMapper couponMapper;

    @Autowired
    private CouponRecordMapper couponRecordMapper;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<Coupon> pageAllCoupon(int page, int size) {
        if(page > 0 && size > 0) {
            PageHelper.startPage(page, size);
        }
        List<Coupon> list = couponMapper.listAllCoupon();
        return list;
    }

    /**
     * 领劵接口
     * 1、获取优惠券是否存在
     * 2、校验优惠券是否可以领取：时间、库存、超过限制
     * 3、扣减库存
     * 4、保存领劵记录
     *
     * 始终要记得，羊毛党思维很厉害，社会工程学 应用的很厉害
     *
     * @param couponId
     * @param userId
     * @param category
     * @return
     */
    @Override
    public JsonData addPromotionCoupon(Integer couponId, Integer userId, CouponCategoryEnum category) {

        Coupon coupon = couponMapper.selectOne(couponId, category.name());

        //优惠券是否可以领取
        this.checkCoupon(coupon, userId);

        //构建领劵记录
        CouponRecord couponRecord = new CouponRecord();
        BeanUtils.copyProperties(coupon, couponRecord);
        couponRecord.setCreateTime(new Date());
        couponRecord.setUseState(CouponStateEnum.NEW.name());
        couponRecord.setUserId(userId);
        couponRecord.setCouponId(couponId);
        couponRecord.setId(null);

        //扣减库存
        int rows = couponMapper.reduceStock(couponId);

        //int flag = 1/0;

        if (rows <= 0) {
            log.warn("发放优惠券失败:{},用户:{}", coupon, userId);
            throw new MyException(BizCodeEnum.COUPON_NO_STOCK);
        }

        String lockKey = "lock:coupon:" + couponId + ":" + userId;
        RLock rLock = redissonClient.getLock(lockKey);

        try {
            //多个线程进入，会阻塞等待释放锁，默认30秒，然后有watch dog自动续期
            rLock.lock();


            //加锁10秒钟过期，没有watch dog功能，无法自动续期
            //rLock.lock(10,TimeUnit.SECONDS);


            log.info("领劵接口加锁成功:{}", Thread.currentThread().getId());


        try {
            TimeUnit.SECONDS.sleep(90);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

            // 先查询用户是否超额领取
            //用户是否超过限制
            int recordNum = couponRecordMapper.selectCount(coupon.getId(), userId);

            if(recordNum >= coupon.getUserLimit()) {
                throw new MyException(BizCodeEnum.COUPON_OUT_OF_LIMIT);
            }

            //库存扣减成功才保存记录
            couponRecordMapper.insert(couponRecord);

        } finally {
            rLock.unlock();
            log.info("解锁成功");
        }

        return JsonData.buildSuccess();
    }

    /**
     * 校验是否可以领取
     * @param coupon 优惠券记录
     * @param userId 用户ID
     */
    private void checkCoupon(Coupon coupon, Integer userId) {

        if(coupon == null) {
            throw new MyException(BizCodeEnum.COUPON_NO_EXITS);
        }

        //库存是否足够
        if(coupon.getStock() <= 0) {
            throw new MyException(BizCodeEnum.COUPON_NO_STOCK);
        }

        //判断是否是否发布状态
        if(!coupon.getPublish().equals(CouponPublishEnum.PUBLISH.name())) {
            throw new MyException(BizCodeEnum.COUPON_GET_FAIL);
        }

        //是否在领取时间范围
        long time = System.currentTimeMillis();
        long start = coupon.getStartTime().getTime();
        long end = coupon.getEndTime().getTime();
        if(time<start || time>end) {
            throw new MyException(BizCodeEnum.COUPON_OUT_OF_TIME);
        }

    }
}