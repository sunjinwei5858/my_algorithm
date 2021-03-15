package com.sunjinwei._04_dp;

/**
 * 最长回文子串 力扣5 难度中等
 * 最长回文子序列 力扣516
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









        return "";
    }
}
