package com.sunjinwei._07_heap;

import java.util.PriorityQueue;

/**
 * 数据流中的第k大元素 力扣703
 * <p>
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第k大元素，不是第k个不同的元素。
 * 请实现 KthLargest 类：
 * <p>
 * KthLargest(int k, int[] nums) 使用整数k和整数流nums初始化对象。
 * int add(int val) 将val插入数据流nums后，返回当前数据流中第k大的元素。
 * 题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素
 * <p>
 * 思路：
 * 要找到第k大元素 那么直接使用二叉堆 java的优先级队列 堆顶放最小值
 * <p>
 * 重构：这里的add不需要进行扩容了，需要将该元素放入到数组当中了
 */
public class _01_kth_largest_refactor {

    private int k;

    private PriorityQueue<Integer> priorityQueue;

    public _01_kth_largest_refactor(int k, int[] nums) {
        this.k = k;
        // 直接使用自然排序 堆顶存储最小值
        this.priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);
        }
    }

    /**
     * add方法中，不需要将元素放入到数组当中了，不需要进行扩容了，所以add方法可以放在构造函数中调用
     *
     * @param val
     * @return
     */
    public int add(int val) {
        if (priorityQueue.size() < k) {
            priorityQueue.offer(val);
            return priorityQueue.peek();
        }
        if (priorityQueue.peek() < val) {
            priorityQueue.poll();
            priorityQueue.offer(val);
        }
        return priorityQueue.peek();
    }

}
