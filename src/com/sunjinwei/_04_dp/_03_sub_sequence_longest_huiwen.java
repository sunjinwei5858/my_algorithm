package com.sunjinwei._04_dp;

/**
 * 最长回文子序列 力扣516
 * <p>
 * 1返回长度
 * 2返回这个子序列
 */
public class _03_sub_sequence_longest_huiwen {

    /**
     * 给定一个字符串 s ，找到其中最长的回文子序列，并返回该序列的长度。可以假设 s 的最大长度为 1000 。
     * <p>
     * 特点：一个字符串
     * 状态定义：在字符数组[i,j]中，最长回文子序列的长度为dp[i][j]
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        // 状态定义：在字串s[i...j]中，最长回文子序列的长度为dp[i][j]
        int[][] dp = new int[n][n];
        // base case: 单个字符的最长回文子序列就是1 可以优化 将base case放入到嵌套for循环中
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        // 状态转移:
        // 通过画图，分为三种情况
        // 情况1：s[i]=s[j],那么就是dp[i,j]=dp[i+1,j-1]+2
        // 情况2：s[i]!=s[j]，那么要看s[i,j-1]范围 推出i和j的关系
        // 情况3：s[i]!=s[j],那么要看s[i+1,j]范围 推出i和j的关系
        // 情况2和情况3取最大即可
        // 遍历顺序：i需要倒叙遍历 j从i的下一个开始遍历
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    int s1 = dp[i][j - 1];
                    int s2 = dp[i + 1][j];
                    dp[i][j] = Math.max(s1, s2);
                }
            }
        }
        return dp[0][n - 1];
    }

    /**
     * 返回这个子序列
     */
    public int[] longestPalindromeSubseq2(String s) {


        return new int[0];
    }

}
