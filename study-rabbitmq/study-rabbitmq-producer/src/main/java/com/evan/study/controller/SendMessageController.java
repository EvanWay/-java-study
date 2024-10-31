package com.evan.study.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class SendMessageController {
    @Autowired
    RabbitTemplate rabbitTemplate;

    //简单模式
    @GetMapping("/sendHelloMessage")
    public String sendHelloMessage() {
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", String.valueOf(UUID.randomUUID()));
        map.put("messageData", "Hello message");
        map.put("createTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        //没有交换机，默认(AMQP default)，发给路由key同名的Queue
        rabbitTemplate.convertAndSend( "study-TestDirectQueue", JSON.toJSONString(map));
        return "ok";
    }

    //工作模式（直连交换机）
    @GetMapping("/sendDirectMessage")
    public String sendDirectMessage() {
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", String.valueOf(UUID.randomUUID()));
        map.put("messageData", "Direct message, hello!");
        map.put("createTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        //键值：study-TestDirectRouting 发送到交换机study-TestDirectExchange
        rabbitTemplate.convertAndSend("study-TestDirectExchange", "study-TestDirectRouting", JSON.toJSONString(map));
        return "ok";
    }

    @GetMapping("/sendTopicMessage1")
    public String sendTopicMessage1() {
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", String.valueOf(UUID.randomUUID()));
        map.put("messageData", "message: MAN ");
        map.put("createTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        //topic
        rabbitTemplate.convertAndSend("study-topicExchange", "topic.man",map);
        return "ok";
    }

    @GetMapping("/sendTopicMessage2")
    public String sendTopicMessage2() {
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", String.valueOf(UUID.randomUUID()));
        map.put("messageData", "message: woman is all ");
        map.put("createTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        //topic
        rabbitTemplate.convertAndSend("study-topicExchange", "topic.woman", map);
        return "ok";
    }

    @GetMapping("/sendFanoutMessage")
    public String sendFanoutMessage() {
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", String.valueOf(UUID.randomUUID()));
        map.put("messageData", "message: testFanoutMessage ");
        map.put("createTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        //扇形
        rabbitTemplate.convertAndSend("study-FanoutExchange", null, map);
        return "ok";
    }

    @GetMapping("/sendDelayedRetryMessage")
    public String sendDelayedRetryMessage() {
        Map<String, Object> map = new HashMap<>();
        map.put("messageId", String.valueOf(UUID.randomUUID()));
        map.put("messageData", "test message, hello!");
        map.put("createTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        // 延迟重试
        rabbitTemplate.convertAndSend("pdd_send_data_exchange", "pdd_send_data_queue", JSON.toJSONString(map).getBytes());
        return "ok";
    }
}
