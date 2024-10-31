package com.evan.study.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock使用（待完善）
 *
 * @author Evan
 * @date 2022/11/8
 */
public class ReentrantLockTest {

    public static void main(String[] args) {
        //ReentrantLock可重入锁也是独占锁
        //ReentrantLock需要手动加锁和解锁,且解锁的操作尽量要放在finally代码块中,保证线程正确释放锁。
        //synchronized加锁解锁的过程是隐式的,用户不用手动操作,优点是操作简单，但显得不够灵活
        ReentrantLock lock = new ReentrantLock();

        for (int i = 1; i <= 3; i++) {
            lock.lock();
        }

        for (int i = 1; i <= 4; i++) {
            lock.unlock();
        }
        System.out.println("1");
    }
}
