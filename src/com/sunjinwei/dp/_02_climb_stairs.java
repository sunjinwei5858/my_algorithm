package com.sunjinwei.dp;


/**
 * 爬楼梯 力扣70  其实就是斐波那契数列
 * <p>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 */
public class _02_climb_stairs {

    /**
     * 动态规划求解，斐波那契数列
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }
        int prev = 1;
        int current = 2;

        for (int i = 3; i < n; i++) {
            int sum = prev + current;
            prev = current;
            current = sum;
        }
        return current;
    }

}
