package com.yx.demo.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author yangxi
 * @version 1.0
 */
public class AddCouponRecordRequest {

    /**
     * 领取优惠券ID
     */
    @JsonProperty("coupon_id")
    private Integer couponId;

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }
}