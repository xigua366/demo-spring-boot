package com.yx.demo.pub;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Publish/Subscribe工作模式，每个消费者实例都能获取到全量的消息
 *
 * 消费者实例2
 */
public class Recv2 {

    private final static String EXCHANGE_NAME = "exchange_fanout";

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
        channel.exchangeDeclare(EXCHANGE_NAME,BuiltinExchangeType.FANOUT);

        //获取队列
        // 动态创建一个独占队列并获取队列名
        String queueName = channel.queueDeclare().getQueue();

        //绑定交换机和队列, fanout交换机不用routingkey
        channel.queueBind(queueName,EXCHANGE_NAME,"");


        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                System.out.println("body="+new String(body,"utf-8"));

                //手工确认消息消费，不是多条确认
                channel.basicAck(envelope.getDeliveryTag(),false);

            }
        };

        //消费,关闭消息消息自动确认,重要
        channel.basicConsume(queueName,false,consumer);


    }
}
