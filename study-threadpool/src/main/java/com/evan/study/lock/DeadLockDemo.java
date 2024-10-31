package com.evan.study.lock;

/**
 * 死锁的例子
 * 死锁——多个线程在执行时候，互相持有对方接下来所需要的资源，互相等待
 * 我持有myAccount，需要去拿zhangSanAccount
 * 张三持有zhangSanAccount，需要去拿myAccount
 *
 * @author Evan
 * @date 2022/6/16
 */
public class DeadLockDemo {

    public static final Object myAccount = new Object();
    public static final Object zhangSanAccount = new Object();

    public static void main(String[] args) {
        //我向张三进行转账
        new Thread(() -> {
            synchronized (myAccount) {
                System.out.println("[1] 判断我的余额信息");
                try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
                synchronized (zhangSanAccount) {
                    System.out.println("[2] 我的账户执行扣款");
                    System.out.println("[3] 张三账户执行存款");
                }
            }
        }).start();

        //张三向我进行转账
        new Thread(() -> {
            synchronized (zhangSanAccount) {
                System.out.println("[1] 判断张三余额信息");
                try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
                synchronized (myAccount) {
                    System.out.println("[2] 张三账户执行扣款");
                    System.out.println("[3] 我的账户执行存款");
                }
            }
        }).start();
    }
}
