package com.sunjinwei._04_dp;

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
public class _05_wanquan_beibao_coins_change_II {

    /**
     * 零钱兑换II 一维
     */
    public int changeII_02(int amount, int[] coins) {
        int n = coins.length;
        // 状态定义：恰好填满重量为j的背包的方法
        int[] dp = new int[amount + 1];
        // base case 状态初始化 默认都是0 当i=0 即重量为0 那么不放入硬币 无为而治也是一种解法
        dp[0] = 1;
        // 状态转移方程
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= amount; j++) {
                if (coins[i] <= j) {
                    int s1 = dp[j - coins[i]];
                    int s2 = dp[j];
                    dp[j] = s1 + s2;
                }
            }
        }
        return dp[amount];
    }

    private int count = 0;

    /**
     * 回溯法：就是会超时
     *
     * @param amount
     * @param coins
     * @return
     */
    public int changeII3(int amount, int[] coins) {

        process(amount, coins, 0);
        return count;
    }

    private void process(int amount, int[] coins, int start) {
        if (amount == 0) {
            count++;
            return;
        }
        if (amount < 0) {
            return;
        }
        for (int i = start; i < coins.length; i++) {
            process(amount - coins[i], coins, i);
        }
    }

}
