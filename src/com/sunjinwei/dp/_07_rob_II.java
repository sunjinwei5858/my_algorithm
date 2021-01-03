package com.sunjinwei.dp;

import java.util.Arrays;

/**
 * 打家劫舍II 力扣213 难度：中等 环形排列 打家劫舍I的变种
 * 描述：
 * 你是一个专业的小偷，计划偷窃沿街的房屋。
 * 每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 增加条件：这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你不触动警报装置的情况下，一夜之内能够偷窃到的最高金额。
 */
public class _07_rob_II {

    /**
     * 动态规划解法
     * 思路：将环形排列转换成两个线性排列!!!!! 这个技巧太66666
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        // 特殊条件1
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 特殊条件2
        if (nums.length == 1) {
            return nums[0];
        }
        // 将环形排列转换成两个单列
        // 比如[1,10]拆成[1,9]和[2,10] 去比较这两个的最大值
        int[] nums01 = Arrays.copyOfRange(nums, 0, nums.length - 1);
        int[] nums02 = Arrays.copyOfRange(nums, 1, nums.length);
        int s1 = rob_help(nums01);
        int s2 = rob_help(nums02);
        return Math.max(s1, s2);

    }

    public int rob_help(int[] nums) {
        // 状态定义：dp[i]代表 数组长度为i的最高金额
        int m = nums.length;
        int[] dp = new int[m];
        // base case 状态初始化
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        // 状态转移方程
        for (int i = 2; i < m; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[m - 1];
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 9, 1, 3};
        int[] nums01 = Arrays.copyOfRange(nums, 0, nums.length - 1);
        int[] nums02 = Arrays.copyOfRange(nums, 1, nums.length);

        System.out.println(Arrays.toString(nums01));
        System.out.println(Arrays.toString(nums02));

    }
}
