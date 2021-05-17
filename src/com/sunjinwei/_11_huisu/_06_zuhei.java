package com.sunjinwei._11_huisu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合问题：力扣77
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 示例:
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 */
public class _06_zuhei {

    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        // 声明放入元素的集合
        LinkedList path = new LinkedList<Integer>();
        // 进行回溯
        combine_help(n, k, path, 1);
        return result;
    }

    /**
     * 自己的解法， ac出来了
     * 执行用时：18 ms, 在所有 Java 提交中击败了58.71%的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了95.84%的用户
     * @param n
     * @param k
     * @param path
     * @param start
     */
    private void combine_help(int n, int k, LinkedList path, int start) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i <= n; i++) {
            // 做选择
            path.add(i);
            // 递归
            combine_help(n, k, path, i + 1);
            // 撤销选择
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        _06_zuhei zuhei = new _06_zuhei();
        List<List<Integer>> combine = zuhei.combine(4, 2);
        for (List<Integer> integers : combine) {
            System.out.println(integers.toString());
        }
    }
}
