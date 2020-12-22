package com.sunjinwei.myself.first.dp;

/**
 * 编辑距离 经典的动态规划
 */
public class _01_edit_instance {

    /**
     * 有四种操作：跳过 增 删 改
     *
     * @param word01
     * @param word02
     * @return
     */
    public int editMinInstance(String word01, String word02) {
        int m = word01.length();
        int n = word02.length();
        // 1状态定义
        int[][] dp = new int[m + 1][n + 1];
        // 2base case
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }
        // 3状态转移方程
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word01.charAt(i - 1) == word02.charAt(j - 1)) {
                    // 跳过
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 增删改
                    dp[i][j] = min(dp[i - 1][j - 1] + 1, dp[i - 1][j] + 1, dp[i][j - 1] + 1);
                }
            }
        }
        return dp[m][n];
    }

    private int min(int a, int b, int c) {
        int min = Math.min(a, b);
        return Math.min(min, c);
    }

    public static void main(String[] args) {
        _01_edit_instance edit_instance = new _01_edit_instance();
        System.out.println(edit_instance.editMinInstance("ab", "abcvvv"));
    }
}
