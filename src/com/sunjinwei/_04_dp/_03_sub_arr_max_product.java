package com.sunjinwei._04_dp;


/**
 * 力扣152 乘积最大子数组    【子数组 必须连续】
 * <p>
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的【连续】子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 条件：1子数组必须连续 2乘积最大
 */
public class _03_sub_arr_max_product {

    /**
     * 解法一：动态规划
     * <p>
     * 最大值dp
     * 最小值dp
     * 这题是求数组中子区间的最大乘积，对于乘法，我们需要注意，负数乘以负数，会变成正数，
     * 所以解这题的时候我们需要维护两个变量，当前的最大值，以及最小值，最小值可能为负数，但没准下一步乘以一个负数，当前的最大值就变成最小值，而最小值则变成最大值了。
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        // 状态定义：nums[i]的值结尾的乘积最小的dpmin
        int[] dpmin = new int[nums.length];
        // 状态定义：nums[i]的值结尾的乘积最大的dpmax
        int[] dpmax = new int[nums.length];
        // base case
        dpmin[0] = nums[0];
        dpmax[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // 有三种情况
            // 1如果nums[i]为负数 那么最大值就是nums[i]和最小值的乘积，
            // 2如果是正数 那么就是nums[i]和最大值的乘积
            // 3都没有当前的nums[i]大
            dpmax[i] = max(dpmin[i - 1] * nums[i], dpmax[i - 1] * nums[i], nums[i]);
            dpmin[i] = min(dpmax[i - 1] * nums[i], dpmin[i - 1] * nums[i], nums[i]);
        }
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < dpmax.length; i++) {
            result = Math.max(result, dpmax[i]);
        }
        return result;
    }

    private int min(int a, int b, int c) {
        int min = Math.min(a, b);
        return Math.min(min, c);
    }

    private int max(int a, int b, int c) {
        int max = Math.max(a, b);
        return Math.max(max, c);
    }
}
