package com.evan.study.java8.list;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * List的用法
 */
public class ListDemo {
    public static void main(String[] args) {

        /* 一、List初始化 */
        // 最原始
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);

        // {{}} 双括号语法
        List<Integer> list2 = new ArrayList<Integer>() {{
            add(1);
            add(2);
        }};
        list2.add(3);

        // Arrays.asList() 不可变 不支持增删元素
        List<Integer> list3 = Arrays.asList(1, 2);
//        list3.add(3);//会报错

        // ArrayList包装下转为可变
        List<Integer> list4 = new ArrayList<>(Arrays.asList(1, 2));
        list4.add(3);

        // jdk8 Stream
        List<Integer> list5 = Stream.of(1, 2, 3).collect(Collectors.toList());


        /* 二、lambda使用 */
        //1、过滤
        List<String> nameList = new ArrayList<>();
        nameList.add("周杰伦");
        nameList.add("陈奕迅");
        nameList.add("林俊杰");
        List<String> test = nameList.stream().filter(x -> x.startsWith("陈")).collect(Collectors.toList());
        System.out.println(test);


        //2、for循环删除元素
        List<String> newList = new ArrayList<>();
        List<String> newList2 = new ArrayList<>();
        newList.addAll(nameList);
        newList2.addAll(nameList);
        //（1）迭代是可以删除的
        Iterator<String> iterator = newList.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (next.equals("周杰伦")) {
                iterator.remove();
            }
        }
        //（2）倒序删除
        for (int i = newList2.size() - 1; i > -1; i--) {
            if (newList2.get(i).equals("林俊杰")) {
                newList2.remove(newList2.get(i));
            }
        }
        //（3）直接使用lambda
        newList2.removeIf(next -> next.equals("周杰伦"));
        //（4）foreach删除会有异常的（报错）
        for (String name : nameList) {
            if (name.equals("周杰伦")) {
                nameList.remove(name);
            }
        }
    }
}
