package com.sunjinwei.test._01_stack_queue;

import java.util.LinkedList;

/**
 * 子数组的最大值减去最小值小于等于num，求有多少个子数组
 * 思路：使用单调队列
 */
public class _08_Get_Num {

    /**
     * 滑动窗口+左右指针
     *
     * @param arr
     * @param num
     * @return
     */
    public int getNum(int[] arr, int num) {
        // 单调递增队列 队头为最大值
        LinkedList<Integer> maxQueue = new LinkedList<>();
        // 单调递减队列 队头为最小值
        LinkedList<Integer> minQueue = new LinkedList<>();
        // 子数组的第一个索引 左指针
        int i = 0;
        // 子数组的第二个索引 右指针
        int j = 0;
        // 子数组的个数
        int res = 0;
        // 1i和j都需要小于数组的长度
        // arr[i..j]
        while (i < arr.length) {
            while (j < arr.length) {
                // 1维持两个队列的单调性
                if (minQueue.isEmpty() || minQueue.peekLast() != j) {
                    // 维持最小值的单调递减
                    while (!minQueue.isEmpty() && arr[minQueue.peekLast()] >= arr[j]) {
                        minQueue.pollLast();
                    }
                    minQueue.addLast(j);
                    // 维持最大值的单调递增
                    while (!maxQueue.isEmpty() && arr[maxQueue.peekLast()] <= arr[j]) {
                        maxQueue.pollLast();
                    }
                    maxQueue.addLast(j);
                }
                // 2判断是否满足条件：最大值和最小值相减是否小于等于num
                if (arr[maxQueue.peekFirst()] - arr[minQueue.peekFirst()] > num) {
                    break;
                }
                // 3满足条件 让j继续向右移动
                j++;
            }
            // 4计算此时满足条件的子数组个数 [i..j] [i..j-1] [i..j-2]
            res += j - i;
            // 5j已经不满足条件，让i向右移动
            if (minQueue.peekFirst() == i) {
                minQueue.pollFirst();
            }
            if (maxQueue.peekFirst() == i) {
                maxQueue.pollFirst();
            }
            i++;
        }
        return res;
    }


}
