package com.evan.study.oom.reference;

import java.lang.ref.SoftReference;

/**
 * 二、软引用——JVM会根据情况来回收，当堆使用率接近阈值时，会去回收软引用。
 * -Xms20m -Xmx20m
 *
 * @author Evan
 * @date 2022/4/28
 */
public class SoftTest {
    public static void main(String[] args) {

        // 强引用
        byte[] bytes = new byte[1024 * 1024 * 10];
        // 赋值给软引用
        SoftReference<byte[]> ref = new SoftReference<>(bytes);
        // 断开强引用
        bytes = null;
        System.out.println("是否被回收" + ref.get());
        // 新创建一个
        byte[] bytes2 = new byte[1024 * 1024 * 10];
        // 不限制内存大小，内存足够不回收；限制内存-Xmx20m时，内存不足回收掉
        System.out.println("是否被回收" + ref.get());

    }
}

