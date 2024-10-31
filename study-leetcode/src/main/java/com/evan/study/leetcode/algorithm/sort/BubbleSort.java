package com.evan.study.leetcode.algorithm.sort;

/**
 * 冒泡排序
 * <p>
 * 过程：
 * 从左到右是小到大，不断把最大的数推到最右边
 *
 * @author Evan
 * @date 2022/6/12
 */
public class BubbleSort {

    public void bubbleSort(int[] array) {
        //外层：共n个数，一共进行n-1轮排序
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
