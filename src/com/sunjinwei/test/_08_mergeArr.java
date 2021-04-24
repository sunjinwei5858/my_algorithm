package com.sunjinwei.test;

/**
 * 88 合并两个有序数组
 * <p>
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
 */
public class _08_mergeArr {

    /**
     * 时间复杂度：O(m+n)O(m+n)。
     * 指针移动单调递增，最多移动m+n次，因此时间复杂度为 O(m+n)
     * <p>
     * 空间复杂度：O(m+n)
     * 需要建立长度为m+n的中间数组
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 创建临时数组
        int[] arr = new int[m + n];
        // 声明指针
        int i = 0;
        // 数组1的指针
        int p1 = 0;
        // 数组2的指针
        int p2 = 0;
        // 处理边界问题 p1取不到m p2取不到n
        while (p1 < m && p2 < n) {
            if (nums1[p1] < nums2[p2]) {
                arr[i] = nums1[p1];
                p1++;
            } else {
                arr[i] = nums2[p2];
                p2++;
            }
            i++;
        }
        while (p1 < m) {
            arr[i] = nums1[p1];
            p1++;
        }
        while (p2 < n) {
            arr[i] = nums2[p2];
            p2++;
        }
        for (int j = 0; j < arr.length; j++) {
            nums1[j] = arr[j];
        }
    }

}
