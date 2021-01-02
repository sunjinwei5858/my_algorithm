package com.sunjinwei.dp;

/**
 * 力扣64 不同路径III 变种: 最小路径和
 * 描述：给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * <p>
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 100
 */
public class _06_total_paths_III {

    public int minPathSum(int[][] grid) {
        // 边界条件1
        if (grid == null || grid.length == 0) {
            return 0;
        }
        // 行
        int m = grid.length;
        // 列
        int n = grid[0].length;
        // 状态方程 [i][j]表示从起点到[i][j]位置的最小路径
        int[][] dp = new int[m][n];
        // base case 初始化
        // 只有行
        int sum01 = 0;
        for (int i = 0; i < m; i++) {
            sum01 = sum01 + grid[i][0];
            dp[i][0] = sum01;
        }
        // 只有列
        int sum02 = 0;
        for (int i = 0; i < n; i++) {
            sum02 = sum02 + grid[0][i];
            dp[0][i] = sum02;
        }
        // 状态转移方程
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 左边[i-1][j] 上边[i][j-1]
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }


}
