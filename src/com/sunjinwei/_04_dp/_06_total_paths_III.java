package com.sunjinwei._04_dp;

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

    /**
     * 动态规划：经典方法
     * <p>
     * 时间复杂度：
     * 矩阵中一共有 M×N 个位置，每个位置都计算一次从（0，0）位置达到自己的最小路径和，
     * 计算的时候只是比较上边位置的最小路径和与左边位置的最小路径和哪个更小，所以时间复杂度为O（M×N），
     * 空间复杂度：
     * dp矩阵的大小为M×N，所以额外空间复杂度为O（M×N）。
     *
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        // 边界条件1
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        // 行
        int m = grid.length;
        // 列
        int n = grid[0].length;
        // 状态方程 [i][j]表示从起点到[i][j]位置的最小路径
        int[][] dp = new int[m][n];
        // base case 初始化
        // 1第一行第一列第一个元素的的路径和 就是本身
        dp[0][0] = grid[0][0];
        // 2只有行
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        // 3只有列
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
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

    /**
     * 动态规划：状态压缩
     * 和上面经典的动态规划方法相比，经过状态压缩之后，时间复杂度依然是O(m*n),但是额外空间复杂度可以从O(m*n)减少至O(min{m,n})
     * 也就是不使用大小为m*n的dp矩阵，而仅仅使用大小为min(m,n)的arr数组
     */


}
