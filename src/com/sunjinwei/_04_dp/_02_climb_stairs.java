package com.sunjinwei._04_dp;


/**
 * 爬楼梯 力扣70  其实就是斐波那契数列
 * <p>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 */
public class _02_climb_stairs {

    /**
     * 动态规划：经典方法求解
     *
     * @param n
     * @return
     */
    public int climbStairs_02(int n) {
        // 这里必须是小于等于2 不是1
        // 当台阶为1时 只有1种方法 爬1个台阶
        // 当台阶为2时 有2种方法 爬两次 爬一次
        if (n <= 2) {
            return n;
        }
        int[] dp = new int[n + 1];
        // 初始化数据
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 动态规划：状态(空间)压缩求解
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        // base case
        if (n <= 2) {
            return n;
        }
        // 记录上一个值
        int prev = 1;
        // 当前值
        int current = 2;
        // 从第三个台阶开始
        for (int i = 3; i < n; i++) {
            int sum = prev + current;
            prev = current;
            current = sum;
        }
        return current;
    }


}
