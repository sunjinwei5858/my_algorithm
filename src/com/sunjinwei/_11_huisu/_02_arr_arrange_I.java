package com.sunjinwei._11_huisu;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 全排列 回溯算法（dfs）：给定一个没有重复数字的序列，返回其所有可能的全排列。力扣46
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 这⾥和组合问题、切割问题和⼦集问题最⼤的不同就是for循环⾥不⽤startIndex了。
 * 因为排列问题，每次都要从头开始搜索，例如元素1在[1,2]中已经使⽤过了，但是在[2,1]中还要再使⽤⼀次1。
 * ⽽used数组，其实就是记录此时path⾥都有哪些元素使⽤了，⼀个排列⾥⼀个元素只能使⽤⼀次。
 * 总结：
 * ⼤家此时可以感受出排列问题的不同：
 * 1每层都是从0开始搜索⽽不是startIndex
 * 2需要used数组记录path⾥都放了哪些元素了
 */
public class _02_arr_arrange_I {

    List<List<Integer>> result = new ArrayList<>();

    /**
     * 方法1：
     * 执行用时：2 ms, 在所有 Java 提交中击败了58.61%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了78.72%的用户
     */
    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> list = new LinkedList<>();
        backTrack(nums, list);
        return result;
    }

    private void backTrack(int[] nums, Deque<Integer> path) {
        // 递归终止条件
        if (nums.length == path.size()) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int num : nums) {
            // 做选择 剪枝
            if (path.contains(num)) {
                continue;
            }
            path.add(num);
            backTrack(nums, path);
            // 撤销选择 撤销的是list的最后一个元素
            path.removeLast();
        }
    }


    /**
     * 方法2:
     * 使用boolean数组保存是否使用的状态,进行递归的时候 将使用状态带进去 这样就不需要使用contains方法了，以空间换时间
     * 执行用时：2 ms, 在所有 Java 提交中击败了58.61%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了61.11%的用户
     */
    public List<List<Integer>> permute2(int[] nums) {
        // 使用boolean数组
        backTrack2(nums, new LinkedList<>(), new boolean[nums.length]);
        return result;
    }

    private void backTrack2(int[] nums, LinkedList<Integer> path, boolean[] used) {
        if (nums.length == path.size()) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            // 做选择
            path.add(nums[i]);
            // 使用
            used[i] = true;
            // 递归
            backTrack2(nums, path, used);
            // 撤销选择
            used[i] = false;
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 3};
        _02_arr_arrange_I quanPailie = new _02_arr_arrange_I();
        List<List<Integer>> permute = quanPailie.permute(ints);
        System.out.println(permute.toString());


    }

}
