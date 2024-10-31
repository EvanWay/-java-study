package com.evan.study.singleton;

/**
 * @author Evan
 * @date 2022/1/18
 */
public class SingletonDemo3Test {
    public static void main(String[] args) throws InterruptedException {

//        // 同步
//        for (int i = 0; i < 10; i++) {
//            SingletonDemo3 instance = SingletonDemo3.getInstance();
//        }

        // 多线程
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                SingletonDemo3 instance = SingletonDemo3.getInstance();
            },String.valueOf(i)).start();
        }

    }
}
