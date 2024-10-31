package com.evan.study.volatile1;

/**
 * 指令重排demo（暂时无法重现，都是结果6；理论上多线程可能重排，结果5）
 *
 * @author Evan
 * @date 2022/1/14
 */
public class ResortSeqDemo {
    int a = 0;
    boolean flag = false;

    public void method1() {
        a = 1;
        flag = true;
    }

    public void method2() {
        if (flag) {
            a = a + 5;
            System.out.println("结果" + a);
        }
    }

    public static void main(String[] args) {
//        //单线程（顺序不会变）
//        ResortSeqDemo demo = new ResortSeqDemo();
//        demo.method1();
//        demo.method2();


        //多线程（指令重排）
        for (int i = 0; i < 100; i++) {
            ResortSeqDemo demo2 = new ResortSeqDemo();
            new Thread(() -> {demo2.method1();}, "aaa").start();
            new Thread(() -> {demo2.method2();}, "bbb").start();

        }
    }
}
