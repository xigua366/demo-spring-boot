package com.yx.rabbitmq.amqp.provider.mq;

import org.springframework.context.annotation.Configuration;

/**
 * @author yangxi
 * @version 1.0
 */
@Configuration
public class RabbitMQConfig {

    // 交换机
    public static final String EXCHANGE_NAME = "order_exchange";

    // 提交订单发送mq，消息的路由键
    public static final String ROUTING_KEY = "order.new";
}