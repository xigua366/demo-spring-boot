package com.yx.demo.mapper;

import com.yx.demo.model.entity.VideoOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yangxi
 * @version 1.0
 */
@Mapper
public interface VideoOrderMapper {

    /**
     * 查询用户是否购买过此商品
     * @param userId
     * @param videoId
     * @param state
     * @return
     */
    VideoOrder findByUserIdAndVideoIdAndState(@Param("user_id") Integer userId, @Param("video_id") Integer videoId, @Param("state") Integer state);


    /**
     * 下单
     * @return
     */
    int saveOrder(VideoOrder videoOrder);


    /**
     * 订单列表
     * @param userId
     * @return
     */
    List<VideoOrder> listOrderByUserId(@Param("user_id") Integer userId);

}