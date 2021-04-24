package com.sunjinwei.test;

import java.util.PriorityQueue;

/**
 * 215. 数组中的第K个最大元素
 * <p>
 * 在未排序的数组中找到第k个最大的元素。请注意，你需要找的是数组排序后的第k个最大的元素，而不是第k个不同的元素。
 * <p>
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 */
public class _05_findKthLargest {

    public int findKthLargest(int[] nums, int k) {
        // 使用优先级队列 最小堆实现
        // 最小堆的堆顶就是队列元素中的最小值 保证优先级队列的size为k

        // 1鲁棒性
        // 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。

        // 2优先级队列
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        // 3保证有k个元素已经入队列
        for (int i = 0; i < k; i++) {
            priorityQueue.add(nums[i]);
        }
        // 4继续比较 如果当前元素比堆顶元素大 那么弹出堆顶元素 并且将当前元素入队列
        for (int i = k; i < nums.length; i++) {
            if (priorityQueue.peek() < nums[i]) {
                priorityQueue.poll();
                priorityQueue.add(nums[i]);
            }
        }
        return priorityQueue.peek();
    }

}
