package com.yx.rabbitmq.amqp.provider.controller;

import com.yx.rabbitmq.amqp.provider.mq.OrderMQSender;
import com.yx.rabbitmq.amqp.provider.service.OrderService;
import com.yx.rabbitmq.amqp.provider.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public JsonData testConfirmCallback() throws Exception {
        String orderNo = orderService.createOrder();

        orderMQSender.testConfirmCallback(orderNo);

        return JsonData.buildSuccess(orderNo);
    }

    /**
     *
     * POST http://localhost:8090/other/testReturnCallback
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/testReturnCallback")
    public JsonData testReturnCallback() throws Exception {
        String orderNo = orderService.createOrder();

        orderMQSender.testReturnCallback(orderNo);

        return JsonData.buildSuccess(orderNo);
    }
}