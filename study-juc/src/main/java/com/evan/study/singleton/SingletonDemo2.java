package com.evan.study.singleton;

/**
 * 懒汉（使用synchronized）（不推荐用）
 * 缺点：效率太低了，每个线程在想获得类的实例时候，执行getInstance()方法都要进行同步
 *
 * @author Evan
 * @date 2022/1/18
 */
public class SingletonDemo2 {
    private static SingletonDemo2 instance = null;

    private SingletonDemo2() {
        System.out.println(Thread.currentThread().getName() + " 我是构造方法SingletonDemo2");
    }

    public static synchronized SingletonDemo2 getInstance() {
        if (instance == null) {
            instance = new SingletonDemo2();
        }
        return instance;
    }
}
