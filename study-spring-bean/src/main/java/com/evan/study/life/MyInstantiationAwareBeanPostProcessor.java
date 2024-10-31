package com.evan.study.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

/**
 * 一般情况下，当我们需要实现InstantiationAwareBeanPostProcessor接口时，是通过继承Spring框架中InstantiationAwareBeanPostProcessor接口实现类
 * InstantiationAwareBeanPostProcessorAdapter这个适配器类来简化我们实现接口的工作
 */
public class MyInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter {

    public MyInstantiationAwareBeanPostProcessor() {
        System.out.println("【InstantiationAwareBeanPostProcessor接口】构造方法");
    }

    /**
     * 实例化Bean之前调用
     */
    @Override
    public Object postProcessBeforeInstantiation(Class beanClass, String beanName) throws BeansException {
        System.out.println("【InstantiationAwareBeanPostProcessor接口】调用InstantiationAwareBeanPostProcessor接口的postProcessBeforeInstantiation方法");
        return null;
    }

    /**
     * 实例化Bean之后调用
     */
    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("【InstantiationAwareBeanPostProcessor接口】调用InstantiationAwareBeanPostProcessor接口的postProcessAfterInstantiation方法");
        return super.postProcessAfterInstantiation(bean, beanName);
    }

    /**
     * 设置某个属性时调用
     */
    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        System.out.println("【InstantiationAwareBeanPostProcessor接口】调用InstantiationAwareBeanPostProcessor接口的postProcessProperties方法");
        return pvs;
    }
}
