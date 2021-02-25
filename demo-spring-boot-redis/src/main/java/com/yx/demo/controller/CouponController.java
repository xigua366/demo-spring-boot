package com.yx.demo.controller;

import com.github.pagehelper.PageInfo;
import com.yx.demo.enums.CouponCategoryEnum;
import com.yx.demo.model.entity.Coupon;
import com.yx.demo.model.request.AddCouponRecordRequest;
import com.yx.demo.service.CouponService;
import com.yx.demo.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 优惠券管理
 * @author yangxi
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/pri/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    /**
     * 分页查询全部可领取的优惠券列表
     * @return
     */
    @GetMapping("page_coupon")
    public JsonData pageAllCoupon(@RequestParam(value = "page", defaultValue = "1") int page,
                                  @RequestParam(value = "size", defaultValue = "10") int size) {
        List<Coupon> list = couponService.pageAllCoupon(page, size);
        return JsonData.buildSuccess(new PageInfo<>(list));
    }

    /**
     * 个人领取促销活动优惠券
     * @param addCouponRecordRequest
     * @param request
     * @return
     */
    @PostMapping("add/promotion")
    public JsonData addPromotionCoupon(@RequestBody AddCouponRecordRequest addCouponRecordRequest, HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("user_id");
        Integer couponId = addCouponRecordRequest.getCouponId();
        JsonData jsonData = couponService.addPromotionCoupon(couponId, userId, CouponCategoryEnum.PROMOTION);
        return jsonData;
    }

    /**
     * 分页查询个人的优惠券列表
     * @param page
     * @param size
     * @param request
     * @return
     */
    @GetMapping("page_coupon_record")
    public JsonData pageCouponRecord(@RequestParam(value = "page", defaultValue = "1") int page,
                                     @RequestParam(value = "size", defaultValue = "10") int size,
                                     HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("user_id");
        return null;
    }
}