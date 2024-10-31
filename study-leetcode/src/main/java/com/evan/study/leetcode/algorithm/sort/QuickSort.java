package com.evan.study.leetcode.algorithm.sort;

/**
 * 快速排序，不稳，nlogn
 *
 * 过程：
 * 1、选定pivot中心轴（默认选左边第一个）
 * 2、left，right，一左一右往中间
 * 3、right与pivot比较，小的话就换到left位置，大于等于继续往左移
 * 4、left与pivot比较，大的话就换到right位置，小于等于继续往右移
 * 5、left和right重合时候，将pivot放入该位置
 * 6、分别对左右子序列重复前五步操作
 *
 * @author Evan
 * @date 2022/6/12
 */
public class QuickSort {

    public void quickSort(int[] arry, int L, int R) {
        if (L > R) {
            return;
        }
        int left = L;
        int right = R;
        int pivot = arry[left];
        while (left < right) {
            //right的值大于中心轴，则继续减1往左移动，否则就换到left的位置
            while (left < right && arry[right] >= pivot) {
                right--;
            }
            if (left < right) {
                arry[left] = arry[right];
            }
            while (left < right && arry[left] <= pivot) {
                left++;
            }
            if (left < right) {
                arry[right] = arry[left];
            }
            if (left >= right) {
                arry[left] = pivot;
            }
        }
        //左子序列
        quickSort(arry, L, right - 1);
        //右子序列
        quickSort(arry, right + 1, R);
    }
}
