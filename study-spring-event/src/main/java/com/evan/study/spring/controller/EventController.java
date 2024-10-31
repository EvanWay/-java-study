package com.evan.study.spring.controller;

import com.evan.study.spring.event.MyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Evan
 * @date 2022/1/20
 */
@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping(value = "test")
    public String test() {

        System.out.println("业务逻辑");

        //发布事件
        System.out.println("开始");
        applicationContext.publishEvent(new MyEvent("传入的参数"));
        System.out.println("结束");

        return "请求成功";
    }
}
