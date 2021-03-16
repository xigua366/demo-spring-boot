package com.yx.rabbitmq.amqp.provider.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 用户业务mq消息发送
 * @author yangxi
 * @version 1.0
 */
@Component
public class UserMQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 发送用户消息 (消息内容为json格式)
     * @param phone 注册人手机号码
     * @throws Exception
     */
    public void sendUserMsg(String phone) throws Exception {

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setMessageId(UUID.randomUUID().toString());
        messageProperties.setContentType("application/json");
        messageProperties.setContentEncoding("utf-8");

        Map<String, Object> msgObj = new HashMap<>();
        msgObj.put("phone", phone);
        String body = objectMapper.writeValueAsString(msgObj);

        Message message = new Message(body.getBytes(StandardCharsets.UTF_8), messageProperties);

        rabbitTemplate.convertAndSend(TestRabbitMQDeadQueueConfig.USER_EXCHANGE_NAME, TestRabbitMQDeadQueueConfig.TO_USER_EXCHANGE_ROUTING_KEY, message);
    }
}