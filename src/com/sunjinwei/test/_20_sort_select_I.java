package com.sunjinwei.test;

/**
 * 选择排序
 */
public class _20_sort_select_I {

    /**
     * 思想：找最小值 然后放到第一个位置，然后以此类推
     * 1。第一次假设第一个元素是最小值 在第二个到以后的范围进行比较
     *
     * @param arr
     */
    public void selectSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            // 1默认外层循环的i为最小值 然后和[i+1, arr.length-1]进行比较
            int minIndex = i;
            // 2内存循环从i+1开始，找到最小值索引minIndex
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 3进行交换
            swap(arr, minIndex, i);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
