package com.sunjinwei._13_huisu;

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
     * 自己的方法1：回溯 直接结果集contains 暴力判重 + 排序 + boolean记录状态
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
        // 暴力对结果集判重
        if (!result.contains(new ArrayList<>(path))) {
            result.add(new ArrayList(path));
        }
        for (int i = start; i < nums.length; i++) {
            if (path.contains(nums[i])) {
                continue;
            }
            // 如果路径包含该元素 跳过
            if (visited[i]) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && visited[i - 1]) {
                continue;
            }
            visited[i] = true;
            path.add(nums[i]);
            // 回溯
            subsets_help(nums, path, i + 1, visited);
            // 撤销选择
            visited[i] = false;
            path.removeLast();
        }
    }

    /**
     * 自己的方法2：直接暴力对结果集判重 不需要排序和boolean记录状态
     * 执行用时：8 ms, 在所有 Java 提交中击败了12.95%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了83.14%的用户 【加了Arrays.sort排序内存】
     * <p>
     * 不进行排序的执行效率情况：
     * 执行用时：8 ms, 在所有 Java 提交中击败了12.95%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了11.59%的用户
     *
     * @param nums
     */
    public List<List<Integer>> subsets_02(int[] nums) {
        // 鲁棒性1
        if (nums == null || nums.length == 0) {
            return result;
        }
        // 直接添加一个空数组
        result.add(new ArrayList<>());
        // 进行递归 回溯
        Arrays.sort(nums);
        // 3声明路径集合
        LinkedList<Integer> path = new LinkedList<>();
        // 4 递归回溯
        subsets_help_02(nums, path, 0);
        return result;
    }

    private void subsets_help_02(int[] nums, LinkedList<Integer> path, int start) {
        // 暴力对结果集判重
        if (!result.contains(new ArrayList<>(path))) {
            result.add(new ArrayList(path));
        }
        for (int i = start; i < nums.length; i++) {
            if (path.contains(nums[i])) {
                continue;
            }
            path.add(nums[i]);
            // 回溯
            subsets_help_02(nums, path, i + 1);
            // 撤销选择
            path.removeLast();
        }
    }

    /**
     * 题解的方法：和自己的对比 因为自己没有画出递归树 所以无法知道这个子集 其实可以当作树的前序遍历
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了84.77%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了58.03%的用户
     *
     * @param nums
     */
    public List<List<Integer>> subsets_03(int[] nums) {
        // 鲁棒性1
        if (nums == null || nums.length == 0) {
            return result;
        }
        // 声明路径集合
        LinkedList<Integer> path = new LinkedList<>();
        // 递归回溯
        subsets_help_03(nums, path, 0);
        return result;
    }

    private void subsets_help_03(int[] nums, LinkedList<Integer> path, int index) {
        // 其实子集就是树的前序遍历
        result.add(new ArrayList<>(path));
        for (int i = index; i < nums.length; i++) {
            // 做选择
            path.add(nums[i]);
            // 递归
            subsets_help_03(nums, path, i + 1);
            // 撤销选择
            path.removeLast();
        }

    }


    public static void main(String[] args) {
        _05_ziji_I ziji_i = new _05_ziji_I();
        int[] arr = new int[]{1, 2, 3};

        List<List<Integer>> subsets = ziji_i.subsets_03(arr);
        for (List<Integer> subset : subsets) {
            System.out.println(subset.toString());
        }

    }
}
