package com.evan.study.mq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Evan
 */
@Component
@RabbitListener(queues = "pdd_send_data_queue")
public class DelayedRetryReceiver {

    /**
     * 延迟重试
     * 这里用isDefault = true，就不用具体类型接，就在Message中message.getBody()接byte[]
     * @param channel
     * @param message
     * @throws IOException
     */
    @RabbitHandler(isDefault = true)
    public void process(Channel channel, Message message) throws IOException {
        try {
            System.out.println(String.format("DeliveryTag：%s，%s"
                    , message.getMessageProperties().getDeliveryTag()
                    , new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
            System.out.println("消费者-DelayedRetryReceiver收到消息  : " + new String(message.getBody()));
            if (false) {
                //如果成功
            } else {
                //如果失败
                throw new Exception("失败");
            }
        } catch (Exception e) {
            System.out.println("手动发送进入缓冲队列延迟重试");
            //手动发送，进入缓冲队列延迟重试
            channel.basicPublish(
                    "pdd_ttl_exchange",
                    "pdd_ttl_queue",
                    null,
                    message.getBody());
        } finally {
            // 成功失败，都要ack，失败后是手动转发进缓冲队列，原先这条还未ack
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }
}
