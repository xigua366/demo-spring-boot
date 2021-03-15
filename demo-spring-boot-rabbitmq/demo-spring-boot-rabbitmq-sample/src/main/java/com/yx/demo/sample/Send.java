package com.yx.demo.sample;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

/**
 * 简单队列工作模式 消息发送方
 *
 * 五种常见的工作模式：
 * 所谓简单队列工作模式就是只有一个发送方实例，一个消费方实例，然后固定的一个指定的队列
 *
 * 在rabbitmq中，对于一个固定的队列，里面的消息被任何一个消费方实例消费之后都会移除（标志为已消费），不会在投递给其它的消费者实例
 * 如果有多个消费者实例同时消费一个固定的队列中的消息，那么这个队列中的消息会负载均衡（默认轮询）分发给这些消费者实例，这个时候也就是所谓的work工作模式了（多个工人一起消费同一个队列里面的消息）
 *
 * 如果要实现多个消费者实例都能消费一遍完整的所有的消息，那么就不一定不能固定的就只申明一个队列，而是有多少个消费者实例，就要申请多少个队列，然后让交换机把消息往这些队列中都转发一份，这个也就是所谓的pub/sub工作模式
 * 标准的pub/sub工作模式采用 fanout(扇型)交换机来实现，这种交换机是忽略消息的路由键的。
 *
 * 如果想让交换机在分发消息到队列中去的时候启用路由键，那么就需要使用direct(直接)交换机来实现了，这个也就是所谓的Routing工作模式。
 *
 * 由于direct交换机是按路由键的值进行全匹配的，有时候想进行模糊匹配，就需要更换使用topic(主题)交换机来实现，这个也就是所谓的Topics工作模式
 *
 *
 * 其它一些要点记录：
 * simple简单队列工作模式跟work工作模式，都不需要指定交换机，也不用程序去进行显示的交换机与队列的绑定工作(没有channel.queueBind(queueName,EXCHANGE_NAME,"");这样的代码出现)
 *
 * 在生产环境中，我们的应用服务实例都是集群部署的，比如说用户服务，我们会部署三台机器，三个实例组成集群。假设用户服务需要消费某个mq消息的话，
 * 对于具体的某一个消息，三个实例中的任何一个实例进行了消费就可以了，不能三个实例都能接收到同一个消息。
 * 这种情况必须要事先定义好队列名，让这些多个实例都监听这同一个队列，然后交换机把消息都往这一个队列中转发。
 *
 *
 */
public class Send {

    private final static String QUEUE_NAME = "hello";

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
            /**
             * 队列名称
             * 持久化配置：mq重启后还在（这个就是化是指这个队列是否是持久化的，而不是说这个队列中的消息是否持久化的，生产环境中，队列都是要配置成持久化的）
             * 是否独占：只能有一个消费者监听队列；当connection关闭是否删除队列。 一般是false，发布订阅是独占（也就是fanout扇形交换机时）
             * (特别注意，即使设置durable为true，但是设置的exclusive为false，当connection关闭时，这个队列还是会被自动删除的)
             * 自动删除: 当没有消费者的时候，自动删除掉，一般是false
             * 其他参数
             *
             * 队列不存在则会自动创建，如果存在则不会覆盖，所以此时的时候需要注意属性不要变化，属性配置变化了，声明会失败，需要去rabbitmq控制台手动删除之前的队列，然后再执行这个代码
             */
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "这个是简单队列测试消息";

            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));

            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}