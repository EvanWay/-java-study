package com.evan.study.thread;

/**
 * 线程join
 *
 * @author Evan
 * @date 2022/5/13
 */
public class TestThreadJoin implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("线程vip" + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestThreadJoin join = new TestThreadJoin();
        Thread thread = new Thread(join);
        thread.start();

        //main线程
        for (int i = 0; i < 1000; i++) {
            if (i == 50) {
                //插队
                thread.join();
            }
            System.out.println("main" + i);
        }

    }
}
