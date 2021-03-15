package com.sunjinwei._04_dp;

/**
 * 力扣62 不同路径
 * 描述：
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）
 * 问总共有多少条不同的路径？
 */
public class _06_total_paths_I {

    /**
     * 二维数组： 中规中矩的写法 复杂度O(m*n)
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        // 状态定义 [i][j] 表示机器人走到[i][j]的路径
        int[][] dp = new int[m][n];
        // base case 初始化
        // 只有行
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        // 只有列
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        // 状态转移方程
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 要走到[i][j] 要么是从正上方走下来的[i,j-1] 要么是从左边走过来的[i-1][j]
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }


    /**
     * 二维数组： 简洁写法 初始化处理直接放在两个for循环中处理
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths_02(int m, int n) {
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


    /**
     * 一维数组：状态压缩!!! 终极降维 复杂度O(m*n)
     * 核心思路：画图 画图 画图 80%的动态规划都可以画图 https://zhuanlan.zhihu.com/p/91582909
     * _04_dp[i] [j] 是一个二维矩阵，我们来画个二维矩阵的图，对矩阵进行初始化
     * 然后根据公式 _04_dp[i][j] = _04_dp[i-1][j] + _04_dp[i][j-1] 来填充矩阵的其他值。下面我们先填充第二行的值。
     * 当我们要填充第三行的值的时候，我们需要用到第一行的值吗？答是不需要的，
     * 当你要填充第三，第四....第 n 行的时候，第一行的值永远不会用到，只要填充第二行的值时会用到。
     * 答案：
     * 我们只需要用一个一维的 _04_dp[] 来保存一行的历史记录就可以了。然后在计算机的过程中，不断着更新 _04_dp[] 的值
     *
     *
     * 该题目：_04_dp[i][j] 依赖于 _04_dp[i-1][j] 和 _04_dp[i][j-1]
     * 联想编辑距离：_04_dp[i][j] 依赖于 _04_dp[i-1][j]，_04_dp[i-1][j-1] 和 _04_dp[i][j-1]。如何进行优化？
     * @param m
     * @param n
     */
    public int uniquePaths_03(int m, int n) {
        // 状态定义:[i]表示从起点走到i位置的路径条
        int[] dp = new int[n];
        // base case 初始化数据
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        // 状态转移方程
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 一边更新 _04_dp[i] 的值，一边把 _04_dp[i] 的旧值抛弃的
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        _06_total_paths_I total_paths = new _06_total_paths_I();
        System.out.println(total_paths.uniquePaths(3, 7));
    }
}
