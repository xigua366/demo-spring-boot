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
 * @author yangxi
 * @version 1.0
 */
@Component
public class OrderMqMsgSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 发送订单消息
     * @param orderNo
     * @throws Exception
     */
    public void sendOrderMsg(String orderNo) throws Exception {

        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setMessageId(UUID.randomUUID().toString());
        messageProperties.setContentType("application/json");
        messageProperties.setContentEncoding("utf-8");

        Map<String, Object> msgObj = new HashMap<>();
        msgObj.put("oderNo", orderNo);
        String body = objectMapper.writeValueAsString(msgObj);

        Message message = new Message(body.getBytes(StandardCharsets.UTF_8), messageProperties);

        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, message);
    }
}