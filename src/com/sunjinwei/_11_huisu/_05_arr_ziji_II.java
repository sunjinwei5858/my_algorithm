package com.sunjinwei._11_huisu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 子集问题II变种： 力扣90
 * <p>
 * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 * 示例:
 * 输入: [1,2,2]
 * 输出:
 * [
 * [2],
 * [1],
 * [1,2,2],
 * [2,2],
 * [1,2],
 * []
 * ]
 */
public class _05_arr_ziji_II {

    private List<List<Integer>> result = new ArrayList<>();

    /**
     * 自己AC出来的方法：
     * 执行用时：2 ms, 在所有 Java 提交中击败了51.76%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了90.22%的用户
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null || nums.length == 0) {
            return result;
        }
        // 先排序 因为这次有重复的元素
        Arrays.sort(nums);
        // 记录状态
        boolean[] visited = new boolean[nums.length];
        // 路径
        LinkedList<Integer> path = new LinkedList<>();
        backTrack1(nums, 0, visited, path);
        return result;
    }

    /**
     * 第一种去重方法：使用visited数组
     *
     * @param nums
     * @param start
     * @param visited
     * @param path
     */
    private void backTrack1(int[] nums, int start, boolean[] visited, LinkedList<Integer> path) {
        result.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            // 进行去重，方法1
            // 为什么是 !visited[i - 1]: 因为要判重 肯定是一圈都走完了 那么所有的元素visited都是true了
            // 此时走到撤销选择 改为false，那么重新进入 !visited[i - 1] 负负得正 就为true了 说明该元素已经使用过了
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            // 做选择
            path.add(nums[i]);
            visited[i] = true;
            // 递归
            backTrack1(nums, i + 1, visited, path);
            // 撤销选择
            visited[i] = false;
            path.removeLast();
        }
    }

    /**
     * 方法2：不使用visited数组
     * 执行用时：2 ms, 在所有 Java 提交中击败了51.76%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了69.32%的用户
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return result;
        }
        // 先排序 因为这次有重复的元素
        Arrays.sort(nums);
        // 路径
        LinkedList<Integer> path = new LinkedList<>();
        backTrack2(nums, 0, path);
        return result;
    }


    private void backTrack2(int[] nums, int start, LinkedList<Integer> path) {
        result.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            // 去重
            // i > start 保证了
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            // 做选择
            path.add(nums[i]);
            // 递归
            backTrack2(nums, i + 1, path);
            // 撤销选择
            path.removeLast();
        }
    }


    public static void main(String[] args) {
        _05_arr_ziji_II ziji_ii = new _05_arr_ziji_II();
        int[] arr = new int[]{1, 2, 2};
        List<List<Integer>> lists = ziji_ii.subsetsWithDup(arr);
        for (List<Integer> list : lists) {
            System.out.println(list.toString());
        }
    }
}
