package com.evan.study.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 内存溢出测试demo2
 * JVM启动参数需要：
 * -Xms10M -Xmx10M
 * -XX:+PrintGCDetails
 * -XX:+HeapDumpOnOutOfMemoryError（开启堆内存溢出导出堆内存到文件，默认在项目的根目录下，需要指定其它路径用-XX:HeapDumpPath=/tmp）OOM时候就会生成.hprof文件
 *
 * @author Evan
 * @date 2022/4/28
 */
public class MatTest2 {
    public static void main(String[] args) {
        // 混淆导致oom代码，看mat是否看得出
        byte[] holder = new byte[2 * 1024 * 1024];

        List<String> list = new ArrayList<String>();
        while (true) {
            list.add("1234567890");
        }
    }
}