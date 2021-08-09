package com.sunjinwei._11_huisu;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 力扣39 组合总和【可以重复选取相同的数字】
 * <p>
 * 给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，找出 candidates 中所有可以使数字和为目标数 target 的唯一组合。
 * candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的。 
 * 对于给定的输入，保证和为 target 的唯一组合数少于 150 个。
 */
public class _06_zuhe_sum_I {

    public List<List<Integer>> res = new ArrayList<>();

    /**
     * 题目要求的解法 [2,3,6,7] target=7 ==> [[2,2,3],[7]]
     * <p>
     * 注意本题和回溯算法：求组合问题！、回溯算法：求组合总和！的⼀个区别是：本题元素为可重复选取的。
     *
     * @param candidates
     * @param target
     */
    public List<List<Integer>> combinationSum1(int[] candidates, int target) {

        if (candidates == null || candidates.length == 0 || target <= 0) {
            return res;
        }
        process1(candidates, target, 0, new LinkedList());
        return res;
    }

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
            path.add(candidates[i]);
            // 关键点 不要i+1了，表示可以重复选取
            process1(candidates, target - candidates[i], i, path);
            path.removeLast();
        }
    }


    /**
     * 方法2：先排序 然后剪枝
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.90%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了51.80%的用户
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {

        if (candidates == null || candidates.length == 0 || target <= 0) {
            return res;
        }
        Arrays.sort(candidates);
        process2(candidates, target, 0, new LinkedList());
        return res;
    }


    /**
     * 优化上面的target<0判断 放入到for循环中去判断 可以减少递归次数
     *
     * @param candidates
     * @param target
     * @param start
     * @param path
     */
    private void process2(int[] candidates, int target, int start, LinkedList<Integer> path) {

        // 终止条件1
        if (target < 0) {
            return;
        }

        // 终止条件2：如果找到了 那么加入结果集
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            // 剪枝优化 将终止条件放入到for循环中处理
            if (candidates[i] > target) {
                continue;
            }
            path.add(candidates[i]);
            // 关键点 不要i+1了，表示可以重复选取当前的数
            process2(candidates, target - candidates[i], i, path);
            path.removeLast();
        }
    }


    public static void main(String[] args) {
        _06_zuhe_sum_I zuheiSum = new _06_zuhe_sum_I();

        int[] arr = new int[]{2, 3, 6, 7};

        List<List<Integer>> combinationSum = zuheiSum.combinationSum1(arr, 7);

        for (List<Integer> integers : combinationSum) {
            System.out.println(integers.toString());
        }
    }

}
