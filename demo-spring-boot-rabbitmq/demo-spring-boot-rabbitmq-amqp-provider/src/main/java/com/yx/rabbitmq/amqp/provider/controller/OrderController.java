package com.yx.rabbitmq.amqp.provider.controller;

import com.yx.rabbitmq.amqp.provider.mq.OrderMqMsgSender;
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
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderMqMsgSender orderMqMsgSender;

    @Autowired
    private OrderService orderService;

    /**
     *
     * POST http://localhost:8090/order/createOrder
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/createOrder")
    public Map<String, Object> createOrder() throws Exception {

        String orderNo = orderService.createOrder();

        orderMqMsgSender.sendOrderMsg(orderNo);

        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("code", 0);
        returnMap.put("msg", "成功");
        returnMap.put("data", orderNo);
        return returnMap;
    }

}