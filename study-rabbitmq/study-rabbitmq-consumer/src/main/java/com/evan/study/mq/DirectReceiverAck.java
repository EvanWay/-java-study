package com.evan.study.mq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * 另一种方式接收——implements ChannelAwareMessageListener
 * ack机制
 * 1、设置手动签收：
 * 2、让监听器实现ChannelAwareMessageListenner接口（这是MessageListener子接口）
 * 3、如果消息成功处理，则调用basicAck()签收
 * 4、如果消息处理失败，则调用basicNack()拒绝签收，broker重新发送给consumer
 * @author Evan
 */
@Component
public class DirectReceiverAck implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            //处理业务逻辑
            System.out.println("消费者3");
            System.out.println("DeliveryTag：" +deliveryTag);
            System.out.println("接收到消息：" + new String(message.getBody()));
            Thread.sleep(2000);
            //异常
            int i = 10/0;
            //手动签收
            channel.basicAck(deliveryTag, true);
        } catch (Exception e) {
            //拒绝签收，第三个参数重回队列
            channel.basicNack(deliveryTag, false, true);
        }
    }
}
