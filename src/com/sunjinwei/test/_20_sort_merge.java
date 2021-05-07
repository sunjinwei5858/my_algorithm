package com.sunjinwei.test;

/**
 * @program: com.sunjinwei.test
 * @author: sun jinwei
 * @create: 2021-05-07 08:11
 * @description: 归并排序
 **/
public class _20_sort_merge {

    public void sortMerge(int[] arr) {

        merge(arr, 0, arr.length - 1);
    }

    private void merge(int[] arr, int left, int right) {

        if (left >= right) {
            return;
        }
        int mid = left + (right - left) >> 1;
        merge(arr, left, mid);
        merge(arr, mid + 1, right);

        // 合并两个有序数组
        mergeSortArr(arr, left, mid, right);
    }

    private void mergeSortArr(int[] arr, int left, int mid, int right) {
        // 创建临时数组
        int[] temp = new int[right - left + 1];
        // 声明两个有序数组的指针
        // 左数组[left, mid] bug
        int p1 = left;
        // 右数组[mid+1, right] bug
        int p2 = mid + 1;
        // 声明临时数组遍历的索引
        int i = 0;
        // 开始合并 bug：mid写成left了
        while (p1 <= mid && p2 <= right) {
            if (arr[p1] >= arr[p2]) {
                arr[i] = arr[p2];
                p2++;
            } else {
                arr[i] = arr[p1];
            }
            i++;
        }

        while (p1 <= mid) {
            arr[i] = arr[p1];
            i++;
            p1++;
        }

        while (p2 <= right) {
            arr[i] = arr[p2];
            i++;
            p2++;
        }

        // 处理原始数组
        for (int j = 0; j < temp.length; j++) {
            arr[left + j] = temp[j];
        }


    }
}