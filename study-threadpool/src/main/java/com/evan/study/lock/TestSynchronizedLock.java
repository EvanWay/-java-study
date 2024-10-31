package com.evan.study.lock;

/**
 * 测试synchronized
 *
 * @author Evan
 * @date 2022/11/18
 */
public class TestSynchronizedLock {
    public static void main(String[] args) {
        //两个线程争夺一个锁
        TestSynchronized testSynchronized = new TestSynchronized();
        new Thread(testSynchronized,"t1").start();
        new Thread(testSynchronized,"t2").start();
        System.out.println("main线程结束");
    }
}

class TestSynchronized implements Runnable {
    private Object obj = new Object();
    private int ticketNums = 10;

    @Override
    public void run() {
        try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
        while (true) {
            synchronized(obj){
                if (ticketNums > 0) {
                    try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
                    System.out.println(Thread.currentThread().getName() + "-" + ticketNums--);
                } else {
                    break;
                }
            }
            try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
