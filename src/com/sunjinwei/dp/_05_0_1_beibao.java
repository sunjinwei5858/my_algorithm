package com.sunjinwei.dp;

/**
 * 0-1背包问题 [物品可以装进背包也可以不装进背包]
 * 描述：给你一个可装载重量为w的背包和n个物品，每个物品有重量和价值两个属性
 * 第i个物品的重量为wt[i] 价值为val[i]，求最大价值
 */
public class _05_0_1_beibao {

    /**
     * @param w   背包的重量
     * @param n   可选择的物品数量
     * @param wt  物品对应的重量
     * @param val 物品对应的价值
     * @return
     */
    public int beibao(int w, int n, int[] wt, int[] val) {
        // 状态定义：[i][j]代表在重量为j的背包中放入i个物品的最大价值
        int[][] dp = new int[n + 1][w + 1];
        // base case
        // 不放入物品的时候 价值为0 不需要初始化了
        for (int i = 1; i <= n; i++) { // 代表可选择的物品
            for (int j = 1; j <= w; j++) { // 代表背包重量
                // 如果物品重量大于背包重量 不能放物品 此时最大价值就是第i-1个物品的最大价值
                // 索引是从1开始 那么i的重量就是wt[i-1]
                if (j < wt[i - 1]) {
                    // 放i-1个物品，背包容量为j的最大价值
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 如果物品重量小于背包问题，可以放物品，价值需要和不放这个物品的价值比较
                    // 因为i的索引是从1开始的 所以对于i的重量和价值 应该是wt[i-1]和val[i-1]
                    // [i - 1][j - wt[i - 1]]: 如果装了第i个物品 那么可能需要重新调整 需要找到减去i的重量的最大价值再加上物品i的价值
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - wt[i - 1]] + val[i - 1]);
                }
            }
        }
        return dp[n][w];
    }

}
