package com.evan.study;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author Evan
 * @date 2022/11/4
 */
@SpringBootTest
public class JavaTests {

    /* String字符串 */
    @Test
    void test() {
        //第一个字面量声明时，就会被字符串常量池存储
        String s1 = "Hello World";
        //已经存在，直接将一样的内存指针赋给s2
        String s2 = "Hello World";

        if (s1 == s2) {
            System.out.println("相等");
        }
    }

    @Test
    void test2() {
        //通过字面量声明
        String s1 = "Hello World";
        //通过new一个对象创建
        String s2 = new String("Hello World");

        if (s1 == s2) {
            System.out.println("相等");
        } else {
            System.out.println("不相等");
        }
    }
}
