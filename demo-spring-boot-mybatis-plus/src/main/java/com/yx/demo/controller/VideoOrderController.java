package com.yx.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yx.demo.model.entity.VideoOrder;
import com.yx.demo.model.request.SaveOrderRequest;
import com.yx.demo.service.VideoOrderService;
import com.yx.demo.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author yangxi
 * @version 1.0
 */
@RestController
@RequestMapping("api/v1/pri/video_order")
public class VideoOrderController {

    @Autowired
    private VideoOrderService videoOrderService;

    /**
     * 下单
     * @param saveOrderRequest
     * @return
     */
    @PostMapping("save")
    public JsonData saveOrder(@RequestBody SaveOrderRequest saveOrderRequest, HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("user_id");
        Integer videoId = saveOrderRequest.getVideoId();
        videoOrderService.saveOrder(userId, videoId);
        return JsonData.buildSuccess();
    }

    /**
     * 查询用户订单列表
     * @param request
     * @return
     */
    @GetMapping("list")
    public JsonData listOrderByUserId(HttpServletRequest request) {
        Integer userId = (Integer) request.getAttribute("user_id");
        List<VideoOrder> videoOrderList = videoOrderService.listOrderByUserId(userId);
        return JsonData.buildSuccess(videoOrderList);
    }

    /**
     * 分页查询全部订单列表
     * @param page 第几页
     * @param size 每页记录数
     * @return
     */
    @GetMapping("page_all")
    public JsonData pageOrderAll(int page, int size) {
        IPage<VideoOrder> videoOrderPage = videoOrderService.pageOrderAll(page, size);
        // return JsonData.buildSuccess(new PageInfo<>(videoOrderList));
        return JsonData.buildSuccess(videoOrderPage);
    }

    // 根据条件筛选用户订单并分页

    // 补充一下多表关联查询分页的玩法
}