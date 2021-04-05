package com.sunjinwei._08_sort;

/**
 * 选择排序
 * 思想：所有元素找到最小的，放在第一个，这就是选择
 * 思路：
 * 第一次：全部走一遍找到最小 放在第一个位置
 * 第二次：左边界从第二个位置开始，全部走一遍，放在第二个位置
 * 第三次：左边界从第三个位置开始，全部走一遍，放在第三个位置
 */
public class _01_select_sort {

    public void selectSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 假设外层循环 就是最小元素
            int minIndex = i;
            // 第一个元素和第二个元素进行比较
            // 所以内层循环会比外层索引更大
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, minIndex, i);
        }
    }

    private void swap(int[] arr, int left, int right) {
        int temp = arr[right];
        arr[right] = arr[left];
        arr[left] = temp;
    }


}
