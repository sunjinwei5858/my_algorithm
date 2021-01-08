package com.sunjinwei.dp;

/**
 * 最长回文子串 力扣5 难度中等
 * 最长回文子序列 力扣516 是可以不连续 但是顺序要一致 不需要对称；子串是必须连续 并且对称
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 回文串： 正着读和反着读都是一样的字符串，对称
 */
public class _04_huiwen_str_longest {

    public String longestPalindrome(String s) {
        int n = s.length();
        if (n == 1) {
            return s;
        }
        if (n == 2) {
            if (s.charAt(0) == s.charAt(1)) {
                return s;
            } else {
                return null;
            }
        }
        // 状态定义：字符串s从第i到j个字符是不是回文串 true代表是回文串 false代表不是回文串
        // 只有[i+1][j-1]是回文串，并且s的第i和j个字母相同时，[i+1][j-1]才是回文串。
        // 我们还需要考虑动态规划中的边界条件，即子串的长度为1或2。
        // 对于长度为1的子串，它显然是个回文串；
        // 对于长度为2的子串，只要它的两个字母相同，它就是一个回文串
        boolean[][] dp = new boolean[n][n];
        // base case 初始化数据
//        // 边界条件1 只有一个字符时
//        dp[0][0] = true;
//        // 边界条件2 只有两个字符时
//        dp[0][1] = (s.charAt(0) == s.charAt(1));
//        dp[1][0] = (s.charAt(0) == s.charAt(1));
       String ans = "";
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j + i < n; j++) {
//                int m = i + j;
//                if (i == 0) {
//
//                }
//            }
//        }


        for (int l = 0; l < n; ++l) {
            for (int i = 0; i + l < n; ++i) {
                int j = i + l;
                if (l == 0) {
                    dp[i][j] = true;
                } else if (l == 1) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
                }
                if (dp[i][j] && l + 1 > ans.length()) {
                    ans = s.substring(i, i + l + 1);
                }
            }
        }
        return ans;
    }
}
