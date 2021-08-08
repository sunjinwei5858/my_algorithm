package com.sunjinwei._11_huisu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合综合III, 力扣216
 * 找出所有相加之和为n的k个数的组合。组合中只允许含有1- 9的正整数，并且每种组合中不存在重复的数字。
 * 本题就是在[1,2,3,4,5,6,7,8,9]这个集合中找到和为n的k个数的组合。
 */
public class _06_zuhe_sum_III {

    public List<List<Integer>> res = new ArrayList<>();

    /**
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.1 MB, 在所有 Java 提交中击败了38.51%的用户
     *
     * @param k 集合大小
     * @param n 数字范围
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {

        process(n, k, new LinkedList(), 1);
        return res;
    }

    private void process(int sum, int k, LinkedList<Integer> path, int startIndex) {
        // 剪枝 也是终止条件
        if (sum < 0) {
            return;
        }
        // 终止条件2
        if (sum == 0) {
            if (path.size() == k) {
                res.add(new ArrayList<>(path));
            }
            return;
        }
        for (int i = startIndex; i < 10; i++) {
            path.add(i);
            process(sum - i, k, path, i + 1);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        _06_zuhe_sum_III zuheSumIii = new _06_zuhe_sum_III();

        int k = 3;
        int n = 7;

        List<List<Integer>> res = zuheSumIii.combinationSum3(k, n);
        for (List<Integer> re : res) {
            System.out.println(re.toString());
        }

    }

}
