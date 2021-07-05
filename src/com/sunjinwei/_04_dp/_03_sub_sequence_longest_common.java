package com.sunjinwei._04_dp;

/**
 * 最长公共子序列 力扣1143
 * <p>
 * 1返回长度
 * 2返回这个子序列 进阶
 * <p>
 * 最长公共子序列（Longest Common Subsequence，简称 LCS）是一道非常经典的面试题目，因为它的解法是典型的二维动态规划，
 * 大部分比较困难的字符串问题都和这个问题一个套路，比如说编辑距离。
 * 而且，这个算法稍加改造就可以用于解决其他问题，所以说 LCS 算法是值得掌握的。
 * <p>
 * 方法1：暴力递归
 * 方法2：暴力递归+备忘录
 * 方法3：动态规划
 */
public class _03_sub_sequence_longest_common {

    /**
     * 动态规划常规写法：
     * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        // 鲁棒性1：只要其中有一个为空 那么返回0
        if (text1 == null || text1.length() == 0) {
            return 0;
        }
        if (text2 == null || text2.length() == 0) {
            return 0;
        }
        // 状态定义：dp[i][j]表示在s1[0..j]和s2[0..j]中的最长公共子串长度
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        // base case
        // 无需初始化 本身就是为0

        // 情况1：s1[i-1]=s2[j-1] 此时dp[i][j]=dp[i-1][j-1]+1
        // 情况2：s1[i-1]!=s2[j-1] 此时看dp[i-1][j]
        // 情况3：s1[i-1]!=s2[j-1] 此时看dp[i][j-1]
        // 然后情况2和情况3取最大
        // 遍历顺序：i和j都正常顺序遍历
        // 状态处理
        for (int i = 1; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    int s1 = dp[i - 1][j];
                    int s2 = dp[i][j - 1];
                    dp[i][j] = Math.max(s1, s2);
                }
            }
        }
        return dp[m][n];
    }

    /**
     * 左神的写法:
     * 根据画图 可以看出要获取dp[i,j]的值 需要左边[i,j-1] 正上[i-1,j] 斜左[i-1,j-1],
     * 其实转化为只要知道了第一行 第一列的值，那么就可以求出第二行 第二列的值!!!!
     */
    public int longestCommonSubsequence2(String text1, String text2) {

        // 鲁棒性1：只要其中有一个为空 那么返回0
        if (text1 == null || text1.length() == 0) {
            return 0;
        }
        if (text2 == null || text2.length() == 0) {
            return 0;
        }
        // 状态定义：dp[i][j]表示在s1[0..j]和s2[0..j]中的最长公共子串长度
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m][n];

        // base case!!!!
        dp[0][0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;
        // 处理第一列的值：s1[0..m]和s2[0]的值
        for (int i = 1; i < m; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], text1.charAt(i) == text2.charAt(0) ? 1 : 0);
        }
        // 处理第一行的值：s2[0..n]和s1[0]的值
        for (int j = 1; j < n; j++) {
            dp[0][j] = Math.max(dp[0][j - 1], text1.charAt(0) == text2.charAt(j) ? 1 : 0);
        }
        // 有了第一行和第一列的值 那么就可以计算出第二行第二列的值了 依次类推
        // dp[2,2]依赖dp[1,1] dp[1,2] dp[2,1]
        // 也就是dp[i,j]依赖[i-1,j-1] [i,j-1] [j,i-1]的值
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    /**
     * 根据dp数组还原出最长公共子序列字符串
     */
    public String longestCommonSubsequence3(String text1, String text2) {
        // 鲁棒性1：只要其中有一个为空 那么返回空数组
        if (text1 == null || text1.length() == 0) {
            return "";
        }
        if (text2 == null || text2.length() == 0) {
            return "";
        }
        // 得到dp数组
        int[][] dp = getDp(text1, text2);
        // 根据dp数组还原最长公共子序列
        // 最右下角的值就是这两个字符串最长的公共子序列的长度
        int m = text1.length() - 1;
        int n = text2.length() - 1;
        int len = dp[m][n];
        // 声明子序列字符数组
        char[] res = new char[len];
        // 三种情况 要么往左走 往上走 往左上走
        // 往左走：dp[i][j]=dp[i-1][j]
        // 往上走：dp[i][j]=dp[i-1][j]
        // 往左上走：dp[i][j]=dp[i-1][j-1] 说明是两个字符串共同的字符 m和n需要-- res保存一个数据

        // 说明还原res 需要从右到左
        for (int i = res.length - 1; i >= 0; i--) {
            if (m > 0 && dp[m][n] == dp[m - 1][n]) {
                // 向左移动 m--
                m--;
            } else if (n > 0 && dp[m][n] == dp[m][n - 1]) {
                // 向上移动 n--
                n--;
            } else {
                // 向左上移动 m-- n-- 并且进行还原数据
                res[i] = text1.charAt(m);
                m--;
                n--;
            }
        }
        return String.valueOf(res);
    }


    /**
     * 获取dp数组
     */
    private int[][] getDp(String text1, String text2) {
        // 状态定义：dp[i][j]表示在s1[0..j]和s2[0..j]中的最长公共子串长度
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m][n];

        // base case!!!!
        dp[0][0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;
        // 处理第一列的值：s1[0..m]和s2[0]的值
        for (int i = 1; i < m; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], text1.charAt(i) == text2.charAt(0) ? 1 : 0);
        }
        // 处理第一行的值：s2[0..n]和s1[0]的值
        for (int j = 1; j < n; j++) {
            dp[0][j] = Math.max(dp[0][j - 1], text1.charAt(0) == text2.charAt(j) ? 1 : 0);
        }
        // 有了第一行和第一列的值 那么就可以计算出第二行第二列的值了 依次类推
        // dp[2,2]依赖dp[1,1] dp[1,2] dp[2,1]
        // 也就是dp[i,j]依赖[i-1,j-1] [i,j-1] [j,i-1]的值
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp;
    }


    /**
     * 暴力递归：力扣通不过 超出时间限制
     *
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence4(String text1, String text2) {
        // 鲁棒性1：只要其中有一个为空 那么返回0
        if (text1 == null || text1.length() == 0) {
            return 0;
        }
        if (text2 == null || text2.length() == 0) {
            return 0;
        }
        // 辅助函数
        return help(text1, text1.length() - 1, text2, text2.length() - 1);
    }

    private int help(String s1, int i, String s2, int j) {
        if (i == -1 || j == -1) {
            return 0;
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            // 需要+1
            return help(s1, i - 1, s2, j - 1) + 1;
        }
        int res1 = help(s1, i - 1, s2, j);
        int res2 = help(s1, i, s2, j - 1);
        return Math.max(res1, res2);
    }

    /**
     * 暴力递归优化：备忘录记忆化 【但是复杂度很高】
     *
     * @param text1
     * @param text2
     * @return
     */
    private Integer[][] arr;

    public int longestCommonSubsequence5(String text1, String text2) {

        // 鲁棒性1：只要其中有一个为空 那么返回0
        if (text1 == null || text1.length() == 0) {
            return 0;
        }
        if (text2 == null || text2.length() == 0) {
            return 0;
        }
        arr = new Integer[text1.length()][text2.length()];
        // 辅助函数
        return help2(text1, text1.length() - 1, text2, text2.length() - 1);
    }

    private int help2(String s1, int i, String s2, int j) {
        if (i == -1 || j == -1) {
            return 0;
        }
        if (arr[i][j] != null) {
            return arr[i][j];
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            arr[i][j] = help2(s1, i - 1, s2, j - 1) + 1;
        } else {
            int res1 = help2(s1, i, s2, j - 1);
            int res2 = help2(s1, i - 1, s2, j);
            arr[i][j] = Math.max(res1, res2);
        }
        return arr[i][j];
    }


    public static void main(String[] args) {
        _03_sub_sequence_longest_common sequenceLongestCommon = new _03_sub_sequence_longest_common();
        String s = sequenceLongestCommon.longestCommonSubsequence3("abc", "def");
        System.out.println(s);
    }


}
