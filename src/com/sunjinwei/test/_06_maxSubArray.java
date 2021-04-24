package com.sunjinwei.test;

/**
 * 53. 最大子序和
 * <p>
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 */
public class _06_maxSubArray {

    /**
     * 动态规划解法
     * 如何得出解题思路：数学归纳法
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 状态定义：dp[i]以nums[i]结尾的子数组最大和 该子数组可以是nums[i]本身或者dp[i-1]+nums[i]
        // 总之：dp[i]中的子数组肯定包括nums[i] 从而推出dp[i-1]中的子数组也包括nums[i-1]
        // 所以 dp[i-1]+nums[i] 这样计算出来的值也是连续的
        int[] dp = new int[nums.length];
        // 初始化
        dp[0] = nums[0];
        // 处理
        // 理解：dp[i]不一定比dp[i-1]更大
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            // 处理dp数组
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            // 找到和的最大值
            max = Math.max(max, dp[i]);
        }
        return max;

    }

    public static void main(String[] args) {
        _06_maxSubArray maxSubArray = new _06_maxSubArray();
        maxSubArray.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4, 1});

    }
}
