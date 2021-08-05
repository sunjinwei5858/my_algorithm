package com.sunjinwei._04_dp02;

/**
 * 买卖股票 【力扣121 简单】【只能买卖一次】
 * <p>
 * 给定一个数组prices，它的第i个元素prices[i]表示一支给定股票第i天的价格。
 * 你只能选择某一天买入这只股票，并选择在未来的某一个不同的日子卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 */
public class _04_Stock_Max_Profit_I {

    /**
     * 方法1：暴力解法，超出时间限制
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {

        if (prices == null || prices.length == 0) {
            return 0;
        }
        int n = prices.length;
        int[] dp = new int[n + 1];
        int a = 0;
        int res = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                a = prices[j] - prices[i];
                dp[i] = Math.max(a, dp[i]);
            }
            res = Math.max(dp[i], res);
        }
        return res;
    }

    /**
     * 解法2：贪心
     * 左边取最小，右边取最大，两者差值就是最大利润
     * <p>
     * 执行用时：3 ms, 在所有 Java 提交中击败了53.50%的用户
     * 内存消耗：51.3 MB, 在所有 Java 提交中击败了61.27%的用户
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {

        if (prices == null || prices.length == 0) {
            return 0;
        }
        // 记录今天买入的最小值
        int min = prices[0];
        // 每天的最大获利
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            // 更新股票的最大获利
            max = Math.max(max, prices[i] - min);
            // 更新股票的最小值
            min = Math.min(min, prices[i]);
        }
        return max;
    }

    /**
     * 解法3：动态规划
     * <p>
     * 执行用时：23 ms, 在所有 Java 提交中击败了13.35%的用户
     * 内存消耗：55 MB, 在所有 Java 提交中击败了5.12%的用户
     *
     * @param prices
     * @return
     */
    public int maxProfit3(int[] prices) {

        if (prices == null || prices.length == 0) {
            return 0;
        }
        // 创建二维数组
        int[][] dp = new int[prices.length][2];
        // 状态定义dp[i][j]
        // dp[i][0]: 表示第i天持有股票所得利润
        // dp[i][1]: 表示第i天不持有股票所得利润

        // 如何推持有股票和不持有股票所得利润
        // 1 持有股票dp[i][0]
        // 1.1 和i-1天保持一致 dp[i-1][0]
        // 1.2 第i天持有股票 利润就是 -prices[i]
        // 所以 最大利润就是max(dp[i-1][0], -prices[i])

        // 2 不持有股票dp[i][1]
        // 2.1 和i-1天保持一致 dp[i-1][1]
        // 2.2 第i天卖出股票, 说明前一天肯定持有股票 prices[i]+dp[i-1][0]
        // 所以 最大利润就是max(dp[i-1][1], prices[i]+dp[i-1][0])

        // 初始化数据
        // 第一天：持有股票0和不持有股票1
        dp[0][0] = -prices[0];
        dp[0][1] = 0;

        for (int i = 1; i < prices.length; i++) {
            // 第i天 持有股票
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            // 第i天 不持有股票
            dp[i][1] = Math.max(dp[i - 1][1], prices[i] + dp[i - 1][0]);
        }
        // 最后结果肯定是没有股票 全部卖出去来 利润才会最大
        return dp[prices.length - 1][1];
    }


}
