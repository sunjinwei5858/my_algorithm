package com.sunjinwei._11_huisu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 分割回文串 返回所有的分割方案 力扣131 难度简单
 * 输入：s = "aab"
 * 输出：[["a","a","b"],["aa","b"]]
 */
public class _08_huiwen_str_partition {

    /**
     * 回溯：画图 类似多叉数的遍历【直接使用回溯模板即可】
     */
    public List<List<String>> partition(String s) {
        if (s == null) {
            return new ArrayList<>();
        }
        // 结果集合
        List<List<String>> res = new ArrayList<>();
        // 路径集合
        LinkedList<String> path = new LinkedList<>();
        backTrack(s, 0, res, path);
        return res;
    }

    private void backTrack(String s, int index, List<List<String>> res, LinkedList<String> path) {
        // 终止条件
        if (index == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (!isHuiwen(s, index, i)) {
                continue;
            }
            path.add(s.substring(index, i + 1));
            backTrack(s, i + 1, res, path);
            path.removeLast();
        }
    }

    private boolean isHuiwen(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 回溯优化：使用dp判断s[i..j]是否是回文串，代替双指针法判断!!! 秒啊
     */
    public List<List<String>> partition2(String s) {
        if (s == null) {
            return new ArrayList<>();
        }
        // 结果集合
        List<List<String>> res = new ArrayList<>();
        // 路径集合
        LinkedList<String> path = new LinkedList<>();
        // dp数组初始化 代替双指针判断是否是回文串
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = (j - i) <= 2 || dp[i + 1][j - 1];
                }
            }
        }
        backTrack2(s, 0, res, path, dp);
        return res;

    }

    private void backTrack2(String s, int index, List<List<String>> res, LinkedList<String> path, boolean[][] dp) {
        // 终止条件
        if (index == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (!dp[index][i]) {
                continue;
            }
            path.add(s.substring(index, i + 1));
            backTrack2(s, i + 1, res, path, dp);
            path.removeLast();
        }

    }


}
