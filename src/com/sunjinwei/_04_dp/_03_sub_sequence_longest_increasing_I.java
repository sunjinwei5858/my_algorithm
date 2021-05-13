package com.sunjinwei._04_dp;

import java.util.Arrays;

/**
 * 最长递增子序列【LIS】 力扣300
 * 给定一个无序的整数数组，找到其中最长递增子序列的长度。
 * 思想：主要学习 动态规划的核心思想-数学归纳法
 */
public class _03_sub_sequence_longest_increasing_I {

    /**
     * 非常规的动态规划
     * 状态定义：dp数组，_04_dp[i]表示以num[i]这个数结尾的最长递增子序列的长度，这是解决子序列问题的一个套路,一维。
     * 状态转移方程：数学归纳法 假设[0,..,i-1]已经算出来了，那么dp[i]:看num[i]和num[i-1]的大小，
     * 如果num[i]比num[i-1]更大，那么dp[i]=max(_04_dp[i],_04_dp[i-1]+1)
     * 如果num[i]比num[i-1]更小，直接跳过，因为递增序列不成立
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        // 状态定义：dptable 一维 ，以num[i]这个数结尾的最长递增子序列的长度
        int[] dpTable = new int[nums.length];
        // base case：默认填充1，每个元素都可以都至少可以单独成为子序列
        Arrays.fill(dpTable, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dpTable[i] = Math.max(dpTable[i], dpTable[j] + 1);
                }
            }
        }
        // 找出dptable数组的最大值
        int result = 0;
        for (int i = 0; i < dpTable.length; i++) {
            result = Math.max(result, dpTable[i]);
        }
        return result;
    }

    /**
     * 优化写法
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS02(int[] nums) {
        // 状态定义：dptable 一维 ，_04_dp[i]的值代表nums前i个数字的最长子序列长度
        int[] dpTable = new int[nums.length];
        // 初始状态：默认填充1，每个元素都可以都至少可以单独成为子序列
        Arrays.fill(dpTable, 1);
        // 找出dptable数组的最大值
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dpTable[i] = Math.max(dpTable[i], dpTable[j] + 1);
                }
            }
            result = Math.max(result, dpTable[i]);
        }
        return result;
    }
}
