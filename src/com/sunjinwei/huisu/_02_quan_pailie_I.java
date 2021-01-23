package com.sunjinwei.huisu;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 全排列 回溯算法（dfs）：给定一个没有重复数字的序列，返回其所有可能的全排列。力扣46
 * <p>
 * 请大家做了一些回溯算法的问题以后顺便思考一下：深度优先遍历、递归、栈，它们三者的关系，
 * 我个人以为它们背后统一的逻辑都是「后进先出」。完成练习有助于我们深刻理解算法思想，我们加油！
 */
public class _02_quan_pailie_I {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        // 使用队列 移除最后一个元素比较方便
        LinkedList<Integer> list = new LinkedList<>();
        // 第一种方式
        // huisu(nums, list);

        // 第二种方式
        //huisu_02(nums, list, 0, nums.length - 1);

        // 第三种方式
       /* boolean[] used = new boolean[nums.length];
        huisu_03(nums, list, used);*/

        // 第四种方式
        boolean[] used04 = new boolean[nums.length];
        huisu_04(nums, list, used04, 0);

        return result;
    }

    /**
     * 方法1：
     * 执行用时：2 ms, 在所有 Java 提交中击败了58.61%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了78.72%的用户
     *
     * @param nums
     * @param list
     */
    private void huisu(int[] nums, Deque<Integer> list) {
        // 递归终止条件
        if (nums.length == list.size()) {
            /**
             * 在java中，参数传递是值传递，对象类型变量在传参的过程中，复制的是变量的地址。
             * 这些地址被添加到res变量，但实际上指向的是同一块内存地址，因此我们会看到6个空的列表对象。
             * 解决的方法很简单，在 res.add(path); 这里做一次拷贝即可。
             *
             */
            result.add(new ArrayList<>(list));
            return;
        }
        for (int num : nums) {
            // 做选择 剪枝
            if (list.contains(num)) {
                continue;
            }
            list.add(num);
            huisu(nums, list);
            // 撤销选择 撤销的是list的最后一个元素
            list.removeLast();
        }
    }

    /**
     * 方法二：自己想出来的方法
     * 执行用时：2 ms, 在所有 Java 提交中击败了58.61%的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了96.93%的用户
     *
     * @param nums
     * @param list
     * @param start
     * @param end
     */
    private void huisu_02(int[] nums, Deque<Integer> list, int start, int end) {
        // 结束条件
        if (list.size() == nums.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        // 选择列表
        for (int i = start; i <= end; i++) {
            // 这里使用contains 不是很高效 需要O(N)的复杂度
            if (list.contains(nums[i])) {
                continue;
            }
            // 做选择
            list.add(nums[i]);
            // 递归
            huisu_02(nums, list, 0, end);
            // 撤销选择
            list.removeLast();
        }
    }

    /**
     * 方法三：题解中的 使用boolean数组保存是否使用的状态,进行递归的时候 将使用状态带进去 这样就不需要使用contains方法了，以空间换时间
     * 执行用时：2 ms, 在所有 Java 提交中击败了58.61%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了61.11%的用户
     */
    private void huisu_03(int[] nums, LinkedList<Integer> list, boolean[] used) {
        if (nums.length == list.size()) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                // 选择
                list.add(nums[i]);
                // 使用
                used[i] = true;
                // 递归
                huisu_03(nums, list, used);
                // 撤销使用
                used[i] = false;
                list.removeLast();
            }
        }
    }

    /**
     * 方法四：题解中的 使用boolean数组保存是否使用的状态,进行递归的时候 将使用状态带进去 这样就需要使用contains方法了，以空间换时间
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了97.28%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了79.86%的用户
     */
    private void huisu_04(int[] nums, LinkedList<Integer> list, boolean[] used, int start) {
        if (nums.length == list.size()) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            if (!used[i]) {
                // 选择
                list.add(nums[i]);
                // 使用
                used[i] = true;
                // 递归
                huisu_04(nums, list, used, 0);
                // 撤销使用
                used[i] = false;
                list.removeLast();
            }
        }
    }


    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 3};
        _02_quan_pailie_I quanPailie = new _02_quan_pailie_I();
        List<List<Integer>> permute = quanPailie.permute(ints);
        System.out.println(permute.toString());


    }

}
