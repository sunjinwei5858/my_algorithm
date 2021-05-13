package com.sunjinwei.test;

import java.util.Arrays;

/**
 * @program: com.sunjinwei.test
 * @author: sun jinwei
 * @create: 2021-05-13 08:08
 * @description: 最长递增子序列
 **/
public class _22_longest_sub_sequence_increasing {

    /**
     * 方法1：动态规划
     * <p>
     * 状态定义: dp[i]是以arr[i]结尾的数组最长的子序列长度
     * 状态初始化：单个元素就是1 所以应该初始化为1
     * 状态方程: arr[i]和arr[i+1]进行比较
     *
     * @param arr
     * @return
     */
    public int longestIncreasingSequence(int[] arr) {

        // 状态定义
        int[] dp = new int[arr.length];
        // 状态初始化
        Arrays.fill(dp, 1);
        // 状态方程
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                // 保证递增
                if (arr[i] > arr[j]) {
                    // 因为dp[i]有初始值 当加入arr[i]时 需要比较取最大
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}