package com.sunjinwei._04_dp;

/**
 * 最长公共子串   【左神】
 * <p>
 * 1返回长度
 * 2返回这个子串 进阶
 */
public class _03_sub_str_longest_common {


    /**
     * 经典动态规划的方法可以做到时间复杂度为O（M×N），额外空间复杂度为O（M×N），经过优化之后的实现可以把额外空间复杂度从O（M×N）降至O（1）
     *
     * @param text1
     * @param text2
     * @return
     */
    public String subStrLonestCommon(String text1, String text2) {
        if (text1 == null || text1.length() == 0) {
            return "";
        }
        if (text2 == null || text2.length() == 0) {
            return "";
        }
        // 状态定义：dp[i][j]表示s1[0..i]和s2[0..j]的最长公共子串长度
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m][n];

        // base case : 无需状态初始化
        dp[0][0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;
        // 状态处理
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        // dp数组的最右下角的值就是当前两个字符串拥有的最长公共子串
        // 需要找到从哪个索引开始 从右向左开始遍历
        int index = -1;
        for (int i = m - 1; i >= 0; i--) {
            if (index != -1) {
                break;
            }
            for (int j = n - 1; j >= 0; j--) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    index = i;
                    break;
                }
            }
        }
        if (index == -1) {
            return "";
        }
        // 将text1字符串进行切割
        return text1.substring(index - dp[m - 1][n - 1] + 1, index + 1);
    }

    /**
     * 左神的写法：寻找索引那块代码自己可以进行优化
     *
     * @param text1
     * @param text2
     * @return
     */
    public String subStrLonestCommon2(String text1, String text2) {
        if (text1 == null || text1.length() == 0) {
            return "";
        }
        if (text2 == null || text2.length() == 0) {
            return "";
        }
        // 状态定义：dp[i][j]表示s1[0..i]和s2[0..j]的最长公共子串长度
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m][n];

        // base case : 无需状态初始化
        dp[0][0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;
        // 状态处理
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        // dp数组的最右下角的值就是当前两个字符串拥有的最长公共子串
        // 正常从左到右顺序遍历也行
        int index = -1;
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] > max) {
                    max = dp[i][j];
                    index = i;
                }
            }
        }
        if (index == -1) {
            return "";
        }
        // 将text1字符串进行切割
        return text1.substring(index - dp[m - 1][n - 1] + 1, index + 1);
    }


    public static void main(String[] args) {
        _03_sub_str_longest_common strLongestCommon = new _03_sub_str_longest_common();
        String s = strLongestCommon.subStrLonestCommon2("1AB345CD", "12345EF");
        System.out.println(s);

    }


}
