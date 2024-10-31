package com.evan.study.singleton;

/**
 * @author Evan
 * @date 2022/1/18
 */
public class SingletonDemo1Test {
    public static void main(String[] args) throws InterruptedException {

//        // 同步
//        for (int i = 0; i < 10; i++) {
//            SingletonDemo1 instance = SingletonDemo1.getInstance();
//        }

        // 多线程
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                SingletonDemo1 instance = SingletonDemo1.getInstance();
            },String.valueOf(i)).start();
        }

    }
}
