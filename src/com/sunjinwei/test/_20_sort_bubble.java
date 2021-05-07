package com.sunjinwei.test;

/**
 * @program: com.sunjinwei.test
 * @author: sun jinwei
 * @create: 2021-05-07 08:02
 * @description: 冒泡排序
 **/
public class _20_sort_bubble {

    public void sortBubble(int[] arr) {

        // 外层循环从最后一个位置开始 因为是将最大值放到最后面开始
        for (int i = arr.length - 1; i >= 0; i++) {
            // 内层循环从第一个开始
            for (int j = 0; j < i; j++) {
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