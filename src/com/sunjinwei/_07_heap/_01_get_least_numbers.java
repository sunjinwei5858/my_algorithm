package com.sunjinwei._07_heap;

import java.util.PriorityQueue;

/**
 * 最小的k个数   剑指
 * 思路：
 * 暴力法：先对数组进行排序，然后取前k个即可 这种方式自己不再多说
 * 快排思想: 比k小的都放在index=k的左边
 * 最大堆：使用优先级队列，最大的元素位于堆顶, O(1)时间获取最大值，需要掌握PriorityQueue，要使最大值在队列的头部 需要传入比较器
 */
public class _01_get_least_numbers {


    /**
     * 最大堆实现：这种方式也可以用于海量数据中找最小的k个数，或者找最小值，
     * 因为只需要堆在内存即可，不需要磁盘上的海量数据全部加载到内存
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        // 鲁棒性
        if (arr == null || arr.length == 0 || k <= 0) {
            return new int[0];
        }
        // 使用优先级队列 底层 默认就是 最大堆
        // 使用lambda表达式 降序
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);

        // 将数组前k个元素放入最大堆中
        for (int i = 0; i < k; i++) {
            priorityQueue.offer(arr[i]);
        }
        // 将数组后面的元素进行处理：
        // 当前元素比最大堆的小 那么最大堆弹出 将当前元素放入最大堆
        for (int i = k; i < arr.length; i++) {
            if (priorityQueue.peek() > arr[i]) {
                priorityQueue.poll();
                priorityQueue.offer(arr[i]);
            }
        }
        // 声明结果集数组
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = priorityQueue.poll();
        }
        return res;
    }
}
