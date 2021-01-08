package com.sunjinwei.dp;

/**
 * 最长回文子序列 力扣516 难度 中等
 * <p>
 * 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
 * 比如 s = "cbbd" 输出2 最长的回文长度为bb ，即2
 */
public class _04_huiwen_str_longest_length {

    /**
     * 动态规划解法
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {

        int m = s.length();
        // 状态定义：s的第i个字符到第j个字符组成的子串中，最长的回文子序列长度
        int[][] dp = new int[m][m];
        // base case 如果只有一个字符 那么回文串长度显然是1
        for (int i = 0; i < m; i++) {
            dp[i][i] = 1;
        }
        // 状态转移方程 [i+1,j-1]+2或者[i,j-1][i+1,j]两者取最大
        // 因为这里是从i+1推出i 所以需要倒序遍历
        // i从最后一个字符开始往前遍历，j从i+1开始往后遍历，这样可以保证每个子问题都已经算好了。
        for (int i = m - 1; i >= 0; i--) {
            for (int j = i + 1; j < m; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][m - 1];
    }

    /**
     * 优化写法：将base case放入外层for循环中
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq_02(String s) {

        int m = s.length();
        // 状态定义：s的第i个字符到第j个字符组成的子串中，最长的回文子序列长度
        int[][] dp = new int[m][m];
        // 状态转移方程 [i+1,j-1]+2或者[i,j-1][i+1,j]两者取最大
        // 因为这里是从i+1推出i 所以需要倒序遍历
        // i从最后一个字符开始往前遍历，j从i+1开始往后遍历，这样可以保证每个子问题都已经算好了。
        for (int i = m - 1; i >= 0; i--) {
            // base case 如果只有一个字符 那么回文串长度显然是1
            dp[i][i] = 1;
            for (int j = i + 1; j < m; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][m - 1];
    }
}
