package com.sunjinwei._08_sort;

/**
 * 在数组中找到最大值：使用递归求解
 */
public class _04_get_max {

    public int getMax(int[] arr) {
        if (arr == null) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private int process(int[] arr, int left, int right) {
        if (left == right) {
            return arr[left];
        }
        // 使用位运算求中点
        int middle = left + (right - left) >> 1;
        int leftMax = process(arr, left, middle);
        int rightMax = process(arr, middle + 1, right);
        return Math.max(leftMax, rightMax);
    }

}
