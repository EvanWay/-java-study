package com.evan.study.thread;

/**
 * 线程礼让yield
 *
 * @author Evan
 * @date 2022/5/13
 */
public class TestThreadYield {
    public static void main(String[] args) {
        MyYield myYield = new MyYield();

        new Thread(myYield, "a").start();
        new Thread(myYield, "b").start();
    }

    static class MyYield implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "线程开始执行");
            //礼让——让cpu重新调度，礼让不一定成功
            Thread.yield();
            System.out.println(Thread.currentThread().getName() + "线程停止执行");
        }
    }
}


