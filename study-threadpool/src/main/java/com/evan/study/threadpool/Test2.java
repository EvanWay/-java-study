package com.evan.study.threadpool;

import java.util.concurrent.*;

/**
 * 线程池 submit
 * execute——只能提交Runnable类型的任务
 * submit——既可以用Runnable、又可以用Callable
 *
 * @author Evan
 * @date 2022/5/12
 */
public class Test2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //Runnable
        Thread1 thread1 = new Thread1();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        //提交完马上就执行后面的代码了
        executorService.submit(thread1);
        System.out.println("先执行");
        executorService.shutdown();

        //Callable
        Thread2 thread2 = new Thread2();
        ExecutorService executorService1 = Executors.newSingleThreadExecutor();
        Future<String> submit = executorService1.submit(thread2);
        //此方法会阻塞住，如果不调用不会阻塞
        String result = submit.get();
        System.out.println("任务线程执行完，后执行");
        executorService1.shutdown();

    }

    //内部类
    static class Thread1 implements Runnable {

        @Override
        public void run() {
            System.out.println("Runnable的run方法");
        }
    }

    //内部类
    static class Thread2 implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("Callable的call方法");
            return "Callable success";
        }
    }
}


