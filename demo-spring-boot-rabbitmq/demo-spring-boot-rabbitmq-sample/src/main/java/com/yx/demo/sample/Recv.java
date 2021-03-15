package com.yx.demo.sample;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * 这个是简单队列 消息消费方
 */
public class Recv {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("120.78.137.97");
        factory.setUsername("admin");
        factory.setPassword("password");
        factory.setVirtualHost("/dev");
        factory.setPort(5672);

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 下面这个queueDeclare()方法声明一个队列的方式，其实就是让程序去创建一个队列，这个创建队列的动作无论在消息发送方还是消费方都是可以进行的。
        // 有一个点要注意的就是对于同一个队列名字，如果mq中间件中已经存在了的话，后续执行代码再次声明这个队列的时候，各种属性一定是要保持一致的，不一致就会报错。
        // 如果不想让程序代码来创建一个队列，也可以自己去web控制台手工创建好一个队列，设置好队列的属性，以及跟交换机做好绑定
        // 发送方申明过一次队列之后，消费方这里其实是不用再次申明的了
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 下面这行代码表示的是，不主动定义一个队列，而是让程序动态的创建一个队列，创建出来的队列名是随机的，而且这种方式创建出来的队列都是独占的，connection关闭后就会自动删除
//        String queueName = channel.queueDeclare().getQueue();

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");


        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //一般是固定的，可以作为会话的名称
                System.out.println("consumerTag="+consumerTag);
                //可以获取交换机、路由健等信息
                System.out.println("envelope="+envelope);

                System.out.println("properties="+properties);

                System.out.println("body="+new String(body,"utf-8"));

            }
        };

        //消费
        channel.basicConsume(QUEUE_NAME,true,consumer);


//        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//            String message = new String(delivery.getBody(), "UTF-8");
//            System.out.println(" [x] Received '" + message + "'");
//        };
//
//        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
