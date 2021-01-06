package com.sunjinwei.dp;

/**
 * 力扣647 回文子串
 * 本题是找到一个字符串中所有的回文子串，而第 5 题是求解一个字符串中最长的回文子串，
 * 很明显求解出所有的字符串自然能够找到最大的
 * 描述：
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 * <p>
 * 题解：https://leetcode-cn.com/problems/palindromic-substrings/solution/647-hui-wen-zi-chuan-dong-tai-gui-hua-fang-shi-qiu/
 */
public class _04_huiwen_string {

    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        // 状态定义：dp[i][j]代表字符串索引从i到j是不是回文串
        boolean[][] dp = new boolean[n][n];
        // base case 初始化数据
        // 只有一个字符的时候 那么肯定是回文串
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        // 声明个数 因为单个字符可以是回文串 所以最小值是字符串的长度
        int result = n;
        // 状态转移方程
        // 根据[i+1, j-1] 推出[i,j], 画图，得从右下角开始遍历
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    // 这里要区分两种情况
                    if (j == i + 1) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                // 判断是不是回文串
                if (dp[i][j]) {
                    result++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        _04_huiwen_string huiwen_string = new _04_huiwen_string();
        System.out.println(huiwen_string.countSubstrings("abba"));
    }

}
