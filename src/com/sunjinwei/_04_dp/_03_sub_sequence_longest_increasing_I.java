package com.sunjinwei._04_dp;

import java.util.Arrays;

/**
 * 最长递增子序列【LIS】 力扣300
 * 给定一个无序的整数数组，找到其中最长递增子序列的长度。
 * 思想：主要学习 动态规划的核心思想-数学归纳法
 * 进阶1：返回子序列
 * 进阶2：时间复杂度为O(nlogn)
 */
public class _03_sub_sequence_longest_increasing_I {

    /**
     * 动态规划解法：时间复杂度为O(n2)
     * <p>
     * 状态定义：dp[i]表示以num[i]这个数结尾的最长递增子序列的长度，这是解决子序列问题的一个套路,一维。
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
     * 1将base case 放入外循环中
     * 2将获取最大长度放在for循环中处理
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS02(int[] nums) {
        // 状态定义：dp[i]代表最长子序列以nums[i]结尾 也就是nums[i]在这个子序列中
        int[] dpTable = new int[nums.length];
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            // 初始状态：默认填充1，每个元素都可以都至少可以单独成为子序列
            dpTable[i] = 1;
            for (int j = 0; j < i; j++) {
                // 开始处理 如何获取最长的递增子序列
                if (nums[i] > nums[j]) {
                    // 在[0,i-1]中寻找递增子序列长度最长的
                    dpTable[i] = Math.max(dpTable[i], dpTable[j] + 1);
                }
            }
            result = Math.max(result, dpTable[i]);
        }
        return result;
    }

    /**
     * 进阶1：获取最长子序列的子序列
     * 思路：
     * 1获取到dp数组 然后找到最长的 并且获取是在哪个索引
     * 2然后在一步步进行反推
     */
    public int[] lengthOfLIS03(int[] nums) {
        // 先获取到dp数组
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            // 状态初始化
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                // 处理最长递增子序列
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        // 获取dp数组的最大值和对应的索引
        int maxLen = 0;
        int index = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                index = i;
            }
        }
        // 初始化子序列
        int[] resArr = new int[maxLen];
        maxLen--;
        resArr[maxLen] = nums[index];
        // 根据index反推 需要满足两个条件
        for (int i = index; i >= 0; i--) {
            // 条件1：保证递增 index更大
            // 条件2：dp[index] = dp[i]+1 或者dp[i] = dp[index] -1
            if (nums[i] < nums[index] && dp[index] == dp[i] + 1) {
                maxLen--;
                resArr[maxLen] = nums[i];
                // 条件3：即时更新index
                index = i;
            }
        }
        return resArr;
    }


    /**
     * 进阶2:  todo 使用二分搜索
     *
     * @param nums
     */
    public int lengthOfLIS04(int[] nums) {
        return 0;
    }


    public static void main(String[] args) {
        _03_sub_sequence_longest_increasing_I longestIncreasingI = new _03_sub_sequence_longest_increasing_I();
        int[] arr = new int[]{1, 4, 3, 6, 8};
        int[] lis03 = longestIncreasingI.lengthOfLIS03(arr);
        System.out.println(Arrays.toString(lis03));
    }

}
