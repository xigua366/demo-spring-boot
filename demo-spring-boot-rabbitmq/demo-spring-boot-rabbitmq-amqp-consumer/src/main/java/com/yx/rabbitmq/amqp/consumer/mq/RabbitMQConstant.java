package com.yx.rabbitmq.amqp.consumer.mq;

import org.springframework.context.annotation.Configuration;

/**
 * mq消费方，相关常量定义
 */
public class RabbitMQConstant {

    /**
     * 订单业务队列
     */
    public static final String ORDER_QUEUE = "order_queue";

    /**
     * 用户业务普通队列
     */
    public static final String USER_QUEUE = "user_queue";

    /**
     * 用户业务死信队列
     */
    public static final String DEAD_USER_QUEUE_NAME = "dead_user_queue";
}
