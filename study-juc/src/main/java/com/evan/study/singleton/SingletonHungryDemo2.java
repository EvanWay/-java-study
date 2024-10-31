package com.evan.study.singleton;

/**
 * 饿汉-静态内部类（推荐用）
 * 特点：静态内部类方式在Singleton类被装载时并不会立即实例化，而是在需要实例化时，调用getInstance方法，才会装载SingletonInstance类，从而完成Singleton的实例化
 *
 * @author Evan
 * @date 2022/1/18
 */
public class SingletonHungryDemo2 {

    private SingletonHungryDemo2() {
        System.out.println(Thread.currentThread().getName() + " 我是构造方法SingletonHungryDemo2");
    }

    private static class SingletonInstance {
        private static final SingletonHungryDemo2 INSTANCE = new SingletonHungryDemo2();
    }

    public static SingletonHungryDemo2 getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
