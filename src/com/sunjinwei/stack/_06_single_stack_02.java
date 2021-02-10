package com.sunjinwei.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * 单调栈: 下一个更大的元素I 496 难度：简单
 * <p>
 * 给你两个没有重复元素的数组nums1和nums2，其中nums1是nums2的子集。
 * 请你找出nums1中每个元素在nums2中的下一个比其大的值。
 * nums1中数字x的下一个更大元素是指x在nums2中对应位置的右边的第一个比x大的元素。如果不存在，对应位置输出-1 。
 * <p>
 * 示例 1:
 * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出: [-1,3,-1]
 * 解释:
 * 对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出-1 。
 * 对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是3 。
 * 对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1 。
 */
public class _06_single_stack_02 {

    /**
     * 方法一：倒序遍历 不太容易理解
     * <p>
     * 执行用时：5ms, 在所有Java提交中击败了81.73%的用户
     * 内存消耗：38.7MB, 在所有Java提交中击败了37.36%的用户
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // 先解决第二个数组的问题 因为nums1是nums2的子集
        int[] temp = new int[nums2.length];
        Stack<Integer> stack = new Stack<>();
        // 使用hashmap进行存储结果集
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                temp[i] = -1;
            } else {
                temp[i] = stack.peek();
            }
            map.put(nums2[i], temp[i]);
            stack.push(nums2[i]);
        }
        int[] result = new int[nums1.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = map.get(nums1[i]);
        }
        return result;
    }

    /**
     * 方法二: 正序遍历+hashmap存储,比较好理解
     * <p>
     * 执行用时：6 ms, 在所有 Java 提交中击败了40.18%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了65.74%的用户
     *
     * @param nums1
     * @param nums2
     */
    public int[] nextGreaterElement_02(int[] nums1, int[] nums2) {
        // 先解决第二个数组的问题 因为nums1是nums2的子集
        Stack<Integer> stack = new Stack<>();
        // 使用hashmap进行存储结果集
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            // 只要当前元素大于栈顶元素 说明栈顶元素的下一个最大值就是当前元素 弹出
            while (!stack.isEmpty() && nums2[i] > stack.peek()) {
                map.put(stack.pop(), nums2[i]);
            }
            // 将当前元素进栈
            stack.push(nums2[i]);
        }
        int[] result = new int[nums1.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = map.getOrDefault(nums1[i], -1);
        }
        return result;
    }

    public static void main(String[] args) {
        _06_single_stack_02 stack_i = new _06_single_stack_02();
        int[] first = {4, 1, 2};
        int[] second = {1, 3, 4, 2};

        int[] ints = stack_i.nextGreaterElement_02(first, second);
        System.out.println(Arrays.toString(ints));


    }
}

