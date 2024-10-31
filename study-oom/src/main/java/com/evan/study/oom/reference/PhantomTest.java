package com.evan.study.oom.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 四、虚引用——一个对象与虚引用关联，则跟没有引用与之关联一样，在任何时候都可能被垃圾回收器回收
 * 虚引用必须和引用队列ReferenceQueue关联使用，当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，就会把这个虚引用加入到与之关联的引用队列中。
 *
 * @author Evan
 * @date 2022/4/28
 */
public class PhantomTest {
    public static void main(String[] args) {
        // 强引用
        byte[] bytes = new byte[1024 * 1024 * 10];
        // 赋值给虚引用
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference ref = new PhantomReference<>(bytes, referenceQueue);
        // 断开强引用
        bytes = null;
        System.out.println("是否被回收" + ref.get());
        System.out.println("是否加入队列" + ref.isEnqueued());
        System.gc();
        System.out.println("是否被回收" + ref.get());
        System.out.println("是否加入队列" + ref.isEnqueued());
    }
}
