package com.evan.study.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 内存溢出测试
 * 问题：在try中OOM了。代码还会执行到finally里嘛。答案：会，但是如果不够内存，finally里也不一定能执行
 *
 * JVM启动参数需要：-Xms10M -Xmx10M -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
 * -Xms10M -Xmx10M
 * -XX:+PrintGCDetails
 * -XX:+HeapDumpOnOutOfMemoryError（开启堆内存溢出导出堆内存到文件，默认在项目的根目录下，需要指定其它路径用-XX:HeapDumpPath=/tmp）OOM时候就会生成.hprof文件
 *
 * OOM也是个异常，如果catch处理的话
 * java虚拟机退出的条件是：虚拟机内不存在非守护线程。
 * 线程结束的条件是：线程发生未处理的异常会导致线程结束，如果处理了OOM异常，后面就还能继续运行
 *
 * @author Evan
 * @date 2022/8/17
 */
public class OomTest1 {
    public static void main(String[] args) {
        System.out.println("start");
        try {
            //发生OOM
            List<String> list = new ArrayList<String>();
            while (true) {
                list.add("1234567890");
            }
        } finally {
            System.out.println("finally");
        }
    }
}
