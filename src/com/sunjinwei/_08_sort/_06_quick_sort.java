package com.sunjinwei._08_sort;

import java.util.Arrays;

/**
 * 快排
 * 分界点应该随机选取，而不是取最后一个或者中间点
 * 思路：有了分界点，就可以把数组通过荷兰国旗问题分成三个部分
 * 左侧：小于分界点
 * 中间值：等于分界点
 * 右侧：大于分界点
 */
public class _06_quick_sort {

    public void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * 快排逻辑处理：使用荷兰问题解法
     *
     * @param arr
     * @param left
     * @param right
     */
    private void quickSort(int[] arr, int left, int right) {
        // 终止条件
        if (left >= right) {
            return;
        }
        // 随机打乱arr[right]的值 这里使用arr[right]最后一个元素作为分界点
        int randomIndex = left + (int) (Math.random() * (right - left + 1));
        swap(arr, randomIndex, right);
        // 使用荷兰国旗寻找分界点范围数组
        int[] pivotArr = partion(arr, left, right);
        quickSort(arr, left, pivotArr[0] - 1);
        quickSort(arr, pivotArr[1] + 1, right);
    }

    /**
     * 荷兰国旗解法
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private int[] partion(int[] arr, int left, int right) {
        // 小于区
        int less = left - 1;
        // 大于区: 因为先默认使用right元素作为分界点 保证了分界点在数组中 可以不需要more=right+1
        int more = right;
        // 当前遍历的索引
        int curr = left;
        while (curr < more) {
            // 当前元素比分界点小 继续放在小于区
            if (arr[curr] < arr[right]) {
                less++;
                swap(arr, less, curr);
                curr++;
            } else if (arr[curr] > arr[right]) {
                more--;
                swap(arr, more, curr);
            } else {
                curr++;
            }
        }
        // 交换分界点
        swap(arr, more, right);
        return new int[]{less + 1, more};

    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {

        _06_quick_sort _06_quick_sort = new _06_quick_sort();
        int[] arr = new int[]{2, 3, 1, 9, 7, 6, 1, 4, 5};
        _06_quick_sort.quickSort(arr);
        System.out.println(Arrays.toString(arr));

    }

}
