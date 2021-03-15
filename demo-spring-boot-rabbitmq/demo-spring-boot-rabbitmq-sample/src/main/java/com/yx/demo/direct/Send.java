package com.yx.demo.direct;

import com.rabbitmq.client.*;
import com.rabbitmq.client.impl.AMQBasicProperties;

import java.nio.charset.StandardCharsets;

/**
 * Routing工作模式
 *
 * 使用direct直接交换机，一定会处理消息的路由键，而且是路由键值全匹配的方式
 *
 */
public class Send {

    private final static String EXCHANGE_NAME = "exchange_direct";

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

            //绑定交换机，直连交换机
            channel.exchangeDeclare(EXCHANGE_NAME,BuiltinExchangeType.DIRECT);

            String error = "我是订单服务的error日志";
            String info = "我是订单服务的info日志";
            String debug = "我是订单服务的debug日志";

            // 连续发送三条消息，分别设置不同的路由键
            channel.basicPublish(EXCHANGE_NAME,"errorRoutingKey",null, error.getBytes(StandardCharsets.UTF_8));
            channel.basicPublish(EXCHANGE_NAME,"infoRoutingKey",null, info.getBytes(StandardCharsets.UTF_8));
            // 可以创建一个BasicProperties对象，然后设置到第三个参数，发送给消费方（里面可以设置messageId等属性，比如可以用UUID作为messageId的值）
            channel.basicPublish(EXCHANGE_NAME,"debugRoutingKey",null, debug.getBytes(StandardCharsets.UTF_8));


            System.out.println("direct消息发送成功");
        }
    }
}