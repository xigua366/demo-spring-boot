package com.yx.rabbitmq.amqp.provider.mq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq相关配置
 *
 * 主要是如下的一些内容：
 * 1、交换机定义。
 * 2、队列定义
 * 3、队列与交换机的绑定关系定义。
 *
 * 特别说明：
 * 1、这些rabbitmq相关的内容定义无论在消息发送方做还是消费方做都是可以的，而且只要一个地方做了就行了，一般是建议统一放在消息的生产方来定义。
 * 2、也可以不再程序中定义，完全去到rabbitmq的web控制台，手工创建交换机，创建队列，做好绑定关系也是可以的。
 *
 */
@Configuration
public class RabbitMQConfig {

    // 订单业务交换机
    public static final String EXCHANGE_NAME = "order_exchange";

    // 订单业务队列
    public static final String QUEUE = "order_queue";

    // 生产者提交订单发送mq，消息的路由键
    public static final String ROUTING_KEY = "order.new";



    /**
     * topic 交换机
     * @return
     */
    @Bean
    public Exchange orderExchange() {

        // 定义一个fanout类型的交换机
//        ExchangeBuilder.fanoutExchange(EXCHANGE_NAME).durable(true).build();

        // 定义一个direct类型的交换机
//        ExchangeBuilder.directExchange(EXCHANGE_NAME).durable(true).build();

        // 定义一个topic类型的交换机
        return ExchangeBuilder.topicExchange(EXCHANGE_NAME).durable(true).build();
    }


    /**
     * 队列
     * @return
     */
    @Bean
    public Queue orderQueue() {
        return QueueBuilder.durable(QUEUE).build();
    }


    /**
     * 交换机和队列绑定关系
     */
    @Bean
    public Binding orderBinding() {
        return BindingBuilder.bind(orderQueue()).to(orderExchange()).with("order.#").noargs();
    }
}