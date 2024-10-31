package com.evan.study.life;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Evan
 * @date 2022/4/19
 */
public class TestLifeCycle {

    public static void main(String[] args) {
        System.out.println("--------------【初始化容器】---------------");
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("--------------【初始化容器完成】------------------");

        //获取studentBean
        StudentBean studentBean = ctx.getBean("studentBean", StudentBean.class);
        System.out.println("获取到bean：" + studentBean);

        System.out.println("--------------【销毁容器】----------------------");
        ctx.close();
        System.out.println("--------------【销毁容器完成】----------------------");
    }
}
