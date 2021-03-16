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
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderMQSender orderMQSender;

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
    public JsonData createOrder() throws Exception {

        String orderNo = orderService.createOrder();

        orderMQSender.sendOrderMsg(orderNo);

        return JsonData.buildSuccess(orderNo);
    }

}