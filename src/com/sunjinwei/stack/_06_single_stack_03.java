package com.sunjinwei.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * 单调栈：503 下一个更大元素II 难度：中等
 * <p>
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），
 * 输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，
 * 这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 */
public class _06_single_stack_03 {

    /**
     * 循环数组：最后一个元素的下一个元素是数组的第一个元素
     * 计算机的内存都是线性的，没有真正意义的环形数组，但是可以模拟环形数组的效果，
     * 一般是通过%运算符求模，获得环形特效
     * 取模运算：a % p（或a mod p），表示a除以p的余数
     * <p>
     * 执行用时：8 ms, 在所有 Java 提交中击败了84.89%的用户
     * 内存消耗：40.2 MB, 在所有 Java 提交中击败了56.99%的用户
     *
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        // 1循环数组：使用位运算 % 来解决
        int[] result = new int[nums.length];
        // 2声明栈
        Stack<Integer> stack = new Stack<>();
        // 填充
        Arrays.fill(result, -1);
        for (int i = 0; i < 2 * nums.length; i++) {
            // 使用 取模 % 模拟循环数组的索引
            int index = i % nums.length;
            while (!stack.isEmpty() && nums[index] > nums[stack.peek()]) {
                result[stack.pop()] = nums[index];
            }
            // 将第一个数组的索引进栈 循环数组的第二个数组的索引不需要进栈
            // 也可以直接push(index)
            if (i < nums.length) {
                stack.push(i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{100, 1, 11, 1, 120, 111, 123, 1, -1, -100};
        _06_single_stack_03 stack_ii = new _06_single_stack_03();
        int[] elements = stack_ii.nextGreaterElements(arr);
        System.out.println(Arrays.toString(elements));
    }
}
