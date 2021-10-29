package com.sunjinwei._07_heap;

import java.util.PriorityQueue;

/**
 * 数据流中的第k大元素 力扣703
 * <p>
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第k大元素，不是第k个不同的元素。
 * 请实现 KthLargest 类：
 * <p>
 * KthLargest(int k, int[] nums) 使用整数k和整数流nums初始化对象。
 * int addLast(int val) 将val插入数据流nums后，返回当前数据流中第k大的元素。
 * 题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素
 * <p>
 * 思路：
 * 要找到第k大元素 那么直接使用二叉堆 java的优先级队列 堆顶放最小值
 */
public class _01_get_largest_k {

    private int k;

    private int[] nums;

    private PriorityQueue<Integer> priorityQueue;

    public _01_get_largest_k(int k, int[] nums) {
        this.k = k;
        this.nums = nums;
        // 直接使用自然排序 堆顶存储最小值
        this.priorityQueue = new PriorityQueue<>();
        // 如果当前数组的长度小于k 那么直接添加到队列
        if (nums.length < k) {
            for (int i = 0; i < nums.length; i++) {
                priorityQueue.offer(nums[i]);
            }
            return;
        }
        // 走到这里 说明长度大于k
        for (int i = 0; i < k; i++) {
            priorityQueue.offer(nums[i]);
        }
        // 将数组第k个之后的元素进行处理
        // 如果当前元素比堆顶最小值大 那么入队列
        for (int i = k; i < nums.length; i++) {
            if (priorityQueue.peek() < nums[i]) {
                priorityQueue.poll();
                priorityQueue.offer(nums[i]);
            }
        }
    }

    public int add(int val) {
        int length = nums.length;
        // 扩容
        nums = new int[length + 1];
        // 将val存储到数组
        nums[length] = val;
        if (length < k) {
            priorityQueue.offer(val);
            return priorityQueue.peek();
        }
        if (priorityQueue.peek() < val) {
            priorityQueue.poll();
            priorityQueue.offer(val);
        }
        // 第k大 也就是最小堆的堆顶 最小值
        return priorityQueue.peek();

    }

}
