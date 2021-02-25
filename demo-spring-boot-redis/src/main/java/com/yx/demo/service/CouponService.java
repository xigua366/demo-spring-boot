package com.yx.demo.service;

import com.yx.demo.enums.CouponCategoryEnum;
import com.yx.demo.model.entity.Coupon;
import com.yx.demo.utils.JsonData;

import java.util.List;

/**
 * @author yangxi
 * @version 1.0
 */
public interface CouponService {

    /**
     * 分页查询可领取优惠券
     * @param page
     * @param size
     */
    List<Coupon> pageAllCoupon(int page, int size);

    /**
     * 领取优惠券
     * @param couponId 优惠券ID
     * @param userId 用户ID
     */
    JsonData addPromotionCoupon(Integer couponId, Integer userId, CouponCategoryEnum category);
}