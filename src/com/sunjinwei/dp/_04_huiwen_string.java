package com.sunjinwei.dp;

/**
 * 力扣647 回文子串
 * 描述：
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 */
public class _04_huiwen_string {

    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // 状态定义：dp[i][j]代表字符串从i到j的回文子串个数
        int m = s.length();
        int[][] dp = new int[m][m];
        // base case 初始化数据
        for (int i = 0; i < m; i++) {
            dp[i][i] = 1;
        }
        // 状态转移方程
        for (int i = m - 1; i >= 0; i++) {
            for (int j = i + 1; j < m; j++) {
                if (s.charAt(i ) == s.charAt(j)){

                }
            }
        }


    }

}
