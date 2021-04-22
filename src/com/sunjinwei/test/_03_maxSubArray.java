package com.sunjinwei.test;

/**
 * 53 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * <p>
 * 分析题意：
 * 关键字：连续
 */
public class _03_maxSubArray {

    /**
     * 动态规划
     * dp[i]定义：以nums[i]结尾的连续子数组最大和
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        // 状态定义: 以nums[i]结尾的连续子数组的最大和
        // 数学归纳法：知道了dp[i-1] 如何知道dp[i]
        // 有两种情况 一种是nums[i] 一种是nums[i]+dp[n-1]
        int[] dp = new int[nums.length];
        // 初始化数据 第一个元素 那么最大和就是该元素的值
        dp[0] = nums[0];
        // 处理
        for (int i = 1; i < nums.length; i++) {
            // 考虑nums[i] 单独成为一段还是加入f(i-1)对应的那一段
            // 这里比较的是nums[i]和dp[i-1]+nums[i] 取最大
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }
        int max = Integer.MIN_VALUE;
        for (int i : dp) {
            max = Math.max(i, max);
        }
        return max;
    }
}
