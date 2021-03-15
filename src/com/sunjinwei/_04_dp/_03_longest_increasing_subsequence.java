package com.sunjinwei._04_dp;

import java.util.Arrays;

/**
 * 力扣300 最长递增子序列【LIS】【一维】 非常经典的动态规划算法题：给定一个无序的整数数组，找到其中最长递增子序列的长度。
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * 关键字：可以不连续 但要保证顺序
 *
 * 思想：主要学习 动态规划的核心思想-数学归纳法
 * <p>
 * 可能会有多种最长上升子序列的组合，只需要输出对应的长度即可
 * 子序列不一定是连续的，子串一定是连续的，子数组也一定是连续的
 * <p>
 * 数学归纳法：先假设这个结论在k<n时成立，然后根据这个假设，想办法推导证明出k=n的时候 此结论也是成立的
 * 类似：设计动态规划算法，不是需要一个dp数组嘛，可以假设dp[0,....,i-1]都已经算出来了，怎么通过这些结果算出dp[i]?
 * 首先要清楚dp数组的含义，即dp[i]的值代表什么？
 */
public class _03_longest_increasing_subsequence {

    /**
     * 非常规的动态规划
     * 状态定义：dp数组，_04_dp[i]表示以num[i]这个数结尾的最长递增子序列的长度，这是解决子序列问题的一个套路,一维。
     * 状态转移方程：数学归纳法 假设[0,..,i-1]已经算出来了，那么dp[i]:看num[i]和num[i-1]的大小，
     * 如果num[i]比num[i-1]更大，那么dp[i]=max(_04_dp[i],_04_dp[i-1]+1)
     * 如果num[i]比num[i-1]更小，直接跳过，因为递增序列不成立
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        // 状态定义：dptable 一维 ，以num[i]这个数结尾的最长递增子序列的长度
        int[] dpTable = new int[nums.length];
        // base case：默认填充1，每个元素都可以都至少可以单独成为子序列
        Arrays.fill(dpTable, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dpTable[i] = Math.max(dpTable[i], dpTable[j] + 1);
                }
            }
        }
        // 找出dptable数组的最大值
        int result = 0;
        for (int i = 0; i < dpTable.length; i++) {
            result = Math.max(result, dpTable[i]);
        }
        return result;
    }

    /**
     * 优化写法
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS02(int[] nums) {
        // 状态定义：dptable 一维 ，_04_dp[i]的值代表nums前i个数字的最长子序列长度
        int[] dpTable = new int[nums.length];
        // 初始状态：默认填充1，每个元素都可以都至少可以单独成为子序列
        Arrays.fill(dpTable, 1);
        // 找出dptable数组的最大值
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dpTable[i] = Math.max(dpTable[i], dpTable[j] + 1);
                }
            }
            result = Math.max(result, dpTable[i]);
        }
        return result;
    }
}
