package com.evan.study.singleton;

/**
 * @author Evan
 * @date 2022/1/18
 */
public class SingletonHungryDemo2Test {
    public static void main(String[] args) throws InterruptedException {

//        // 同步
//        for (int i = 0; i < 10; i++) {
//            SingletonHungryDemo2 instance = SingletonHungryDemo2.getInstance();
//        }

        // 多线程
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                SingletonHungryDemo2 instance = SingletonHungryDemo2.getInstance();
            },String.valueOf(i)).start();
        }

    }
}
