package com.sunjinwei.test;

public class _01quicksort {

    public void sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        // 随机固定一个枢纽pivot
        int randomIndex = left + (int) (Math.random() * (right - left + 1));
        // 将最右边的设置为pivot
        swap(arr, randomIndex, right);
        // 进行partition
        int pivot = partition(arr, left, right);
        // 递归处理
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }

    private int partition(int[] arr, int left, int right) {
        // [left, i]区域 <=pivot
        // [i+1, j-1]区域 >pivot
        // [j, right) 区域 未处理
        // right =pivot

        // 默认小于等于pivot区域为空
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (arr[j] <= arr[right]) {
                // 扩大<=pivot区域
                i++;
                // 将小于pivot的数据交换到 小于等于pivot区域
                swap(arr, i, j);
            }
        }
        // 将pivot回归到 小于等于pivot的区域。原理和for循环里面的逻辑是一样的
        swap(arr, i + 1, right);
        return i + 1;
    }

    private void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }
}
