package com.sunjinwei._07_heap;

import java.util.PriorityQueue;

/**
 * 堆相关题目：左神
 * 已知一个几乎有序的数组，几乎有序是指：如果把数组排好顺序的话，每个元素移动的距离一定不超过k，并且k相对于数组来说是比较小的
 * 请选择一个合适的排序策略，对这个数组进行排序。
 * 思路：
 * 第一种：使用小根堆 直接使用jdk提供的优先级队列，第一次先放k+1个元素，然后往右移动
 * 第二种：直接把所有元素都放入堆中 但是这样没有利用到几乎有序的条件
 */
public class _03_sort_arr_distance_less_k {

    public void sortArrDistanceLessK(int[] arr, int k) {
        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        // 如果arr的长度小于k 那么取arr的长度
        // 第一次先把[0,k]位置的元素放入小根堆，这样堆里面元素其实是k+1个
        for (; index <= Math.min(arr.length - 1, k); index++) {
            heap.add(arr[index]);
        }
        // 第二步：从小根堆中弹出一个数 然后再从后面的元素加入到堆中
        int i = 0;
        // 其实这里弹出和加入的顺序谁在前面谁在后面都可以 因为题目说了几乎有序 每个元素移动的距离一定不超过k
        for (; i < arr.length; i++, index++) {
            // 弹出
            arr[i] = heap.poll();
            // 加入
            heap.add(arr[index]);
        }
        // 如果堆中还有元素 那么继续弹出
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }
}
