package com.yx.demo.mapper;

import com.yx.demo.model.entity.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yangxi
 * @version 1.0
 */
@Mapper
public interface CouponMapper {

    /**
     * 查询优惠券
     * @param couponId
     * @param category
     * @return
     */
    Coupon selectOne(@Param("couponId") Integer couponId, @Param("category") String category);

    /**
     * 查询全部可领取的优惠券
     * @return
     */
    List<Coupon> listAllCoupon();

    /**
     * 扣减优惠券库存
     * @param couponId
     * @return
     */
    int reduceStock(@Param("couponId") Integer couponId);
}