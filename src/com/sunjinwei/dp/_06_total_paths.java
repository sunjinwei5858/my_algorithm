package com.sunjinwei.dp;

/**
 * 力扣62 不同路径
 * 描述：
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）
 * 问总共有多少条不同的路径？
 */
public class _06_total_paths {

    /**
     * 二维数组
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        // 状态定义 [i][j] 表示机器人走到[i][j]的路径
        int[][] dp = new int[m][n];
        // 状态转移方程
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // base case 初始化 边界条件
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    // 要走到[i][j] 要么是从正上方走下来的[i,j-1] 要么是从左边走过来的[i-1][j]
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        _06_total_paths total_paths = new _06_total_paths();
        System.out.println(total_paths.uniquePaths(3, 7));
    }
}
