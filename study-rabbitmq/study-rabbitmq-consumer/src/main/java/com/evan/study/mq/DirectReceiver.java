package com.evan.study.mq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 直连交换机
 * 监听的队列名称 study-TestDirectQueue
 */
@Component
public class DirectReceiver {

    /**
     * 手动确认
     * void basicAck(long deliveryTag, boolean multiple)
     * basicAck：表示成功确认，使用此回执方法后，消息会被rabbitmq broker 删除。
     * deliveryTag：表示消息投递序号。
     * multiple：是否批量确认，值为 true 则会一次性 ack所有小于当前消息 deliveryTag 的消息。
     *
     * 一次拒绝多条消息
     * void basicNack(long deliveryTag, boolean multiple, boolean requeue)
     * basicNack ：表示失败确认，一般在消费消息业务异常时用到此方法，可以将消息重新投递入队列。
     * deliveryTag：表示消息投递序号。
     * multiple：是否批量确认。
     * requeue：值为 true 消息将重新入队列。
     *
     * 一次拒绝一条消息
     * void basicReject(long deliveryTag, boolean requeue)
     * basicReject：拒绝消息，与basicNack区别在于不能进行批量操作，其他用法很相似。
     * deliveryTag：表示消息投递序号。
     * requeue：值为 true 消息将重新入队列。
     */


    @RabbitListener(queues = "study-TestDirectQueue")//监听的队列名称 study-TestDirectQueue
    @RabbitHandler
    public void receive(String msg, Channel channel, Message message) throws IOException, InterruptedException {
        System.out.println("消费者1");
        System.out.println("DeliveryTag：" + message.getMessageProperties().getDeliveryTag());
        System.out.println("接收到消息：" + msg);
        Thread.sleep(2000);
        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
    }

    @RabbitListener(queues = "study-TestDirectQueue")//监听的队列名称 study-TestDirectQueue
    @RabbitHandler
    public void receive2(String msg, Channel channel, Message message) throws IOException, InterruptedException {
        System.out.println("消费者2");
        System.out.println("DeliveryTag：" + message.getMessageProperties().getDeliveryTag());
        System.out.println("接收到消息：" + msg);
        Thread.sleep(8000);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
