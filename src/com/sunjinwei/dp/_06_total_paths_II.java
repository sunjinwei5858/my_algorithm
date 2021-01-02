package com.sunjinwei.dp;

/**
 * 力扣63 不同路径II 变种: 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 * 描述：
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）
 * <p>
 * 问总共有多少条不同的路径？
 * <p>
 * 动态规划的题目分为两大类，
 * 一种是求最优解类，典型问题是背包问题，
 * 另一种就是计数类，比如这里的统计方案数的问题，它们都存在一定的递推性质。
 * 前者的递推性质还有一个名字，叫做 「最优子结构」 ——即当前问题的最优解取决于子问题的最优解，
 * 后者类似，当前问题的方案数取决于子问题的方案数。
 * 所以在遇到求方案数的问题时，我们可以往动态规划的方向考虑。
 */
public class _06_total_paths_II {

    /**
     * 二维数组动态规划
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null && obstacleGrid.length == 0) {
            return 0;
        }
        // 长度
        int m = obstacleGrid.length;
        // 宽度
        int n = obstacleGrid[0].length;
        // 边界情况1 最开始有障碍物
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        // 边界情况2 最末尾有障碍物
        if (obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }
        // 状态方程
        int[][] dp = new int[m][n];
        // base case 初始化数据
        // 只有行的情况
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 0) {
                dp[i][0] = 1;
            } else {
                // 在只有行的情况下 如果行有障碍物 那么障碍物之后的格子都到达不了 直接break，后面的不管有没有障碍物都不能访问了
                dp[i][0] = 0;
                break;
            }
        }
        // 只有列的情况
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 0) {
                dp[0][i] = 1;
            } else {
                // 在只有列的情况 如果列有障碍物 那么障碍物之后的格子都到达不了 直接break
                dp[0][i] = 0;
                break;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                } else { // 可以省略
                    dp[i][j] = 0;
                }
            }
        }
        return dp[m - 1][n - 1];
    }


    /**
     * 简洁写法!!!
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles_02(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0) {
            return 0;
        }
        // 定义dp数组并初始化第 1 行和第 1 列。
        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        //
        for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n && obstacleGrid[0][j] == 0; j++) {
            dp[0][j] = 1;
        }

        // 根据状态转移方程 dp[i][j] = dp[i - 1][j] + dp[i][j - 1] 进行递推。
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }

}
