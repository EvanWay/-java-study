package com.evan.study.spring.event;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 事件的监听器——接口
 *
 * @author Evan
 * @date 2022/1/19
 */
@Component
public class MyEventInterfaceListener implements ApplicationListener<MyEvent> {

    @Async
    @Override
    public void onApplicationEvent(MyEvent myEvent) {

        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }

        String source = (String) myEvent.getSource();
        System.out.println("Listener——接口方式，收到的参数：" + source);

        System.out.println("Listener——接口方式");
    }
}
