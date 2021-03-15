package com.yx.rabbitmq.amqp.consumer.mq;

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
 * 1、这些rabbitmq相关的内容定义无论在消息发送方做还是消费方做都是可以的，而且只要一个地方做了就行了。
 * 2、也可以不再程序中定义，完全去到rabbitmq的web控制台，手工创建交换机，创建队列，做好绑定关系也是可以的。
 *
 */
@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "order_exchange";

    public static final String QUEUE = "order_queue";


    /**
     * topic 交换机
     * @return
     */
    @Bean
    public Exchange orderExchange(){
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
    public Binding orderBinding(Queue queue, Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("order.#").noargs();
    }

}
