package com.yx.rabbitmq.amqp.provider.service.impl;

import com.yx.rabbitmq.amqp.provider.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author yangxi
 * @version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public String createOrder() {

        // 创建订单 TODO

        // 订单号
        String orderNo = "PO" + (System.currentTimeMillis()/1000);

        return orderNo;
    }
}