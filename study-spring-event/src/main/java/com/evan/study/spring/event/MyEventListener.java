package com.evan.study.spring.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 事件的监听器——注解
 *
 * @author Evan
 * @date 2022/1/20
 */
@Component
public class MyEventListener {

    @EventListener(MyEvent.class)
    public void listener1(MyEvent myEvent) {
        System.out.println(myEvent);
        System.out.println("listener1");
    }

    /**
     * 可以根据方法参数匹配到
     *
     * @param myEvent 事件
     */
    @EventListener()
    public void listener2(MyEvent myEvent) {
        System.out.println(myEvent);
        System.out.println("listener2");
    }
}
