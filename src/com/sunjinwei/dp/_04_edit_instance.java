package com.sunjinwei.dp;

/**
 * 编辑距离，力扣72 【这个题目非常经典 是任何字符串匹配的用动态规划解决的入门题】
 * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
 */
public class _04_edit_instance {

    /**
     * 解法一：动态规划
     * 典型的动态规划解法
     * if s1[i] == s2[j]:
     * 啥都别做（skip）
     * i, j 同时向前移动
     * else:
     * 三选一：
     * 插入（insert）
     * 删除（delete）
     * 替换（replace）
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        // 状态定义：dp[i,j]表示word1前i个字符变到word2前j个字符最少的编辑次数
        int[][] dp = new int[m + 1][n + 1];
        // base case
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        // 自底向上求解 i=1 j=1开始
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    // 跳过
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 增 删 改
                    dp[i][j] = 1 + min(dp[i][j - 1], dp[i - 1][j - 1], dp[i - 1][j]);
                }
            }
        }
        return dp[m][n];
    }

    public int min(int a, int b, int c) {
        int min = Math.min(a, b);
        return Math.min(min, c);
    }

    public static void main(String[] args) {
        _04_edit_instance edit_instance = new _04_edit_instance();
        System.out.println(edit_instance.minInstance_02("ad", "abc"));

    }

    /**
     * 解法二：暴力递归
     */
    public String str1;

    public String str2;

    public int minInstance_02(String word1, String word2) {
        str1 = word1;
        str2 = word2;
        // 自顶向下
        return dp(word1.length() - 1, word2.length() - 1);
    }

    private int dp(int i, int j) {
        if (i == -1 || j == -1) {
            return 0;
        }
        if (str1.charAt(i) == str2.charAt(j)) {
            // 跳过
            return dp(i - 1, j - 1);
        } else {
            // 增 删 改
            return min(
                    dp(i, j - 1) + 1,
                    dp(i - 1, j - 1) + 1,
                    dp(i - 1, j) + 1
            );
        }
    }

}
