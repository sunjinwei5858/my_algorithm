package com.sunjinwei._13_huisu;

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
 */
public class _02_quan_pailie_II {

    List<List<Integer>> result = new ArrayList<>();


    public List<List<Integer>> permuteUnique(int[] nums) {

        LinkedList<Integer> list = new LinkedList<>();

        // 声明布尔数组 记录元素的使用状态
        boolean[] used = new boolean[nums.length];

        huisu_01(nums, list, used);

        return result;
    }

    /**
     * 自己的方法：不重复的数列 直接使用contains判断
     * 执行用时：825 ms, 在所有 Java 提交中击败了5.06%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了96.71%的用户
     *
     * @param nums
     * @param list
     * @param used
     */
    private void huisu_01(int[] nums, LinkedList<Integer> list, boolean[] used) {
        // 结束条件：还需要加上的条件是 不重复 直接使用contains
        // 一个比较容易想到的办法是在结果集中去重。但是问题来了，这些结果集的元素是一个又一个列表，对列表去重不像用哈希表对基本元素去重那样容易。
        if (list.size() == nums.length && !result.contains(new ArrayList<>(list))) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 如果没有使用
            if (!used[i]) {
                // 做选择
                used[i] = true;
                list.add(nums[i]);
                // 递归
                huisu_01(nums, list, used);
                // 撤销选择
                used[i] = false;
                list.removeLast();
            }
        }
    }


    /**
     * 方法2：看的题解
     * 先将数组进行排序，保证相同的数字是相邻的
     * 执行用时：3 ms, 在所有 Java 提交中击败了34.73%的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了75.30%的用户
     *
     * @param nums
     */
    public List<List<Integer>> permuteUnique_02(int[] nums) {

        LinkedList<Integer> list = new LinkedList<>();
        boolean[] used = new boolean[nums.length];
        // 重新对数据进行排序
        Arrays.sort(nums);
        huisu_02(nums, list, used);
        return result;
    }

    private void huisu_02(int[] nums, LinkedList<Integer> list, boolean[] used) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
        }
        for (int i = 0; i < nums.length; i++) {
            // 剪枝1: 如果使用过 跳过 不需要选择
            if (used[i]) {
                continue;
            }
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
            huisu_02(nums, list, used);
            // 撤销选择
            used[i] = false;
            list.removeLast();
        }
    }


    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 1};
        _02_quan_pailie_II quanPailie = new _02_quan_pailie_II();
        List<List<Integer>> permute = quanPailie.permuteUnique_02(ints);
        System.out.println(permute.toString());


    }

}
