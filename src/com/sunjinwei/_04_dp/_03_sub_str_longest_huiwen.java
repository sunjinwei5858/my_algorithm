package com.sunjinwei._04_dp;

/**
 * 最长回文子串 力扣5
 * 方法1：中心扩展法
 * 方法2：dp 这道题使用动态规划解答 并不是最优解
 */
public class _03_sub_str_longest_huiwen {


    /**
     * 方法1：动态规划
     * <p>
     * 执行用时：309 ms, 在所有 Java 提交中击败了30.65%的用户
     * 内存消耗：42.3 MB, 在所有 Java 提交中击败了42.65%的用户
     *
     * @param s
     * @return
     */
    public String longestHuiwenStr(String s) {
        // 鲁棒性1: 字符串为空 或者只有一个字符
        if (s == null) {
            return null;
        }
        // 鲁棒性2：字符串长度为1 那直接返回
        if (s.length() == 1) {
            return s;
        }
        // 二维dp数组 状态定义 dp[i][j]代表字符串从[i,j]范围是否是回文串
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        // 状态初始化：本身就是false
        // 无需初始化

        // 处理
        // 外循环和内循环如何进行遍历【画图】
        // 需要根据[i+1,j-1]来推出[i,j] 所以此时i需要倒叙遍历 j正常顺序遍历
        // 斜向上遍历
        int left = 0;
        int right = 0;
        int maxLength = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    // 情况1：同一个字符
                    if (i == j) {
                        dp[i][j] = true;
                    } else if (i == j - 1) {
                        // 情况2：为相邻字符 j更大
                        dp[i][j] = true;
                    } else {
                        // 情况3：需要判断中间字符是不是回文串
                        if (dp[i + 1][j - 1]) {
                            dp[i][j] = true;
                        }
                    }
                }
                // 进行判断 处理最长回文子串的逻辑
                if (dp[i][j] && (j - i + 1 > maxLength)) {
                    left = i;
                    right = j;
                    maxLength = j - i + 1;
                }
            }
        }
        return s.substring(left, right + 1);
    }

    /**
     * 方法2：中心扩展法
     * 奇数
     * 偶数
     * <p>
     * 执行用时：42 ms, 在所有 Java 提交中击败了69.51%的用户
     * 内存消耗：39.2 MB, 在所有 Java 提交中击败了52.32%的用户
     *
     * @param s
     */
    public String longestHuiwenStr2(String s) {
        if (s == null || s.length() == 1) {
            return s;
        }
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String s1 = extend(s, i, i);
            String s2 = extend(s, i, i + 1);
            res = longest(res, s1, s2);
        }
        return res;
    }

    private String longest(String res, String s1, String s2) {
        res = res.length() > s1.length() ? res : s1;
        res = res.length() > s2.length() ? res : s2;
        return res;
    }

    private String extend(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        //[left+1, right-1] 因为substring的api逻辑是左闭右开
        return s.substring(left + 1, right);
    }

    /**
     * 方法3：中心扩展法的优化
     * <p>
     * 执行用时：30 ms, 在所有Java提交中击败了88.43%的用户
     * 内存消耗：38.3 MB, 在所有Java提交中击败了93.60%的用户
     *
     * @param s
     */
    int i = 0;
    int j = 0;
    int maxLen = 0;

    public String longestHuiwenStr3(String s) {
        if (s == null || s.length() == 1) {
            return s;
        }
        for (int i = 0; i < s.length(); i++) {
            extend2(s, i, i);
            extend2(s, i, i + 1);
        }
        // 此时已经计算出最大的maxLen 和对应的[i+1,j-1]位置
        return s.substring(i + 1, j);
    }

    private void extend2(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        if (right - left - 1 > maxLen) {
            i = left;
            j = right;
            maxLen = right - left - 1;
        }
    }


    public static void main(String[] args) {
        _03_sub_str_longest_huiwen longestHuiwen = new _03_sub_str_longest_huiwen();
        String huiwenStr = longestHuiwen.longestHuiwenStr("babad");
        System.out.println(huiwenStr);
    }


}
