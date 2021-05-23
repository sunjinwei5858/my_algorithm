package com.sunjinwei._04_dp;

/**
 * 最长回文子序列的长度 力扣516 难度 中等 动态规划
 * 注意：回文子串必须连续 但是回文子序列可不是连续的
 * 回文子串 回文子序列都是动态规划的经典题目
 * <p>
 * 给定一个字符串s，并返回该序列的长度。可以假设s的最大长度为1000 。
 * 比如 s = "cbbd" 输出2 最长的回文长度为bb ，即2
 * 注意：
 * 和409的区别 409是构造最长的回文串 返回长度
 */
public class _04_huiwen_str_subseq_longest {

    /**
     * 经典动态规划：
     * 1.确定dp数组，以及下标的含义
     * dp[i,j]:字符串在[i,j]范围内最长的回文子序列的长度为dp[i,j]
     * 2.确定递推公式
     * 在判断回文子串题目中，关键逻辑就是看s[i]和s[j]是否相同，
     * 如果相同，那么dp[i,j]=dp[i+1][j-1]+2
     * 如果不相同，说明s[i]和s[j]的同时加入并不能增加[i.j]区间回文子串的长度，
     * 那么分别加入s[i],s[j]看看哪一个可以组成最长的回文子序列
     * 加入s[i]的回文子序列长度为dp[i+1][j]
     * 加入s[i]的回文子序列长度为dp[i][j-1]
     * 那么，一定是这两者取最大
     * 3.dp数组如何初始化
     * 当i和j相同时，需要手动初始化，也就是一个字符，那么长度就是1
     * 4.确定遍历顺序
     * dp[i,j]=dp[i+1][j-1]+2可以看出，dp[i,j]依赖dp[i+1][j-1]
     * 所以遍历的时候一定时从下到上遍历
     *
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
