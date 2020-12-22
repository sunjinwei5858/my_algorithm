package com.sunjinwei.dp;

/**
 * 最长公共子序列【LCS】一道非常经典的动态规划 典型的面试题 力扣1143
 * 大部分比较困难的字符串问题都和这个问题一个套路，比如编辑距离
 * <p>
 * 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的「公共子序列」是这两个字符串所共同拥有的子序列。
 * 若这两个字符串没有公共子序列，则返回 0。
 */
public class _04_longest_common_subsequence {


    public String str1;

    public String str2;

    /**
     * 解法一：暴力递归 dp函数
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        str1 = text1;
        str2 = text2;
        // 自顶向下
        return dp(text1.length() - 1, text2.length() - 1);
    }

    /**
     * dp函数==》dp数组dp[i,j]=dp[]
     *
     * @param i
     * @param j
     * @return
     */
    private int dp(int i, int j) {
        if (i == -1 || j == -1) {
            return 0;
        }
        if (str1.charAt(i) == str2.charAt(j)) {
            // 最长公共子序列 进行+1
            return dp(i - 1, j - 1) + 1;
        } else {
            // 返回前一个
            return Math.max(dp(i - 1, j), dp(i, j - 1));
        }
    }

    /**
     * 解法二：动态规划 dptable
     */
    public int longestCommonSubsequence_dp(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        // 1 状态定义: [i][j]为
        int[][] dp = new int[m + 1][n + 1];
        // 2 正序遍历 自底向上求解
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        _04_longest_common_subsequence commonSubsequence = new _04_longest_common_subsequence();
        System.out.println(commonSubsequence.longestCommonSubsequence_dp("ace", "abcde"));
    }


}
