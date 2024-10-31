package com.evan.study.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 内存溢出测试demo
 * JVM启动参数需要：
 * -Xms10M -Xmx10M
 * -XX:+PrintGCDetails
 * -XX:+HeapDumpOnOutOfMemoryError（开启堆内存溢出导出堆内存到文件，默认在项目的根目录下，需要指定其它路径用-XX:HeapDumpPath=/tmp）OOM时候就会生成.hprof文件
 *
 * @author Evan
 * @date 2022/4/28
 */
public class MatTest1 {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        while (true) {
            users.add(new User());
        }
    }
}