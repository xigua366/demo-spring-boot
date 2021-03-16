package com.yx.rabbitmq.amqp.consumer.mq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 订单消息消费方监听器
 */
// @RabbitListener直接放在方法上即可，不需要放在类上，参考：https://blog.csdn.net/koubi1234/article/details/89676010
// @RabbitListener(queues = RabbitMQConfig.QUEUE)
@Component
@Slf4j
public class OrderMQListener {

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    @RabbitHandler
    public void messageHandler(String body, Message message, Channel channel) throws IOException {

        long msgTag = message.getMessageProperties().getDeliveryTag();
        System.out.println("msgTag="+msgTag);
        System.out.println("message="+message.toString());
        System.out.println("body="+body);

        try {
            // try里面消费消息，执行本地业务方法，一般是要用一个本地事务保证原子性
            //复杂业务逻辑，且要增加事务 TODO

            // 业务处理成功，告诉broker，消息已经被确认
            channel.basicAck(msgTag,false);

        } catch(Exception e) {

            log.error("消费失败, msgTag={}", msgTag, e);

            // 告诉broker，消息拒绝确认（可以设置批量ack）
            //channel.basicNack(msgTag,false,true);

            // 告诉broker，消息拒绝确认（一次只能拒绝一个消息，不可以设置批量ack）
            //channel.basicReject(msgTag,true);
        }

    }

}