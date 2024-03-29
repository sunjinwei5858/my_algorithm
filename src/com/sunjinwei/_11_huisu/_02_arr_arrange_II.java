package com.sunjinwei._11_huisu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 全排列的变种47：给定一个可包含重复数字的序列nums，按任意顺序 返回所有不重复的全排列
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 * [1,2,1],
 * [2,1,1]]
 * 示例 2：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * 求组合 求子集也有去重问题，套路也是一样，将数组进行排序
 */
public class _02_arr_arrange_II {

    List<List<Integer>> result = new ArrayList<>();

    /**
     * 方法1：看的题解
     * 先将数组进行排序，保证相同的数字是相邻的
     * 执行用时：3 ms, 在所有 Java 提交中击败了34.73%的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了75.30%的用户
     *
     * @param nums
     */
    public List<List<Integer>> permuteUnique1(int[] nums) {

        LinkedList<Integer> list = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        // 排序
        Arrays.sort(nums);
        backTrack(nums, list, used);
        return result;
    }

    private void backTrack(int[] nums, LinkedList<Integer> list, boolean[] used) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
        }
        for (int i = 0; i < nums.length; i++) {
            // 剪枝1: 如果使用过 跳过 不需要选择
            if (used[i]) {
                continue;
            }
            // used[i - 1] == true，说明同⼀树⽀nums[i - 1]使⽤过
            // used[i - 1] == false，说明同⼀树层nums[i - 1]使⽤过 树层去重 效率高

            // 剪枝2: 如果i大于1 并且 i-1和i的值相同，并且i-1没有被使用过 相同元素 都需要跳过 不需要选择
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            // 如果改成下面的 也是正确的 这是为什么呢？
            /*if (i > 0 && nums[i] == nums[i - 1] && used[i - 1]) {
                continue;
            }*/
            // 做选择
            used[i] = true;
            list.add(nums[i]);
            // 递归
            backTrack(nums, list, used);
            // 撤销选择
            used[i] = false;
            list.removeLast();
        }
    }


    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 1};
        _02_arr_arrange_II quanPailie = new _02_arr_arrange_II();
        List<List<Integer>> permute = quanPailie.permuteUnique1(ints);
        System.out.println(permute.toString());


    }

}
