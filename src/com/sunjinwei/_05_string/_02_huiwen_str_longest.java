package com.sunjinwei._05_string;

/**
 * 寻找最长回文串 力扣5
 * 中心扩散法
 */
public class _02_huiwen_str_longest {

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        String res = "";
        int size = s.length();
        for (int i = 0; i < size; i++) {
            String s1 = help(s, i, i, size);
            String s2 = help(s, i, i + 1, size);
            res = s1.length() > s2.length() ? s1 : s2;
        }
        return res;
    }

    private String help(String s, int left, int right, int size) {
        // 中心扩散法 left-- right++
        while (left >= 0 && right < size && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right - left - 1);
    }

    public static void main(String[] args) {
        _02_huiwen_str_longest huiwenStrLongest = new _02_huiwen_str_longest();
        String palindrome = huiwenStrLongest.longestPalindrome("babad");
        System.out.println(palindrome);
    }
}
