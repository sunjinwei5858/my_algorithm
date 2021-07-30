package com.sunjinwei._04_dp02;

/**
 * 是否可以分隔等和子集【力扣416】【类比火柴拼正方形 这道题是判断两个数组的和 火柴是判断四个数组的和】
 * 给你一个只包含正整数的非空数组nums。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 */
public class _03_Arr_Can_Partition {

    /**
     * 动态规划解决：其实就是背包问题，转化为0-1背包
     *
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        // 判断数组的和是奇数还是偶数
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        // 使用位运算 & 来判断奇偶性
        // 奇数直接返回false 偶数返回true
        if ((sum & 1) == 1) {
            return false;
        }
        // 背包重量 其实就是sum/2
        int w = sum >> 1;
        // 状态定义：dp[i][j] 第i个物品放入重量为j的背包中的最大价值
        int[][] dp = new int[nums.length + 1][w + 1];
        // 处理
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= w; j++) {
                if (j < nums[i - 1]) {
                    // 背包容量不够
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 背包容量够 可放可不放
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i - 1]] + nums[i - 1]);
                }
            }
        }
        // 判断背包最大是否能存放和为target的元素
        return dp[nums.length][w] == w;
    }

    /**
     * 动态规划解决：其实就是背包问题，转化为0-1背包 另一种解法 将dp int二维数组转为boolean数组
     *
     * @param nums
     * @return
     */
    public boolean canPartition2(int[] nums) {
        // 判断数组的和是奇数还是偶数
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        // 使用位运算 & 来判断奇偶性
        // 奇数直接返回false 偶数返回true
        if ((sum & 1) == 1) {
            return false;
        }
        // 背包重量 其实就是sum/2
        int w = sum >> 1;
        // 状态定义：dp[i][j] 前i个物品加起来的和是否等于j
        boolean[][] dp = new boolean[nums.length + 1][w + 1];
        // 初始化
        dp[0][0] = true;
        // 处理
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= w; j++) {
                if (j < nums[i]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - nums[i - 1]] || dp[i - 1][j - 1];
                }
            }
        }
        // 判断背包最大是否能存放和为w的元素
        return dp[nums.length][w];
    }


}
