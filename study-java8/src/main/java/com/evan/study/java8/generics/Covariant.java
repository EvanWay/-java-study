package com.evan.study.java8.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * 协变（子类赋值给父类）
 * 协变带来宽松，但是这样之后无法添加元素
 */
public class Covariant {

    public static double sum(List<? extends Number> list) {
        double result = 0;
        for (Number number : list) {
            result += number.doubleValue();
        }
        return result;
    }

    public static void main(String[] args) {
        // 子类赋值给父类，无法直接调用，sum方法需要加上协变 ? extends
        List<Double> doubleList = new ArrayList<>();
        sum(doubleList);
    }
}
