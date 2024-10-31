package com.evan.study.singleton;

/**
 * 懒汉（DCL-double check lock-双重检查）（推荐）
 * 特点：锁的粒度变小
 *      需要添加volatile禁止指令重排，不然instance = new SingletonDemo3();会未完全初始化
 *
 * @author Evan
 * @date 2022/1/18
 */
public class SingletonDemo3 {
    private static volatile SingletonDemo3 instance = null;

    private SingletonDemo3() {
        System.out.println(Thread.currentThread().getName() + " 我是构造方法SingletonDemo3");
    }

    public static SingletonDemo3 getInstance() {
        //第一重判断，先判断实例是否存在，不存在再加锁处理
        if (instance == null) {
            //加锁的程序在某一时刻只允许一个线程访问
            synchronized (SingletonDemo3.class) {
                //第二重判断
                if (instance == null) {
                    instance = new SingletonDemo3();
                }
            }
        }
        return instance;
    }
}
