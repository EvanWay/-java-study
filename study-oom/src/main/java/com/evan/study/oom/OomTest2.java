package com.evan.study.oom;

/**
 * 内存溢出测试2
 *
 * 空间够就可以执行，空间不够就还是不行。
 * 不建议在 OutOfMemoryErrors 的情况下依赖 finally 块
 *
 * JVM启动参数需要：-Xms10M -Xmx10M
 *
 * @author Evan
 * @date 2022/8/17
 */
public class OomTest2 {
    public static void main(String[] args) {
        System.out.println("start");
        try {
            throw new OutOfMemoryError();
        } finally {
            byte[] b = new byte[1024 * 1024 * 1024];
            //空间还是不足，后续的就无法执行到
            System.out.println("finally");
        }
    }
}
