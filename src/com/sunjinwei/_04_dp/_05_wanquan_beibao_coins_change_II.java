package com.sunjinwei._04_dp;

/**
 * 完全背包：
 * 力扣322 零钱兑换I（中等）：凑出目标金额的最少硬币数
 * 力扣518 零钱兑换II（中等）：凑出目标金额的方式
 *
 * 这两道题和爬楼梯非常相似
 *
 * 转化为背包问题：
 * 背包的重量为amount，有一系列物品coins，每个物品的重量为coins[i]，每个物品的数量无限
 * I: 最少的硬币数 能够把背包装满
 * II: 请问有多少种方法，能够把背包恰好填满
 */
public class _05_wanquan_beibao_coins_change_II {

    /**
     * 零钱兑换II 解法一： 二维的动态规划
     *
     * @param amount
     * @param coins
     * @return
     */
    public int changeII(int amount, int[] coins) {
        int n = coins.length;
        // 状态定义：[i][j]表示前i个物品，恰好填满重量为j的背包的方法
        int[][] dp = new int[n + 1][amount + 1];
        // base case 状态初始化
        // 重量为0时 那么不放入硬币 所以也是一种方法 无为而治也是一种解法
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        // 物品为0 金额为0 无为而治
        dp[0][0] = 1;
        // 状态转移方程
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                // 如果硬币的面额大于amount 那么不需要放入背包
                // 此时填满重量为j的背包的方法为[i-1][j]
                if (coins[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 如果硬币的面额小于amount 可以放入背包
                    // 如果该物品放入背包 那么还需要考虑的是 凑出剩余背包重量的方法 也就是[j-coins[i-1]]
                    int s1 = dp[i][j - coins[i - 1]];
                    // 如果不放入背包
                    int s2 = dp[i - 1][j];
                    // 因为是完全背包 每个物品可以使用无数次 所以两种情况需要相加
                    dp[i][j] = s1 + s2;
                }
            }
        }
        return dp[n][amount];
    }

    /**
     * 零钱兑换II 解法二：一维
     */
    public int changeII_02(int amount, int[] coins) {
        int n = coins.length;
        // 状态定义：恰好填满重量为j的背包的方法
        int[] dp = new int[amount + 1];
        // base case 状态初始化 默认都是0 当i=0 即重量为0 那么不放入硬币 无为而治也是一种解法
        dp[0] = 1;
        // 状态转移方程
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                // 如果硬币的面额大于amount 那么不需要放入背包
                if (coins[i - 1] <= j) {
                    // 如果硬币的面额小于amount 可以放入背包
                    // 如果放入背包 那么还需要考虑的是 凑出剩余背包重量的方法 也就是[j-coins[i-1]]
                    int s1 = dp[j - coins[i - 1]];
                    // 如果不放入背包
                    int s2 = dp[j];
                    // 因为是完全背包 每个物品可以使用无数次 所以两种情况需要相加
                    dp[j] = s1 + s2;
                }
            }
        }
        return dp[amount];
    }

}
