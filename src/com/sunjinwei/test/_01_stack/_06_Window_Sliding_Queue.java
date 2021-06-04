package com.sunjinwei.test._01_stack;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 滑动窗口获取最大值
 * 其实就是单调队列，获取最大值 那么就是单调递增
 */
public class _06_Window_Sliding_Queue {

    /**
     * 1.获取最大值 那么保证单调队列是单调递增的
     * 2.对头存储最大值
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] getWindowMax(int[] arr, int k) {
        // 鲁棒性
        if (arr == null || arr.length == 0 || k < 0) {
            return null;
        }
        // 使用双端队列来维护单调队列 队列存储索引
        LinkedList<Integer> queue = new LinkedList<>();
        // 滑动窗口数组的size
        int[] res = new int[arr.length - k + 1];
        // 滑动窗口的索引
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            // 1维护单调队列 单调递增 如果队尾小于当前元素 那么需要进行弹出
            while (!queue.isEmpty() && arr[queue.peekLast()] <= arr[i]) {
                queue.pollLast();
            }
            // 2将元素放入队列
            queue.addLast(i);
            // 3处理队列头部元素是否过期 过期那么需要弹出
            // 判断是否过期 队列头部的索引等于i-k
            if (queue.peekFirst() == i - k) {
                queue.pollFirst();
            }
            // 4最后 处理是否到达了窗口的size
            // bug 是i-k+1>=0比较
            if (i - k + 1 >= 0) {
                // bug 是arr的值 而不是索引i
                res[index] = arr[queue.peekFirst()];
                index++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        _06_Window_Sliding_Queue windowSlidingQueue = new _06_Window_Sliding_Queue();

        int[] arr = new int[]{4, 3, 5, 4, 3, 3, 6, 7};

        int[] windowMax = windowSlidingQueue.getWindowMax(arr, 3);
        System.out.println(Arrays.toString(windowMax));


    }

}
