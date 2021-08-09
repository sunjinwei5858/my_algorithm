package com.sunjinwei._11_huisu;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 力扣40 变种 组合总和II【不可以重复选取相同的数字】
 * 这道题和39题的区别：
 * 1。数组中包含重复的元素
 * 2。不能有相同的组合
 */
public class _06_zuhe_sum_II {

    public List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum1(int[] candidates, int target) {

        if (candidates == null || candidates.length == 0 || target <= 0) {
            return res;
        }
        // 关键1：因为不能选取重复的数字 那么可以先排序
        Arrays.sort(candidates);
        process1(candidates, target, 0, new LinkedList());
        return res;
    }

    /**
     * @param candidates
     * @param target
     * @param start
     * @param path
     */
    private void process1(int[] candidates, int target, int start, LinkedList<Integer> path) {

        // 终止条件1：target<0
        if (target < 0) {
            return;
        }
        // 终止条件2：如果找到了 那么加入结果集
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            // 关键2：去重!!!!!
            if (i > start && candidates[i - 1] == candidates[i]) {
                continue;
            }
            path.add(candidates[i]);
            // 关键3：不能选择重复的
            process1(candidates, target - candidates[i], i + 1, path);
            path.removeLast();
        }
    }

    /**
     * 方法2：第二种去重方式，代码随想录，声明used数组，使用树层去重!!!! 大大提高效率!!!
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        if (candidates == null || candidates.length == 0 || target <= 0) {
            return res;
        }
        // 关键1：因为不能选取重复的数字 那么可以先排序
        Arrays.sort(candidates);
        process2(candidates, target, 0, new LinkedList(), new boolean[candidates.length]);
        return res;
    }

    private void process2(int[] candidates, int target, int startIndex, LinkedList path, boolean[] used) {

        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < candidates.length; i++) {
            // 去重：同一树层去重 user[i-1]=false && candidates[i-1]=candidates[i]
            if (i > 0 && candidates[i - 1] == candidates[i] && !used[i - 1]) {
                continue;
            }
            // 剪枝
            if (candidates[i] > target) {
                continue;
            }
            // 回溯
            path.add(candidates[i]);
            process2(candidates, target - candidates[i], i + 1, path, used);
            path.removeLast();
        }

    }


}
