package com.yx.demo.work.rr;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * work工作模式，轮询负载均衡方式分发队列中的消息给各个消费者实例，无论这个消费者实例消费能力如何，所有消费者实例被分到的消息数都是一样的
 *
 * 消费者实例2
 */
public class Recv2 {

    private final static String QUEUE_NAME = "work_mq_rr";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("120.78.137.97");
        factory.setUsername("admin");
        factory.setPassword("password");
        factory.setVirtualHost("/dev");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 发送方申明过一次队列之后，消费方这里其实是不用再次申明的了
        // channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");


        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                //模拟消费者消费慢
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //一般是固定的，可以作为会话的名称
                //System.out.println("consumerTag="+consumerTag);
                //可以获取交换机、路由健等信息
                //System.out.println("envelope="+envelope);

                //System.out.println("properties="+properties);

                System.out.println("body="+new String(body,"utf-8"));

                //手工确认消息消费，不是多条确认
                channel.basicAck(envelope.getDeliveryTag(),false);

            }
        };

        //消费,关闭消息消息自动确认,重要
        channel.basicConsume(QUEUE_NAME,false,consumer);

    }
}
