package com.evan.study.singleton;

/**
 * 懒汉（线程不安全）（不可用）
 * 缺点：只能在单线程下使用，多线程不安全
 *
 * @author Evan
 * @date 2022/1/18
 */
public class SingletonDemo1 {

    private static SingletonDemo1 instance = null;

    private SingletonDemo1() {
        System.out.println(Thread.currentThread().getName() + " 我是构造方法SingletonDemo1");
    }

    public static SingletonDemo1 getInstance() {
        if (instance == null) {
            instance = new SingletonDemo1();
        }
        return instance;
    }
}
