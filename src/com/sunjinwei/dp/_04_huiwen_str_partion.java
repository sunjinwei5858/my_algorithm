package com.sunjinwei.dp;

import java.util.*;

/**
 * 分割回文串 力扣131 难度：简单
 * <p>
 * 描述：给定一个字符串s，将s分割成一些子串，使每个子串都是回文串。
 * 返回s所有可能的分割方案。
 */
public class _04_huiwen_str_partion {

    private List<List<String>> result = new ArrayList<>();

    /**
     * 方法1：回溯
     *
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {

        // 边界条件1 鲁棒性
        if (s == null || s.length() == 0) {
            return result;
        }
        // 队列
        Deque<String> path = new LinkedList<>();
        // 因为截取字符串是消耗性能的，因此，采用传子串索引的方式判断一个子串是否是回文子串
        backTracking(s, path, 0, s.length());
        return result;
    }

    private void backTracking(String s, Deque<String> path, int start, int length) {

        // 终止条件
        if (start == length) {
            result.add(new ArrayList<>(path));
            return;
        }
        // 字符串遍历
        for (int i = start; i < length; i++) {
            String substring = s.substring(start, i + 1);
            // 判断字符串从start到i位置是不是回文串 做选择
            // 不是的话 剪枝
            if (!isPlalindrome(substring)) {
                continue;
            }
            // 添加
            path.addLast(substring);
            // 递归
            backTracking(s, path, i + 1, length);
            // 回溯
            path.removeLast();
        }

    }

    /**
     * 抽取：判断字符串是不是回文串 双指针的方法
     */
    public boolean isPlalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        _04_huiwen_str_partion huiwen_create = new _04_huiwen_str_partion();
        List<List<String>> aab = huiwen_create.partition("aab");
        for (List<String> strings : aab) {
            System.out.println(strings.toString());
        }
    }

}
