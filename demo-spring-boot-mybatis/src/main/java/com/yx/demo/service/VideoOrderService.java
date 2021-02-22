package com.yx.demo.service;


import com.yx.demo.model.entity.VideoOrder;

import java.util.List;

/**
 * @author yangxi
 * @version 1.0
 */
public interface VideoOrderService {

    /**
     * 新增视频课程订单
     * @param userId
     * @param videoId
     * @return
     */
    int saveOrder(Integer userId, Integer videoId);

    /**
     * 查询用户视频课程订单
     * @param userId
     * @return
     */
    List<VideoOrder> listOrderByUserId(Integer userId);

    /**
     * 分页查询全部订单
     * @param page 第几页
     * @param size 每天显示记录数
     * @return
     */
    List<VideoOrder> pageOrderAll(int page, int size);
}