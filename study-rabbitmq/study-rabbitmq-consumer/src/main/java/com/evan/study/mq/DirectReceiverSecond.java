package com.evan.study.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 监听的队列名称 study-TestDirectQueue（二）
 * 自动ack
 */
//@Component
//@RabbitListener(queues = "study-TestDirectQueue")
//public class DirectReceiverSecond {
//
//    @RabbitHandler
//    public void process(String message) {
//        System.out.println("第二个消费者-DirectReceiverSecond收到消息：");
//        System.out.println(message);
//    }
//}
