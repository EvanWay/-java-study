package com.evan.study.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 测试LinkedListTest
 * 不能用for get(i)，要使用foreach
 * @author Evan
 * @date 2022/2/13
 */
public class LinkedListTest {

    private static final int LIST_SIZE = 100000;

    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<Integer>();
        List<Integer> linkedList = new LinkedList<Integer>();

        for (int i = 0; i < LIST_SIZE; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.get(i);
        }
        System.out.println("ArrayList的for循环遍历速度：" + (System.currentTimeMillis() - startTime) + "ms");

        // LinkedList，for循环通过get(i)取得某一元素时需要对list重新遍历。最好使用迭代器Iterator或者foreach去遍历。
        startTime = System.currentTimeMillis();
        for (int i = 0; i < linkedList.size(); i++) {
            linkedList.get(i);
        }
        System.out.println("LinkedList的for循环遍历速度：" + (System.currentTimeMillis() - startTime) + "ms");
    }
}
