package com.sunjinwei.stack;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 窗口滑动：
 * 1。有一个整型数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次向右边滑一个位置
 * 2。如果arr数组长度为n，窗口大小为w，则一共产生n-w+1个窗口的最大值
 * 请实现一个函数：
 * 输入：整型数组arr，窗口大小为w
 * 输出：一个长度为n-w+1的数组res，res[i]表示每一种窗口状态下的最大值
 * 要求：时间复杂度为O(n)
 * <p>
 * 联想：
 * 窗口的最大值，想到java的优先级队列，默认实现是小顶堆 最小值保存在顶部位置，但是这道题窗口是滑动的，所以需要使用单调队列，可以进行删除队列中的元素
 */
public class _05_max_sliding_window {

    /**
     * 方法一：暴力解法 时间复杂度O(N*W)，思路简单易懂 面试时使用此解法 容易回去等结果
     * 执行用时：37 ms, 在所有 Java 提交中击败了13.98%的用户
     * 内存消耗：46.4 MB, 在所有 Java 提交中击败了74.28%的用户
     *
     * @param nums 整型数组
     * @param k    窗口大小
     * @return 滑动窗口的最大值数组
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 鲁棒性
        if (nums == null || nums.length == 0 || k < 1) {
            return null;
        }
        // 声明滑动窗口的长度
        int windowLength = nums.length - k + 1;
        // 滑动窗口数组
        int[] result = new int[nums.length - k + 1];
        int left = 0;
        int right = k;
        int count = 0;
        while (right <= nums.length && count <= windowLength) {
            int max = Integer.MIN_VALUE;
            for (int i = left; i < right; i++) {
                max = Math.max(nums[i], max);
            }
            result[count] = max;
            count++;
            left++;
            right++;
        }
        return result;
    }

    /**
     * 方法二：使用双端队列，即单调队列，写法一
     *
     * @param nums 整型数组
     * @param k    窗口大小
     * @return 滑动窗口的最大值数组
     */
    public int[] maxSlidingWindow_02(int[] nums, int k) {
        // 鲁棒性
        if (nums == null || nums.length < 2) {
            return nums;
        }
        // 双向队列 保存当前窗口最大值的数组位置 保证队列中数组位置的数值按从大到小排序
        LinkedList<Integer> queue = new LinkedList();
        // 结果数组 也就是滑动窗口保存最大值的数组 长度就是 原数组长度-窗口长度+1 即有多少个窗口
        int[] result = new int[nums.length - k + 1];
        // 遍历nums数组
        for (int i = 0; i < nums.length; i++) {
            // 保证从大到小 如果前面数小则需要依次弹出，直至满足要求
            // 目的就是维护一个单调递减的队列!!!!
            // 关键步骤1
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();
            }
            // 添加当前值对应的数组下标
            queue.addLast(i);
            // 判断当前队列中队首的值是否有效 也就是保证队列中的元素是窗口中的元素 不然需要推出!!!! 必不可少的判断
            // 关键步骤2
            if (queue.peek() <= i - k) {
                queue.poll();
            }
            // 当窗口长度为k时 保存当前窗口中最大值
            // 关键步骤3
            if (i + 1 >= k) {
                result[i + 1 - k] = nums[queue.peek()];
            }
        }
        return result;
    }

    /**
     * 方法三：单调队列的第二种写法，写法二
     * <p>
     * 执行用时：30 ms, 在所有 Java 提交中击败了90.40%的用户
     * 内存消耗：49.7 MB, 在所有 Java 提交中击败了82.15%的用户
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow_03(int[] nums, int k) {
        int len = nums.length;
        if (len == 0) {
            return nums;
        }
        int[] arr = new int[len - k + 1];
        int arr_index = 0;
        // 我们需要维护一个单调递增的双向队列
        Deque<Integer> deque = new LinkedList<>();
        // 先将第一个窗口的值按照规则入队
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
                deque.removeLast();
            }
            deque.offerLast(nums[i]);
        }
        // 存到数组里，队头元素
        arr[arr_index] = deque.peekFirst();
        arr_index++;
        // 移动窗口
        for (int j = k; j < len; j++) {
            // 如果窗口的前一个元素等于队头元素 那么移除队头 保证队列元素是窗口的元素 移除队列中不属于窗口中的元素
            // 关键步骤1
            if (nums[j - k] == deque.peekFirst()) {
                deque.pollFirst();
            }
            // 关键步骤2  因为有了关键步骤1的铺垫 这里保证了队列的元素都是窗口的元素
            while (!deque.isEmpty() && deque.peekLast() < nums[j]) {
                deque.pollLast();
            }
            deque.offerLast(nums[j]);
            // 为什么这里不需要再判断j是不是已经是窗口了，因为第一个for循环已经构造出了第一个窗口，所以这里递进必然保证是一个完整的窗口
            arr[arr_index] = deque.peekFirst();
            arr_index++;
        }
        return arr;
    }

    public static void main(String[] args) {
        _05_max_sliding_window window_max = new _05_max_sliding_window();
        int[] arr = new int[]{4, 8, 5, 4, 3, 3, 6, 7};
        int[] ints = window_max.maxSlidingWindow_03(arr, 3);
        System.out.println(Arrays.toString(ints));
    }
}
