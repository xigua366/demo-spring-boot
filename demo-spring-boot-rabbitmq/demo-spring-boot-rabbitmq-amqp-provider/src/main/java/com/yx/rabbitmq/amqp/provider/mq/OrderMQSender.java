package com.yx.rabbitmq.amqp.provider.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 订单业务mq消息发送
 * @author yangxi
 * @version 1.0
 */
@Component
public class OrderMQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 发送订单消息 (消息内容为json格式)
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

    /**
     * 直接发送字符串消息
     * @param str
     */
    public void sendStrMsg(String str) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, "PO1615795767");
    }

    /**
     * 生产者到交换机可靠性投递测试
     * @param orderNo 订单号
     */
    public void testConfirmCallback(String orderNo) {
        // 使用confirmCallback跟returnCallback时得自己重新new一个RabbitTemplate对象
        // 具体原因参考：https://blog.csdn.net/qq_38082304/article/details/103985960

        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {

            /**
             * @param correlationData 配置
             * @param ack 交换机是否收到消息，true是成功，false是失败
             * @param cause 失败的原因
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {

                System.out.println("ConfirmCallback======>");
                System.out.println("correlationData======>correlationData="+correlationData);
                System.out.println("ack======>ack="+ack);
                System.out.println("cause======>cause="+cause);

                if(ack){
                    System.out.println("发送成功");
                    //更新数据库 消息的状态为成功  TODO
                }else {
                    System.out.println("发送失败，记录到日志或者数据库");
                    //更新数据库 消息的状态为失败  TODO
                }

            }
        });

        //数据库新增一个消息记录，状态是发送  TODO

        // 正常发送消息
//        template.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY,orderNo);
        // 通过设置一个不存在的交换机，来模拟异常（模糊生产者发送消息到交换机时的异常）
        template.convertAndSend(RabbitMQConfig.EXCHANGE_NAME+" springboot", RabbitMQConfig.ROUTING_KEY,orderNo);

    }


    /**
     * 交换机到队列可靠性投递测试
     * @param orderNo 订单号
     */
    public void testReturnCallback(String orderNo) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMandatory(true);
        template.setReturnCallback(new RabbitTemplate.ReturnCallback() {

            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("message:" + message);
                System.out.println("replyCode:" + replyCode);
                System.out.println("replyText:" + replyText);
                System.out.println("exchange:" + exchange);
                System.out.println("routingKey:" + routingKey);
            }

        });

//		template.setReturnCallback(new RabbitTemplate.ReturnCallback() {
//
//			@Override
//			public void returnedMessage(ReturnedMessage returned) {
//				int code = returned.getReplyCode();
//				System.out.println("code="+code);
//				System.out.println("returned="+returned.toString());
//
//			}
//		});

        // 正常发送消息
//         template.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY,orderNo);

        // 通过修改一个不匹配任何一个队列的路由键来模拟异常（交换机转发消息到队列时异常）
        template.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "springboot.order.new",orderNo);
    }
}