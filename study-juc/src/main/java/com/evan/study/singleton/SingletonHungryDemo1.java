package com.evan.study.singleton;

/**
 * 饿汉-静态常量（可用）
 * 特点：类加载时就初始化，不用考虑线程安全问题
 * 缺点：在类装载的时候就完成实例化，没有达到Lazy Loading的效果。如果从始至终从未使用过这个实例，则会造成内存的浪费
 *
 * @author Evan
 * @date 2022/1/18
 */
public class SingletonHungryDemo1 {
    private final static SingletonHungryDemo1 instance = new SingletonHungryDemo1();

    private SingletonHungryDemo1() {
        System.out.println(Thread.currentThread().getName() + " 我是构造方法SingletonHungryDemo1");
    }

    public static SingletonHungryDemo1 getInstance() {
        return instance;
    }
}
