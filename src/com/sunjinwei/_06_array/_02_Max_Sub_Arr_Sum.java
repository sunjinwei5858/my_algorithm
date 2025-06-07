package com.sunjinwei._06_array;

/**
 * @program: com.sunjinwei._06_array_02
 * @author: sun jinwei
 * @create: 2021-07-23 08:56
 * @description: 力扣53 最大子序和 难度 简单
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 这道题和二叉树的最大路径和 都是同一类型的变种个
 * 参考题解：
 * https://leetcode-cn.com/problems/maximum-subarray/solution/dai-ma-sui-xiang-lu-53-zui-da-zi-xu-he-b-xqus/
 **/
public class _02_Max_Sub_Arr_Sum {

    /**
     * 使用动态规划
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {

        // dp: 以nums[i]结尾的数组最大子序和
        int res = Integer.MIN_VALUE;

        int[] dp = new int[nums.length + 1];

        dp[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {

            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);

            res = Math.max(dp[i], res);
        }

        return res;
    }

    public static void main(String[] args) {
        _02_Max_Sub_Arr_Sum maxSubArrSum = new _02_Max_Sub_Arr_Sum();
        int[] arr = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int res = maxSubArrSum.maxSubArray(arr);
        System.out.println(res);
    }


}