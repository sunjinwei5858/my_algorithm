package com.sunjinwei._04_dp;

import java.util.Arrays;

/**
 * 完全背包：
 * 力扣322 零钱兑换I（中等）：凑出目标金额的最少硬币数
 * 力扣518 零钱兑换II（中等）：凑出目标金额的方式
 * <p>
 * 这两道题和爬楼梯非常相似
 * <p>
 * 转化为背包问题：
 * 背包的重量为amount，有一系列物品coins，每个物品的重量为coins[i]，每个物品的数量无限
 * I: 最少的硬币数 能够把背包装满
 * II: 请问有多少种方法，能够把背包恰好填满
 */
public class _05_wanquan_beibao_coins_change_I {

    /**
     * 零钱兑换I 方法一： 一维dp
     * <p>
     * 给定不同面额的硬币coins和一个总金额amount。
     * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
     * 如果没有任何一种硬币组合能组成总金额，返回-1。
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChangeI(int[] coins, int amount) {
        // 状态定义：当目标金额为i时，至少需要dp[i]枚硬币凑出
        int[] dp = new int[amount + 1];
        // base case 初始化
        // 初始化1：这里要填充为很大的值
        Arrays.fill(dp, amount + 1);
        // 初始化2：这里是为0
        dp[0] = 0;
        // 硬币在外层循环 这种方式处理的比较多 更好理解
        // 因为是完全背包 这里和排列 组合 没有关系 所以两种内外循环选择都可以
        for (int coin : coins) {
            for (int j = 1; j <= amount; j++) {
                if (coin <= j) {
                    int s1 = dp[j];
                    int s2 = 1 + dp[j - coin];
                    dp[j] = Math.min(s1, s2);
                }
            }
        }
        if (dp[amount] == amount + 1) {
            return -1;
        }
        return dp[amount];
    }

    /**
     * 零钱兑换I 方法二： 暴力递归
     */
    private int result = Integer.MAX_VALUE;

    public int coinChangeI_02(int[] coins, int amount) {

        // 辅助函数
        findWay(coins, amount, 0);

        if (result == Integer.MAX_VALUE) {
            return -1;
        }
        return result;
    }

    private void findWay(int[] coins, int amount, int count) {
        if (amount < 0) {
            return;
        }
        if (amount == 0) {
            result = Math.min(result, count);
        }
        for (int coin : coins) {
            findWay(coins, amount - coin, count + 1);
        }
    }


}
