package com.sunjinwei.stack;

import java.util.Arrays;

/**
 * 窗口滑动：
 * 1。有一个整型数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次向右边滑一个位置
 * 2。如果arr数组长度为n，窗口大小为w，则一共产生n-w+1个窗口的最大值
 * 请实现一个函数：
 * 输入：整型数组arr，窗口大小为w
 * 输出：一个长度为n-w+1的数组res，res[i]表示每一种窗口状态下的最大值
 * 要求：时间复杂度为O(n)
 */
public class _05_window_max_arr_generate {

    /**
     * 方法一：时间复杂度O(N*W)
     *
     * @param arr          整型数组
     * @param windowLength 窗口大小
     * @return 滑动窗口的最大值数组
     */
    public int[] slidingWindow(int[] arr, int windowLength) {
        // 声明滑动窗口的长度
        int length = arr.length - windowLength + 1;
        // 滑动窗口数组
        int[] windowArr = new int[length];
        int left = 0;
        int right = windowLength;
        int count = 0;
        while (right <= arr.length && count <= length) {
            int max = Integer.MIN_VALUE;
            for (int i = left; i < right; i++) {
                max = Math.max(arr[i], max);
            }
            windowArr[count] = max;
            count++;
            left++;
            right++;
        }
        return windowArr;
    }

    /**
     * 方法二：使用双端队列
     *
     * @param args
     */

    public static void main(String[] args) {
        _05_window_max_arr_generate window_max = new _05_window_max_arr_generate();
        int[] arr = new int[]{4, 8, 5, 4, 3, 3, 6, 7};
        int[] ints = window_max.slidingWindow(arr, 3);
        System.out.println(Arrays.toString(ints));
    }
}
