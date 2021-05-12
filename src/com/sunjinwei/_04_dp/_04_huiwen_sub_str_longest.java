package com.sunjinwei._04_dp;

/**
 * 最长回文子串 力扣5 难度中等  【子串必须连续】【子数组也是连续的】
 * 最长回文子序列 力扣516 难度中等 【子序列可以不连续 相比前面两者 稍微复杂一些】
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 回文串： 正着读和反着读都是一样的字符串，对称
 */
public class _04_huiwen_sub_str_longest {

    /**
     * 最长回文子串: 子串必须是连续的
     * 中心扩散法
     * <p>
     * 执行用时：38 ms, 在所有 Java 提交中击败了75.03%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了69.98%的用户
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {

        if (s == null) {
            return null;
        }
        if (s.length() == 1) {
            return s;

        }
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String s1 = palindrome(s, i, i);
            String s2 = palindrome(s, i, i + 1);
            // 判断res s1 s2 三者的长度
            res = longest(res, s1, s2);
        }
        return res;
    }

    private String longest(String res, String s1, String s2) {
        res = res.length() >= s1.length() ? res : s1;
        res = res.length() >= s2.length() ? res : s2;
        return res;
    }

    private String palindrome(String s, int left, int right) {
        while (right < s.length() && left >= 0 && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // left和right的边界不能取 应该取 [left+1, right-1]
        // 因为substring是左闭右开 所以传入的是left+1和right
        return s.substring(left + 1, right);
    }

    public static void main(String[] args) {
        String s = "babad";
        _04_huiwen_sub_str_longest huiwenStrLongest = new _04_huiwen_sub_str_longest();
        String palindrome = huiwenStrLongest.longestPalindrome(s);
        System.out.println(palindrome);
    }
}
