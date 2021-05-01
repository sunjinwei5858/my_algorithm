package com.sunjinwei.test;

/**
 * 704 二分查找
 * <p>
 * 给定一个n个元素有序的（升序）整型数组nums和一个目标值target，写一个函数搜索nums中的target，如果目标值存在返回下标，否则返回 -1。
 */
public class _15_binary_search {

    public int search(int[] nums, int target) {

        int right = nums.length - 1;
        int left = 0;

        while (left <= right) {
            int mid = (right - left) / 2;
            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5, 7};
        _15_binary_search binarySearch = new _15_binary_search();
        int index = binarySearch.search(arr, 3);
        System.out.println(index);

    }
}
