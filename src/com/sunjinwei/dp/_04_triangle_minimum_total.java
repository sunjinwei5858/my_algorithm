package com.sunjinwei.dp;

import java.util.List;

/**
 * 力扣120 三角形最小路径和 triangle
 *
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * 相邻的结点在这里指的是下标[i,j]与上一层结点下标相同[i+1,j]或者等于上一层结点下标+1的[i+1，j+1]两个结点。
 *
 * <p>
 * 定义dp：二维数组 自底向上 dp[i,j] 底部到[i,j]的最小路径和
 * 状态转移方程：dp[i,j] = min(dp[i+1,j], dp[i+1,j+1]) + triangle(i)
 */
public class _04_triangle_minimum_total {


    /**
     * 解法一： 递归
     * <p>
     * 若定义 f(i, j)f(i,j) 为 (i, j)(i,j) 点到底边的最小路径和，则易知递归求解式为:
     * <p>
     * f(i, j) = min(f(i + 1, j), f(i + 1, j + 1)) + triangle[i][j]f(i,j)=min(f(i+1,j),f(i+1,j+1))+triangle[i][j]
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        return dfs(triangle, 0, 0);
    }

    private int dfs(List<List<Integer>> triangle, int i, int j) {
        if (triangle.size() == i) {
            return 0;
        }
        // 自顶向下 当前[i,j]位置到顶点[0,0]的路径和+[i,j]位置到底部的路径最小值
        int total1 = dfs(triangle, i + 1, j);
        int total2 = dfs(triangle, i + 1, j + 1);
        Integer current = triangle.get(i).get(j);
        return Math.min(total1, total2) + current;
    }

    /**
     * 解法二：暴力递归有大量的重复计算，使用数组进行记忆优化
     * 定义二维数组进行记忆
     */
    public Integer[][] memory;

    public int minmumTotal_02(List<List<Integer>> triangle) {
        int length = triangle.size();
        memory = new Integer[length][length];
        return dfs_02(triangle, 0, 0);
    }

    private int dfs_02(List<List<Integer>> triangle, int i, int j) {
        if (i == triangle.size()) {
            return 0;
        }
        if (memory[i][j] != null) {
            return memory[i][j];
        }
        int total1 = dfs_02(triangle, i + 1, j);
        int total2 = dfs_02(triangle, i + 1, j + 1);
        Integer current = triangle.get(i).get(j);
        return Math.min(total1, total2) + current;
    }

    /**
     * 解法三：动态规划 定义二维dp数组 将解法二中的【自顶向下的递归】==》【自底向上的递推】
     * 1。状态定义：dp[i][j]表示从[i.j]到底边的最小路径和
     * 2。状态转移：dp[i][j]=min(dp[i+1][j], dp[i+1][j+1])+triangle[i][j]
     */
    public int dp(List<List<Integer>> triangle) {
        int n = triangle.size();
        // dp数组 将size+1
        int[][] dp = new int[n + 1][n + 1];
        // 自底向上 那么就需要倒序遍历
        for (int i = n - 1; i >= 0; i--) {
            List<Integer> integers = triangle.get(i);
            for (int j = 0; j < integers.size(); j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + integers.get(j);
            }
        }
        return dp[0][0];
    }


}
