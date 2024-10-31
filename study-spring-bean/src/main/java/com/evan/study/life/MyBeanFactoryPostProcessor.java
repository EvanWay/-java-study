package com.evan.study.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author Evan
 * @date 2022/4/19
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public MyBeanFactoryPostProcessor() {
        System.out.println("【BeanFactoryPostProcessor接口】构造方法");
    }

    /**
     * 重写BeanFactoryPostProcessor接口的postProcessBeanFactory方法，可通过该方法对beanFactory进行设置
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("【BeanFactoryPostProcessor接口】调用BeanFactoryPostProcessor接口的postProcessBeanFactory方法");
    }
}
