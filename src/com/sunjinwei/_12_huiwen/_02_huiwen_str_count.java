package com.sunjinwei._12_huiwen;

/**
 * 统计字符串有多少个回文串
 * 方法1：动态规划 还可以进行状态压缩
 * 方法2：中心扩展法
 * <p>
 * 思路：回文串 需要考虑三种情况：回文串中心是一个元素 是两个元素 其余情况依赖[i+1][j-1]
 */
public class _02_huiwen_str_count {

    /**
     * 动态规划
     *
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // 状态定义dp[i][j]: 字符串从[i,j]是否是回文串
        // [i,j]是不是回文串依赖[i+1,j-1]是不是
        int size = s.length();
        boolean[][] dp = new boolean[size][size];
        // base case 无需初始化 因为默认为false
        // 处理, 因为[i,j]依赖[i+1,j-1] 所以遍历顺序为斜向上遍历 即i倒序遍历 j正序遍历
        int count = 0;
        for (int i = size - 1; i >= 0; i--) {
            for (int j = i; j < size; j++) {
                // 只有字符串在i和j位置字符相同才能进行比较
                if (s.charAt(i) != s.charAt(j)) {
                    continue;
                }
                // 情况1：只有一个元素
                if (i == j) {
                    dp[i][j] = true;
                } else if (i == j - 1) {
                    // 情况2：只有两个元素
                    dp[i][j] = true;
                } else {
                    // 情况3：依赖[i+1][j-1]
                    dp[i][j] = dp[i + 1][j - 1];
                }
                if (dp[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }


    /**
     * 中心扩散法
     * 分为两种情况：一个中心点 两个中心点
     * left-- right++
     *
     * @param s
     * @return
     */
    public int countSubstrings2(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            // 一个中心点
            count += help(s, i, i);
            // 两个中心点
            count += help(s, i, i + 1);
        }
        return count;
    }

    private int help(String s, int left, int right) {
        int res = 0;
        // left-- right++
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
            res++;
        }
        return res;
    }


}
