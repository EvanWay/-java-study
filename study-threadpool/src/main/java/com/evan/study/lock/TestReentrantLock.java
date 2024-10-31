package com.evan.study.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试可重入锁ReentrantLock
 *
 * @author Evan
 * @date 2022/5/16
 */
public class TestReentrantLock {
    public static void main(String[] args) {
        //两个线程争夺一个锁
        TestReentrant test = new TestReentrant();
        new Thread(test,"t1").start();
        new Thread(test,"t2").start();
        System.out.println("main线程结束");
    }
}

class TestReentrant implements Runnable {
    private final ReentrantLock lock = new ReentrantLock();
    private int ticketNums = 10;

    @Override
    public void run() {
        try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
        while (true) {
            try {
                //加锁
                lock.lock();
                if (ticketNums > 0) {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "-" + ticketNums--);
                } else {
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //解锁
                lock.unlock();
            }
        }
    }
}