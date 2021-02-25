package com.yx.demo.mapper;

import com.yx.demo.model.entity.CouponRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author yangxi
 * @version 1.0
 */
@Mapper
public interface CouponRecordMapper {

    int insert(CouponRecord couponRecord);

    int selectCount(@Param("couponId") Integer couponId, @Param("userId") Integer userId);
}