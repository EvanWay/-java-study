package com.evan.study.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Callable
 * （1）有返回值
 * （2）Callable接口实现类中的run方法允许异常向上抛出，可以在内部处理，try catch
 * 但Runnable接口实现类中run方法的异常必须在内部处理，不能抛出
 *
 * @author Evan
 * @date 2022/5/6
 */
public class TestThread2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        //1、实现Callable接口（匿名内部类）
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("call()");
                return "call()-success";
            }
        };
        System.out.println("main线程——打印顺序1");

        //2、创建FutureTask对象，传入callable对象
        FutureTask<String> futureTask = new FutureTask<String>(callable);
        //3、通过Thread，启动线程
        new Thread(futureTask).start();

        // get()会阻塞主进程的继续往下执行，如果不调用不会阻塞
        System.out.println(futureTask.get() + "——打印顺序2");
        System.out.println("mian线程——打印顺序3");
    }
}
