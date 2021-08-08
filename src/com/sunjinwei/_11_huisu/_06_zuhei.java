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
 * [[2,4],[3,4],[2,3],[1,2],[1,3],[1,4]]
 */
public class _06_zuhei {

    private List<List<Integer>> result = new ArrayList<>();

    /**
     * 方法1：
     * 执行用时：18 ms, 在所有 Java 提交中击败了58.71%的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了95.84%的用户
     *
     * @param n
     * @param k
     */
    public List<List<Integer>> combine(int n, int k) {
        // 声明放入元素的集合
        LinkedList path = new LinkedList<Integer>();
        // 进行回溯
        combine_help(n, k, path, 1);
        return result;
    }

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

    /**
     * 方法2：剪枝!!!!，比如 n = 5 ,k = 5 ,此时只需要遍历一次即可 不需要重复的递归处理了
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.98%的用户
     * 内存消耗：39.8 MB, 在所有 Java 提交中击败了48.94%的用户
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine2(int n, int k) {
        // 声明放入元素的集合
        LinkedList path = new LinkedList<Integer>();
        // 进行回溯
        backTrack(n, k, path, 1);
        return result;
    }

    private void backTrack(int n, int k, LinkedList path, int start) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        // 剪枝!!!! 极大的优化了时间复杂度
        for (int i = 0; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            backTrack(n, k, path, i + 1);
            path.removeLast();
        }

    }


    public static void main(String[] args) {
        _06_zuhei zuhei = new _06_zuhei();
        List<List<Integer>> combine = zuhei.combine(4, 4);
        for (List<Integer> integers : combine) {
            System.out.println(integers.toString());
        }
    }
}
