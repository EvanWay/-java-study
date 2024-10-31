package com.evan.study.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * BeanPostProcessor Bean的后置处理器
 * @author Evan
 * @date 2022/4/19
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    public MyBeanPostProcessor(){
        System.out.println("【BeanPostProcessor接口】构造方法");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("【BeanPostProcessor接口】调用postProcessBeforeInitialization方法，这里可对"+beanName+"的属性进行更改。");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("【BeanPostProcessor接口】调用postProcessAfterInitialization方法，这里可对"+beanName+"的属性进行更改。");
        return bean;
    }
}
