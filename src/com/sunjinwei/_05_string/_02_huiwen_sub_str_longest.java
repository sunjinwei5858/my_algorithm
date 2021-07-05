package com.sunjinwei._05_string;

/**
 * 【回文串】
 * 寻找最长回文串 力扣5
 * 中心扩散法:两种写法
 * 动态规划：一种写法 和统计有多少回文子串的思想一样!!!!
 */
public class _02_huiwen_sub_str_longest {

    /**
     * 1中心扩散法的第一种写法，辅助函数help中直接返回string
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        String res = "";
        int size = s.length();
        for (int i = 0; i < size; i++) {
            String s1 = help(s, i, i, size);
            String s2 = help(s, i, i + 1, size);
            // 三者取最长的
            res = longest(res, s1, s2);
        }
        return res;
    }

    private String longest(String res, String s1, String s2) {
        String ans = null;
        ans = s1.length() > s2.length() ? s1 : s2;
        ans = ans.length() > res.length() ? ans : res;
        return ans;
    }

    private String help(String s, int left, int right, int size) {
        // 中心扩散法 i-- j++
        while (left >= 0 && right < size && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // 因为substring是左闭右开的 所以left+1即可，right不需要减1
        return s.substring(left + 1, right);
    }

    /**
     * 2中心扩展法的第二种写法，辅助函数处理返回长度
     *
     * @param s
     */

    private int i = 0;
    private int j = 0;
    private int maxLength = 0;

    public String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        int size = s.length();
        for (int i = 0; i < s.length(); i++) {
            help2(s, i, i, size);
            help2(s, i, i + 1, size);
        }
        return s.substring(i + 1, j);
    }

    private void help2(String s, int left, int right, int size) {
        while (left >= 0 && right < size && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        // 左边left+1
        // 右边right
        // 长度 right-left-1
        if (right - left - 1 > maxLength) {
            maxLength = right - left - 1;
            i = left;
            j = right;
        }
    }


    /**
     * 3动态规划解法：正确姿势
     *
     * @param s
     * @return
     */
    public String longestPalindrome4(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        // 状态定义：dp[i][j]表示s[i..j]的字符串是否是回文串
        boolean[][] dp = new boolean[s.length()][s.length()];
        // base case: 如果只有一个字符串 那么为true 可以放到for循环中去处理
        // 状态处理：通过画图 字符串[i..j]
        // s[i]=s[j] 有四种情况 'a' 'aa' 'aba' 'abba'
        int maxLen = 0;
        int left = 0;
        int n = s.length();
        // 遍历顺序：i倒叙遍历 j顺序遍历
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    // 同一个字符 比如'a'
                    if (i == j) {
                        dp[i][j] = true;
                    } else if (j - i <= 2) {
                        // 相邻字符 比如'aa';
                        // 三个字符 比如 'afa'
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    left = i;
                }
            }
        }
        return s.substring(left, left + maxLen);
    }

    /**
     * 对上面动态规划的代码进行重构
     *
     * @param s
     * @return
     */
    public String longestPalindrome5(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        // 状态定义：dp[i][j]表示s[i..j]的字符串是否是回文串
        boolean[][] dp = new boolean[s.length()][s.length()];
        // base case: 如果只有一个字符串 那么为true 可以放到for循环中去处理
        // 状态处理：通过画图 字符串[i..j]
        // s[i]=s[j] 有四种情况 'a' 'aa' 'aba' 'abba'
        // 遍历顺序：i倒叙遍历 j顺序遍历
        int maxLen = 0;
        int left = 0;
        int n = s.length();
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    // 将 'a' 'aa' 'aba' 这三种情况进行合并为(j-i)<=2
                    dp[i][j] = (j - i) <= 2 || dp[i + 1][j - 1];
                }
                // 寻找长度最长的回文串
                // 求出长度 j-i+1
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    left = i;
                }
            }
        }
        return s.substring(left, left + maxLen);
    }


    public static void main(String[] args) {
        _02_huiwen_sub_str_longest huiwenStrLongest = new _02_huiwen_sub_str_longest();
        String palindrome = huiwenStrLongest.longestPalindrome2("aaa");
        System.out.println(palindrome);
    }
}
