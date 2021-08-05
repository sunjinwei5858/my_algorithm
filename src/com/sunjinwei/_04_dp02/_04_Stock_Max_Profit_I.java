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


}
