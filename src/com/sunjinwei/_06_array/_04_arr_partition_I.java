package com.sunjinwei._06_array;

/**
 * 数组的partition问题
 * 给定一个有序数组arr，调整arr使得这个数组的左半部分没有重复元素且升序，而不用保证右部分是否有序。【这道题和力扣26删除排序数组重复项是一样的】
 * <p>
 * 补充问题：给定一个数组arr，其中只可能含有0、1、2三个值，请实现arr的排序。
 * 另一种问法为：有一个数组，其中只有红球、蓝球和黄球，请实现红球全放在数组的左边，蓝球放在中间，黄球放在右边。
 * 另一种问法为：有一个数组，再给定一个值 k，请实现比 k 小的数都放在数组的左边，等于k的数都放在数组的中间，比k大的数都放在数组的右边。
 * <p>
 * 要求：
 * 所有题目实现O(1)内存
 */
public class _04_arr_partition_I {

    /**
     * 问题1：其实就是删除排序数组重复项
     *
     * @param arr
     */
    public void leftUnique(int[] arr) {

        int left = 0;
        int index = 1;
        while (index < arr.length) {
            if (arr[index] != arr[left]) {
                left++;
                arr[left] = arr[index];
            }
            index++;
        }
    }

    /**
     * 问题2：给定一个数组arr，其中只可能含有0、1、2三个值，请实现arr的排序。
     *
     * @param arr
     */
    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 左区域
        int left = -1;
        // 右区域
        int right = arr.length;
        // 遍历的索引
        int index = 0;

        while (index < right) {
            if (arr[index] < 0) {
                // 左边
                left++;
                swap(arr, left, index);
                index++;
            } else if (arr[index] == 1) {
                // 中间
                index++;
            } else {
                // 右边
                right--;
                swap(arr, right, index);
            }
        }
    }


    public void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
}
