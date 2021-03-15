package com.yx.demo.topic;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Topics 工作模式，交换机在转发消息到队列的时候，会对路由键值进行模糊匹配
 *
 * 消费者实例1
 */
public class Recv1 {

    private final static String EXCHANGE_NAME = "exchange_topic";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("120.78.137.97");
        factory.setUsername("admin");
        factory.setPassword("password");
        factory.setVirtualHost("/dev");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();


        //绑定交换机，
        channel.exchangeDeclare(EXCHANGE_NAME,BuiltinExchangeType.TOPIC);

        //获取队列
        String queueName = channel.queueDeclare().getQueue();

        //绑定交换机和队列, 需要指定routingkey
        channel.queueBind(queueName,EXCHANGE_NAME,"order.log.error");


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
