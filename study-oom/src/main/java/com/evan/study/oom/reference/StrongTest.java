package com.evan.study.oom.reference;

/**
 * 一、强引用——JVM必定不会回收这个对象，即使在内存不足的情况下，JVM宁愿抛出OutOfMemory错误也不会回收这种对象
 * -Xms20m -Xmx20m
 *
 * @author Evan
 * @date 2022/4/29
 */
public class StrongTest {
    public static void main(String[] args) {
        // bytes为一个强引用，不会回收
        byte[] bytes = new byte[1024 * 1024 * 10];

        // 新建一个bytes就会OOM
        byte[] bytes2 = new byte[1024 * 1024 * 10];
    }
}
