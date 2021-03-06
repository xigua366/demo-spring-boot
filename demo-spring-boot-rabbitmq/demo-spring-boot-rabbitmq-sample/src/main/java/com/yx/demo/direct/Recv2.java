package com.yx.demo.direct;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Routing 工作模式，交换机在转发消息到队列的时候，会对路由键值进行完整匹配
 *
 * 消费者实例2
 */
public class Recv2 {

    private final static String EXCHANGE_NAME = "exchange_direct";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("120.78.137.97");
        factory.setUsername("admin");
        factory.setPassword("password");
        factory.setVirtualHost("/dev");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();


        //绑定交换机，fanout扇形，即广播
        channel.exchangeDeclare(EXCHANGE_NAME,BuiltinExchangeType.DIRECT);

        // 动态创建一个独占队列并获取队列名
        // String queueName = channel.queueDeclare().getQueue();

        // 也可自己自定义申明一个非独占的队列
        String queueName = "directtestqueue";
        channel.queueDeclare(queueName, false, false, false, null);

        //绑定交换机和队列, direct交换机需要指定routingkey
        channel.queueBind(queueName,EXCHANGE_NAME,"errorRoutingKey");


        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                System.out.println("consumerTag:" + consumerTag + ", body="+new String(body,"utf-8"));
                System.out.println("envelope:" + envelope);

                StringBuilder sb = new StringBuilder();
                properties.appendPropertyDebugStringTo(sb);
                System.out.println("properties:" + sb);
                System.out.println("messageId:" + properties.getMessageId());

                //手工确认消息消费，不是多条确认
                channel.basicAck(envelope.getDeliveryTag(),false);

            }
        };

        //消费,关闭消息消息自动确认,重要
        channel.basicConsume(queueName,false, consumer);


    }
}
