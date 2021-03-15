package com.yx.demo.pub;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

/**
 * Publish/Subscribe工作模式
 *
 * 使用fanout扇形交换机，不处理消息的路由键，会把这个交换机接收到全量消息转发给所有绑定了当前交换机的队列，消费者无论监听哪一个队列都可以消费全量的消息
 *
 * 思考：为什么说使用fanout扇形交换机的时候，队列应该设置成exclusive=true 独占的呢？难道不是独占的不行么？
 *
 * 回答：经过实际动手测试，发现在使用fanout扇形交换机的时候，也是可以自己自定义声明队列，然后给队列设置为非独占的。
 * 也就是说使用fanout扇形交换机的时候，队列不是必须要设置成exclusive=true的。 具体示例代码参考： com.yx.demo.pub.Recv3.java
 */
public class Send {

    private final static String EXCHANGE_NAME = "exchange_fanout";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("120.78.137.97");
        factory.setUsername("admin");
        factory.setPassword("password");
        factory.setVirtualHost("/dev");
        factory.setPort(5672);

        //JDK7语法，自动关闭，创建连接
        try (Connection connection = factory.newConnection();
             //创建信道
             Channel channel = connection.createChannel()) {

            //绑定交换机，fanout扇形，即广播
            channel.exchangeDeclare(EXCHANGE_NAME,BuiltinExchangeType.FANOUT);

            String msg = "欢迎来一起学习rabbitmq Publish/Subscribe工作模式";
            channel.basicPublish(EXCHANGE_NAME,"",null, msg.getBytes(StandardCharsets.UTF_8));

            System.out.println("广播消息发送成功");
        }
    }
}