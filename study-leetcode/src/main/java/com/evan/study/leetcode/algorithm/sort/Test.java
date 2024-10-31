package com.evan.study.leetcode.algorithm.sort;

import java.util.Arrays;

/**
 * 测试各种排序算法
 *
 * 10种排序口诀：
 * 选泡插
 * 快归堆希桶计基
 * 恩方恩老恩一三
 * 对恩加K恩乘K
 * 不稳稳稳不稳稳
 * 不稳不稳稳稳稳
 *
 * @author Evan
 * @date 2022/6/12
 */
public class Test {
    public static void main(String[] args) {

//        //快速排序
//        int[] array = new int[]{19, 97, 9, 17, 1, 8};
//        QuickSort quickSort = new QuickSort();
//        quickSort.quickSort(array, 0, array.length - 1);
//        System.out.println(Arrays.toString(array));

        //冒泡排序
        int[] array = new int[]{19, 97, 9, 17, 1, 8};
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.bubbleSort(array);
        System.out.println(Arrays.toString(array));
    }
}
