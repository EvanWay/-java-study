package com.evan.study.threadpool;

import java.util.concurrent.*;

/**
 * 线程池（两种实现）
 * 一、new ThreadPoolExecutor
 * 二、使用Executors（其实也都是调用ThreadPoolExecutor）
 *
 * @author Evan
 * @date 2022/4/26
 */
public class Test {
    public static void main(String[] args) {

        //一、new ThreadPoolExecutor
        /**
         * 线程池7个参数：
         * corePoolSize：核心线程数——并不是一开始new线程池就有这么多core线程，要等execute，submit，就会加一个线程
         * maximumPoolSize：允许的最大线程数
         * keepAliveTime：当线程数大于核心时，这是多余的空闲线程在终止前等待新任务的最长时间。
         * unit：线程活动保持的时间单位。TimeUnit.SECONDS
         * workQueue：保存任务的队列
         * threadFactory：创建新线程时的工厂
         * handler：拒绝策略，由于达到线程边界和队列容量而阻塞执行时使用的处理程序
         * （1）AbortPolicy 直接抛出异常（默认拒绝策略）
         * （2）DiscardPolicy 直接丢弃，不抛异常
         * （3）DiscardOldestPolicy 丢弃阻塞队列中最先进入的一个任务，将新任务加入
         * （4）CallerRunsPolicy 满了之后，会把线程直接用调用者线程执行
         *
         * 过程：
         * 3个核心线程，当新的任务进来，放入阻塞队列（长度3），放满之后，最大线程数5个，新创建线程。直到最大线程数也满了，拒绝策略
         * 3+3+2，超过8个同时进行，就会触发拒绝策略
         */
        ExecutorService executorService = new ThreadPoolExecutor(3, 5, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 8; i++) {
            final int num = i + 1;
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "====>办理业务" + "第" + num);
            });
        }
        executorService.shutdown();

        //二、使用Executors（其实也都是调用ThreadPoolExecutor）

        //newSingleThreadExecutor()单线程化的线程池，一个线程来执行任务，保证所有任务顺序执行
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 6; i++) {
            final int j = i;
            executorService2.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " " + j);
            });
        }
        executorService2.shutdown();

        //newFixedThreadPool(5)定长的线程池，可控制线程最大并发数，超出的线程会在队列中等待。
        ExecutorService executorService3 = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            final int j = i;
            executorService3.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " " + j);
            });
        }
        executorService3.shutdown();

        //newCachedThreadPool()可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
        ExecutorService executorService4 = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int j = i;
            executorService4.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " " + j);
            });
        }
        executorService4.shutdown();
    }
}
