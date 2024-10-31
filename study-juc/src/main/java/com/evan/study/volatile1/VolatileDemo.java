package com.evan.study.volatile1;

/**
 * @author Evan
 * @date 2022/1/10
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 假设是主物理内存
 */
class MyData {

//    int number = 0;
    volatile int number = 0;

    public void addTo60() {
        this.number = 60;
    }

    public void numberPlusPlus() {
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger(0);

    public void atomicIntegerPlusPlus(){
        atomicInteger.getAndIncrement();
    }
}

/**
 * Volatile（三大特性：可见性、不保证原子性、禁止指令重排）
 * 1. 验证volatile的可见性
 * 2. 验证volatile不保证原子性
 *      原子性——完整性，不可分割性。某个线程在做某个业务时，不可以被加塞或被分割，需要整体完成，要么同时成功，要么同时失败
 *      volatile不保证原子性原因——i++，实际上分为4步，取值，+，更新主物理内存；volatile不保证更新主物理内存时，线程竞争存在覆盖重写，丢数据。
 *      解决方法：1 使用synchronized （大材小用） 2 使用juc下的 AtomicInteger
 * 3. 验证volatile禁止指令重排
 *      编译时，编译器会进行指令重排
 *      使用volatile禁止指令的重排
 */
public class VolatileDemo {

    public static void main(String[] args) {
        // 1.验证可见性
        visibility();

        // 2.验证不保证原子性
        //atomic1();

        // 使用AtomicInteger
        //atomic2();
    }

    /**
     * 1. 验证volatile的可见性
     * 不添加volatile关键字修饰——不可见
     * 添加volatile关键字修饰——可见
     */
    private static void visibility() {

        MyData myData = new MyData();

        // AAA线程
        new Thread(() -> {

            System.out.println(Thread.currentThread().getName() + " AAA线程开始");

            // 线程睡眠3秒，假设在进行运算
            try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
            // 修改number的值
            myData.addTo60();

            // 输出修改后的值
            System.out.println(Thread.currentThread().getName() + " AAA线程更新number为:" + myData.number);

        }, "AAA").start();

        // 主线程
        while(myData.number == 0) {
            // main线程就一直在这里等待循环，直到number的值不等于零
        }

        // 按道理这个值是不可能打印出来的，因为主线程运行的时候，number的值为0，所以一直在循环
        // 如果能输出这句话，说明AAA线程在睡眠3秒后，更新的number的值，重新写入到主内存，并被main线程感知到了
        System.out.println(Thread.currentThread().getName() + " 主线程感知到number不等于0");

        /**
         * 最后输出结果：
         * AAA AAA线程开始
         * AAA AAA线程更新number为:60
         * 最后线程没有停止，并行没有输出  主线程继续 这句话，说明没有用volatile修饰的变量，是没有可见性
         */
    }

    /**
     *  2.验证不保证原子性
     *  20个线程，每个加1000次，多线程同时进行，不保证原子
     */
    private static void atomic1() {
        MyData myData = new MyData();
        // 创建20个线程，线程里面进行1000次循环
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.numberPlusPlus();
                }
            }, i + "线程").start();
        }

        //java默认的线程 main线程和 GC线程，活动线程数>2 说明上面代码还没执行完
        while (Thread.activeCount() > 2) {
            //线程礼让
            Thread.yield();
        }
        System.out.println("最终的number值为:" + myData.number);
    }

    /**
     *  2.验证不保证原子性
     *  使用AtomicInteger，保证原子性
     */
    private static void atomic2() {
        MyData myData = new MyData();
        // 创建20个线程，线程里面进行1000次循环
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.atomicIntegerPlusPlus();
                }
            }, i + "线程").start();
        }

        //java默认的线程 main线程和 GC线程，活动线程数>2 说明上面代码还没执行完
        while (Thread.activeCount() > 2) {
            //线程礼让
            Thread.yield();
        }
        System.out.println("最终的number值为:" + myData.atomicInteger.get());
    }
}
