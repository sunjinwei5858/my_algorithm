package com.sunjinwei.bfs_dfs_huisu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 力扣78 子集
 * 给你一个整数数组 nums ，返回该数组所有可能的子集（幂集）。解集不能包含重复的子集。力扣78
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 */
public class _05_ziji_I {

    private List<List<Integer>> result = new ArrayList<>();

    /**
     * 方法1：回溯
     * 思路：解集不能包含重复的子集 也就是 不能重复 有一种办法先对数组进行排序
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        // 鲁棒性1
        if (nums == null || nums.length == 0) {
            return result;
        }
        // 直接添加一个空数组
        result.add(new ArrayList<>());
        // 进行递归 回溯
        // 1先对数组进行排序
        Arrays.sort(nums);

        boolean[] visited = new boolean[nums.length];
        // 3声明路径集合
        LinkedList<Integer> path = new LinkedList<>();
        // 4 递归回溯
        subsets_help(nums, path, 0, visited);
        return result;
    }

    private void subsets_help(int[] nums, LinkedList<Integer> path, int start, boolean[] visited) {
        // 终止条件
        if (start == nums.length) {
            result.add(new ArrayList(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            // 如果路径包含该元素 跳过
            /*if (visited[i]) {
                continue;
            }*/
            visited[i] = true;
            path.add(nums[i]);
            // 回溯
            subsets_help(nums, path, i + 1, visited);
            // 撤销选择
            visited[i] = false;
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        _05_ziji_I ziji_i = new _05_ziji_I();
        int[] arr = new int[]{1, 2, 3};

        List<List<Integer>> subsets = ziji_i.subsets(arr);
        for (List<Integer> subset : subsets) {
            System.out.println(subset.toString());
        }

    }
}
