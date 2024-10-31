package com.evan.study.oom.reference;

import java.lang.ref.WeakReference;

/**
 * 三、弱引用——是一种比软引用较弱的引用类型。在系统GC时，只要发现弱引用，不管系统堆空间是否足够，都会将对象进行回收。
 *
 * @author Evan
 * @date 2022/4/28
 */
public class WeakTest {
    public static void main(String[] args) {
        // 强引用
        byte[] bytes = new byte[1024 * 1024 * 10];
        // 赋值给弱引用
        WeakReference<byte[]> ref = new WeakReference<>(bytes);
        // 断开强引用
        bytes = null;
        System.out.println("是否被回收" + ref.get());
        System.gc();
        System.out.println("是否被回收" + ref.get());
    }
}
