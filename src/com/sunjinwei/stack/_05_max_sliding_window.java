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
     * 方法二：单调队列，左神思路，队列存储的是索引
     * <p>
     * 执行用时：36 ms, 在所有 Java 提交中击败了53.34%的用户
     * 内存消耗：50.1 MB, 在所有 Java 提交中击败了77.85%的用户
     * <p>
     * 满足的条件：
     * 1。保证单调性，队尾和当前元素相比较
     * 2。保证队头的元素是窗口中的元素
     * 3。保证刚好是窗口大小时，队首就是最大值
     *
     * @param nums 整型数组
     * @param k    窗口大小
     * @return 滑动窗口的最大值数组
     */
    public int[] maxSlidingWindow_02(int[] nums, int k) {
        // 鲁棒性
        if (nums == null || nums.length == 0 || k < 1) {
            return null;
        }
        // 双向队列 保存当前窗口最大值的数组位置 保证队列中数组位置的数值按从大到小排序
        LinkedList<Integer> queue = new LinkedList();
        // 结果数组 也就是滑动窗口保存最大值的数组 长度就是 原数组长度-窗口长度+1 即有多少个窗口
        int[] result = new int[nums.length - k + 1];
        // 遍历nums数组
        for (int i = 0; i < nums.length; i++) {
            // 关键步骤1：保证单调性
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) {
                queue.pollLast();
            }
            // 添加数组下标
            queue.addLast(i);
            // 关键步骤2：保证队列中的元素是窗口中的元素 其实就是判断队头的元素
            if (queue.peek() <= (i - k)) {
                queue.poll();
            }
            // 关键步骤3：当窗口长度为k时 保存当前窗口中最大值
            // i-k+1是递增的
            if (i - k + 1 >= 0) {
                result[i - k + 1] = nums[queue.peek()];
            }
        }
        return result;
    }

    /**
     * 方法三：单调队列的第二种写法，写法二，力扣题解
     * <p>
     * 执行用时：30 ms, 在所有 Java 提交中击败了90.40%的用户
     * 内存消耗：49.7 MB, 在所有 Java 提交中击败了82.15%的用户
     * <p>
     * 思路：
     * 思路其实和写法一，是一样的，只不过这里是先构造出了第一个滑动窗口，并且队列保存的是值，写法一保存的是索引
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

    /**
     * 方法四：抽取了一个单调队列
     * <p>
     * 执行用时：7 ms, 在所有 Java 提交中击败了89.43%的用户
     * 内存消耗：47.4 MB, 在所有 Java 提交中击败了23.01%的用户
     */
    private SingleDeque singleDeque;

    public int[] maxSlidingWindow_04(int[] nums, int k) {
        // 鲁棒性
        if (nums == null || nums.length < 2 || k < 1) {
            return new int[0];
        }
        singleDeque = new SingleDeque();
        // 窗口数组
        int[] result = new int[nums.length - k + 1];
        // 第一个窗口
        for (int i = 0; i < k; i++) {
            singleDeque.push(nums[i]);
        }
        // 将第一个窗口的最大值放入数组
        int index = 0;
        result[index] = singleDeque.max();
        index++;
        // 处理：剩余的窗口
        for (int i = k; i < nums.length; i++) {
            // 判断队列的头部元素是不是在窗口中
            singleDeque.pop(nums[i - k]);
            singleDeque.push(nums[i]);
            result[index] = singleDeque.max();
            index++;
        }
        return result;
    }

    public static void main(String[] args) {
        _05_max_sliding_window window_max = new _05_max_sliding_window();
        int[] arr = new int[]{4, 8, 5, 4, 3, 3, 6, 7};
        int[] ints = window_max.maxSlidingWindow_03(arr, 3);
        System.out.println(Arrays.toString(ints));
    }
}
