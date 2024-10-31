package com.evan.study.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * 事件对象
 *
 * @author Evan
 * @date 2022/1/19
 */
public class MyEvent extends ApplicationEvent {
    public MyEvent(Object source) {
        super(source);
    }
}
