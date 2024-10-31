package com.evan.study.thread;

/**
 * 并发问题
 * 多个线程操作同一个资源的情况下，线程不安全
 *
 * @author Evan
 * @date 2022/5/5
 */
public class TestThread1 implements Runnable {

    //票数
    private int tickNums = 10;

    @Override
    public void run() {
        while (true) {
            if (tickNums <= 0) {
                break;
            }
            System.out.println(Thread.currentThread().getName() + "-->拿到了第" + tickNums-- + "票");
        }
    }

    public static void main(String[] args) {
        TestThread1 testThread1 = new TestThread1();

        new Thread(testThread1, "小明").start();
        new Thread(testThread1, "小红").start();
        new Thread(testThread1, "黄牛").start();
    }
}
