package com.sunjinwei.test;

/**
 * @program: com.sunjinwei.test
 * @author: sun jinwei
 * @create: 2021-05-07 08:06
 * @description: 插入排序
 **/
public class _20_sort_insert {

    public void sortInsert(int[] arr) {

        // 外层循环就是插入的元素i 正序
        // 内层循环就是原来的数组 不断的往前看 倒序遍历
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}