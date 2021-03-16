package com.yx.rabbitmq.amqp.provider.mq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 用来测试定义死信交换机、死信队列等
 * @author yangxi
 * @version 1.0
 */
@Configuration
public class TestRabbitMQDeadQueueConfig {

    // 用户业务死信交换机
    public static final String DEAD_USER_EXCHANGE_NAME = "dead_user_exchange";

    // 用户业务死信队列
    public static final String DEAD_USER_QUEUE_NAME = "dead_user_queue";

    // 死信交换机与死信队列的绑定关系
    public static final String DEAD_USER_BINDING_REF = "dead.user.#";

    // 正常的用户业务交换机
    public static final String USER_EXCHANGE_NAME = "user_exchange";

    // 正常的用户业务队列 (存放正常的业务消息)
    public static final String USER_QUEUE_NAME = "user_queue";

    // 正常的用户业务延迟队列 (存放需要延迟的业务消息)
    public static final String DELAY_USER_QUEUE_NAME = "delay_user_queue";

    // 用户业务交换机与正常业务队列的绑定关系
    public static final String USER_BINDING_REF = "user.#";

    // 用户业务交换机与延迟业务队列的绑定关系
    public static final String DELAY_USER_BINDING_REF = "user.delay.#";

    // 生产者发送消息到用户业务交换机时的路由键
    // 生产者发送消息时，路由键配置成user.delay.new，那么最终效果是交换机(user_exchange)会把消息既路由到正常的用户业务消息队列（user_queue），也会路由到需要延迟的用户业务消息队列（delay_user_queue）
    public static final String TO_USER_EXCHANGE_ROUTING_KEY = "user.delay.new";

    // 正常的用户业务延迟队列中消息过期后转发到私信交换机的路由键
    public static final String TO_DEAD_USER_EXCHANGE_ROUTING_KEY = "dead.user.new";


    // ================= 死信交换机与死信队列的配置 start =======================

    /**
     * 用户业务死信交换机 topic类型
     * @return
     */
    @Bean
    public Exchange deadUserExchange() {
        // 定义一个topic类型的交换机
        return ExchangeBuilder.topicExchange(DEAD_USER_EXCHANGE_NAME).durable(true).build();
    }


    /**
     * 用户业务死信队列
     * @return
     */
    @Bean
    public Queue deadUserQueue() {
        return QueueBuilder.durable(DEAD_USER_QUEUE_NAME).build();
    }


    /**
     * 用户业务死信交换机和死信队列绑定关系
     */
    @Bean
    public Binding deadUserBinding() {
        return BindingBuilder.bind(deadUserQueue()).to(deadUserExchange()).with(DEAD_USER_BINDING_REF).noargs();
    }

    // ================= 死信交换机与死信队列的配置 end =======================


    // ================= 用户业务交换机、正常队列、延迟队列等配置 start =======================

    /**
     * 正常的用户业务交换机 topic类型
     * @return
     */
    @Bean
    public Exchange userExchange() {
        // 定义一个topic类型的交换机
        return ExchangeBuilder.topicExchange(USER_EXCHANGE_NAME).durable(true).build();
    }


    /**
     * 正常的用户业务队列
     * @return
     */
    @Bean
    public Queue userQueue() {
        return QueueBuilder.durable(USER_QUEUE_NAME).build();
    }

    /**
     * 正常的用户业务延迟消息队列
     * @return
     */
    @Bean
    public Queue delayUserQueue() {

        // 延迟队列与普通队列最大的区别就是需要配置x-message-ttl消息过期时间、x-dead-letter-exchange死信交换机、x-dead-letter-routing-key过期消息转发到死信交换机的路由键等属性
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-message-ttl", 30*1000); // 30秒过期
        arguments.put("x-dead-letter-exchange", DEAD_USER_EXCHANGE_NAME);
        arguments.put("x-dead-letter-routing-key", TO_DEAD_USER_EXCHANGE_ROUTING_KEY);

        return QueueBuilder.durable(DELAY_USER_QUEUE_NAME).withArguments(arguments).build();
    }


    /**
     * 正常的用户业务交换机和队列绑定关系（包括绑定延迟队列）
     */
    @Bean
    public Binding userBinding() {
        return BindingBuilder.bind(userQueue()).to(userExchange()).with(USER_BINDING_REF).noargs();
    }

    /**
     * 正常的用户业务交换机和延迟队列绑定关系
     */
    @Bean
    public Binding delayUserBinding() {
        return BindingBuilder.bind(delayUserQueue()).to(userExchange()).with(DELAY_USER_BINDING_REF).noargs();
    }

    // ================= 用户业务交换机、正常队列、延迟队列等配置 end =======================
}