package com.sunjinwei._06_array;

import java.util.Arrays;

/**
 * 数组的partition
 */
public class _01_Arr_Partition {

    /**
     * partition1：将数组划分为左边小于pivot 中间等于pivot 右边大于pivot
     *
     * @param arr
     * @param pivot
     */
    public void partition(int[] arr, int pivot) {

        // 小于区的开始索引 初始值设置为-1 因为pivot可能不在原数组
        int left = -1;
        // 大于区的开始索引 初始值设置为len 因为pivot可能不在原数组
        int right = arr.length;
        // 遍历的索引 从0开始
        int index = 0;
        while (index < right) {
            // 小于区
            if (arr[index] < pivot) {
                left++;
                swap(arr, index, left);
                index++;
            } else if (arr[index] > pivot) {
                // 大于区
                right--;
                swap(arr, right, index);
            } else {
                // 等于区
                index++;
            }
        }
    }


    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    /**
     * partition2：返回等于pivot的区间的元素的开始和结束索引
     *
     * @param arr
     * @param pivot
     * @return
     */
    public int[] partition02(int[] arr, int pivot) {

        // 小于区的开始索引 初始值设置为-1 因为pivot可能不在原数组
        int left = -1;
        // 大于区的开始索引 初始值设置为len 因为pivot可能不在原数组
        int right = arr.length;
        // 遍历的索引 从0开始
        int index = 0;
        while (index < right) {
            // 小于区
            if (arr[index] < pivot) {
                left++;
                swap(arr, index, left);
                index++;
            } else if (arr[index] > pivot) {
                // 大于区
                right--;
                swap(arr, right, index);
            } else {
                // 等于区
                index++;
            }
        }
        return new int[]{left + 1, right - 1};
    }


    public static void main(String[] args) {

        _01_Arr_Partition arrPartition = new _01_Arr_Partition();
        int[] arr = new int[]{7, 9, 1, 8, 5, 2, 5};
        int pivot = 5;

        arrPartition.partition(arr, pivot);

        //int[] ints = arrPartition.partition02(arr, pivot);

        //System.out.println(Arrays.toString(ints));

        System.out.println(Arrays.toString(arr));


    }
}
