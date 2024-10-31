package com.evan.study.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 延迟重试配置类
 * @author Evan
 */
@Configuration
public class DelayedRetryConfig {

//    public static final int QUEUE_EXPIRATION = 1000 * 60 * 60 * 1;
    public static final int QUEUE_EXPIRATION = 1000 * 30;

    /**
     * 实际发送消息队列
     */
    @Bean
    public Queue pddSendDataQueue() {
        return new Queue("pdd_send_data_queue",true);
    }

    /**
     * 实际发送消息队列的交换机（也为DLX）
     */
    @Bean
    DirectExchange pddSendDataExchange() {
        return new DirectExchange("pdd_send_data_exchange",true,false);
    }

    /**
     * 绑定
     * 将队列和交换机绑定, 并设置用于匹配键：TestDirectRouting
     */
    @Bean
    Binding bindingSendData() {
        return BindingBuilder.bind(pddSendDataQueue())
                .to(pddSendDataExchange())
                .with("pdd_send_data_queue");
    }


    /**
     * 缓冲ttl队列
     */
    @Bean
    public Queue pddTtlQueue() {
        // x-dead-letter-exchange：出现dead letter之后将dead letter重新发送到指定exchange
        // x-dead-letter-routing-key：出现dead letter之后将dead letter重新按照指定的routing-key发送
        // x-message-ttl：设置队列中的所有消息的生存周期
        // dlx指定的还是pdd_send_data_exchange，路由key为pdd_send_data_queue
        return QueueBuilder.durable("pdd_ttl_queue")
                .withArgument("x-dead-letter-exchange","pdd_send_data_exchange")
                .withArgument("x-dead-letter-routing-key","pdd_send_data_queue")
                .withArgument("x-message-ttl",QUEUE_EXPIRATION)
                .build();
    }

    /**
     * 缓冲ttl队列的交换机
     */
    @Bean
    DirectExchange pddTtlExchange() {
        return new DirectExchange("pdd_ttl_exchange",true,false);
    }

    /**
     * 绑定
     * 缓存队列ttl绑定
     */
    @Bean
    Binding ttlBinding(Queue pddTtlQueue, DirectExchange pddTtlExchange) {
        return BindingBuilder.bind(pddTtlQueue)
                .to(pddTtlExchange)
                .with("pdd_ttl_queue");
    }
}
