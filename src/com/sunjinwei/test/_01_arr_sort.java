package com.sunjinwei.test;

import java.util.Arrays;

/**
 * 手撕快排
 */
public class _01_arr_sort {


    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int left, int right) {
        // 终止条件：一定要left>=right
        if (left >= right) {
            return;
        }
        // 随机打乱最后一个索引的值
        int randomIndex = left + (int) (Math.random() * (right - left + 1));
        swap(nums, right, randomIndex);
        int[] partition = partition(nums, left, right);
        quickSort(nums, left, partition[0] - 1);
        quickSort(nums, partition[1] + 1, right);

    }

    private int[] partition(int[] nums, int left, int right) {
        // 小于区
        int less = left - 1;
        // 大于区
        int more = right;
        while (left < more) {
            // 小于区：交换
            if (nums[left] < nums[right]) {
                less++;
                swap(nums, left, less);
                left++;
            } else if (nums[left] > nums[right]) {
                more--;
                swap(nums, left, more);
            } else {
                left++;
            }
        }
        swap(nums, more, right);
        return new int[]{less + 1, more};

    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        _01_arr_sort arrSort = new _01_arr_sort();
        int[] arr = new int[]{2, 5, 3, 1};
        arrSort.sortArray(arr);

        System.out.println(Arrays.toString(arr));

        for (int i = 0; i < 100; i++) {
            int randomIndex = 5 + (int) (Math.random() * (4));

            System.out.println(randomIndex);
        }


    }
}
