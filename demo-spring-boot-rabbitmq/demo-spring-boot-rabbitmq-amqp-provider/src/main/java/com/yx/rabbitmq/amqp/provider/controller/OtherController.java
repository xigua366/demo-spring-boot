package com.yx.rabbitmq.amqp.provider.controller;

import com.yx.rabbitmq.amqp.provider.mq.OrderMQSender;
import com.yx.rabbitmq.amqp.provider.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangxi
 * @version 1.0
 */
@RestController
@RequestMapping("/other")
public class OtherController {


    @Autowired
    private OrderMQSender orderMQSender;

    @Autowired
    private OrderService orderService;

    /**
     *
     * POST http://localhost:8090/other/testConfirmCallback
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/testConfirmCallback")
    public Map<String, Object> testConfirmCallback() throws Exception {
        String orderNo = orderService.createOrder();

        orderMQSender.testConfirmCallback(orderNo);

        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("code", 0);
        returnMap.put("msg", "成功");
        returnMap.put("data", orderNo);
        return returnMap;
    }

    /**
     *
     * POST http://localhost:8090/other/testReturnCallback
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/testReturnCallback")
    public Map<String, Object> testReturnCallback() throws Exception {
        String orderNo = orderService.createOrder();

        orderMQSender.testReturnCallback(orderNo);

        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("code", 0);
        returnMap.put("msg", "成功");
        returnMap.put("data", orderNo);
        return returnMap;
    }
}