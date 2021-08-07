package com.sunjinwei._11_huisu;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 力扣40 变种 组合总和II【不可以重复选取相同的数字】
 * ==》
 * 1。使⽤过的元素不能重复选取
 *
 * <p>
 * 给定一个无重复元素的正整数数组 candidates 和一个正整数 target ，找出 candidates 中所有可以使数字和为目标数 target 的唯一组合。
 * candidates 中的每个数字在每个组合中只能使用一次。 
 * 对于给定的输入，保证和为 target 的唯一组合数少于 150 个。
 */
public class _06_zuhei_sum_II {

    public List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

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
            // 关键2：去重
            if (i > start && candidates[i - 1] == candidates[i]) {
                continue;
            }
            path.add(candidates[i]);
            // 关键3：不能选择重复的
            process1(candidates, target - candidates[i], i + 1, path);
            path.removeLast();
        }
    }


}
