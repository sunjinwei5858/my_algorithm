package com.sunjinwei._04_dp;

/**
 * 最长公共子序列 力扣1143
 * <p>
 * 1返回长度
 * 2返回这个子序列 进阶
 * <p>
 * 最长公共子序列（Longest Common Subsequence，简称 LCS）是一道非常经典的面试题目，因为它的解法是典型的二维动态规划，
 * 大部分比较困难的字符串问题都和这个问题一个套路，比如说编辑距离。
 * 而且，这个算法稍加改造就可以用于解决其他问题，所以说 LCS 算法是值得掌握的。
 */
public class _03_sub_sequence_longest_common {

    /**
     * 常规写法：
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        // 鲁棒性1：只要其中有一个为空 那么返回0
        if (text1 == null || text1.length() == 0) {
            return 0;
        }
        if (text2 == null || text2.length() == 0) {
            return 0;
        }
        // 状态定义：dp[i][j]表示在s1[0..j]和s2[0..j]中的最长公共子串长度
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        // base case
        // 无需初始化 本身就是为0

        // 情况1：s1[i-1]=s2[j-1] 此时dp[i][j]=dp[i-1][j-1]+1
        // 情况2：s1[i-1]!=s2[j-1] 此时看dp[i-1][j]
        // 情况3：s1[i-1]!=s2[j-1] 此时看dp[i][j-1]
        // 然后情况2和情况3取最大
        // 遍历顺序：i和j都正常顺序遍历
        // 状态处理
        for (int i = 1; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    int s1 = dp[i - 1][j];
                    int s2 = dp[i][j - 1];
                    dp[i][j] = Math.max(s1, s2);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 左神的写法:
     * 根据画图 可以看出要获取dp[i,j]的值 需要左边[i,j-1] 正上[i-1,j] 斜左[i-1,j-1],
     * 其实转化为只要知道了第一行 第一列的值，那么就可以求出第二行 第二列的值!!!!
     */
    public int longestCommonSubsequence2(String text1, String text2) {

        // 鲁棒性1：只要其中有一个为空 那么返回0
        if (text1 == null || text1.length() == 0) {
            return 0;
        }
        if (text2 == null || text2.length() == 0) {
            return 0;
        }
        // 状态定义：dp[i][j]表示在s1[0..j]和s2[0..j]中的最长公共子串长度
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m][n];

        // base case!!!!
        dp[0][0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;
        // 处理第一列的值：s1[0..m]和s2[0]的值
        for (int i = 1; i < m; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], text1.charAt(i) == text2.charAt(0) ? 1 : 0);
        }
        // 处理第一行的值：s2[0..n]和s1[0]的值
        for (int j = 1; j < n; j++) {
            dp[0][j] = Math.max(dp[0][j - 1], text1.charAt(0) == text2.charAt(j) ? 1 : 0);
        }
        // 有了第一行和第一列的值 那么就可以计算出第二行第二列的值了 依次类推
        // dp[2,2]依赖dp[1,1] dp[1,2] dp[2,1]
        // 也就是dp[i,j]依赖[i-1,j-1] [i,j-1] [j,i-1]的值
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[m - 1][n - 1];
    }


}
