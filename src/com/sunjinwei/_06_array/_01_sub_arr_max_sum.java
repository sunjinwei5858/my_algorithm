package com.sunjinwei._06_array;

/**
 * 变种1：
 * 力扣1800 难度：简单
 * 给你一个正整数组成的数组nums，返回nums中一个升序子数组的最大可能元素和。
 * 关键词：正数组成，子数组要升序
 */
public class _01_sub_arr_max_sum {

    /**
     * 当元素递减时，sum重新赋值
     * 当元素递增时，sum+=
     *
     * @param nums
     * @return
     */
    public int maxAscendingSum(int[] nums) {

        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = nums[0];
        int max = sum;
        // 索引从index=1开始遍历
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                // 如果是升序 那么就累加
                sum += nums[i];
            } else {
                // 如果是降序 那么sum就重新赋值
                sum = nums[i];
            }
            max = Math.max(sum, max);
        }
        return max;
    }


}
