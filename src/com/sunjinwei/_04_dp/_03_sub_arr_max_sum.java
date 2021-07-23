package com.sunjinwei._04_dp;

/**
 * 和最大子数组/最大子序和 力扣53 难度：简单  【子数组 必须连续】
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 关键字：和(递增)，连续
 * 最大子数组问题和前文讲过的 经典动态规划：最长递增子序列 的套路非常相似，代表着一类比较特殊的动态规划问题的思路
 */
public class _03_sub_arr_max_sum {

    /**
     * 错误写法
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        // 依然使用数学归纳法来找状态转移关系：假设我们已经算出了 _04_dp[i-1]，如何推导出 _04_dp[i] 呢？
        for (int i = 1; i <= nums.length - 1; i++) {
            // 不能单纯的使用if判断是不是大于0 而是应该取最大值
            if (nums[i] > 0) {
                dp[i] = nums[i] + dp[i - 1];
            } else {
                dp[i] = nums[i];
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 正确解法：
     * 分析：和最长递增子序列的思想一致
     * 按照常规的状态定义：nums[0..i] 中的「最大的子数组和」为 _04_dp[i]。
     * 如果这样定义的话，整个 nums 数组的「最大子数组和」就是 _04_dp[n-1]。如何找状态转移方程呢？按照数学归纳法，假设我们知道了 _04_dp[i-1]，如何推导出 _04_dp[i] 呢？
     * 实际上是不行的，因为子数组要连续，但是常规的定义，不能保证nums[0...i]中的最大子数组与nums[i+1]是连续的，如果是负数 那么不会进行相加 也就不连续了
     * <p>
     * ==》重新定义状态：以nums[i]的值为结尾的最大子数组和为dp[i]
     * 这种定义之下，想得到整个 nums 数组的「最大子数组和」，不能直接返回 _04_dp[n-1]，而需要遍历整个 _04_dp 数组。
     *
     * @param nums
     * @return
     */
    public int maxSubArray_02(int[] nums) {
        // 非常规的动态规划 状态定义：以nums[i]的值为结尾的最大子数组之和为dp[i]
        int[] dp = new int[nums.length];
        // base case 初始化
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
        }
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    /**
     * 可以进行优化 第二个for循环可以去掉
     *
     * @param nums
     * @return
     */
    public int maxSubArray_03(int[] nums) {
        // 非常规的动态规划 状态定义：以nums[i]的值为结尾的最大子数组之和为dp[i]
        int[] dp = new int[nums.length];
        // base case 初始化
        dp[0] = nums[0];
        int result = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    /**
     * 贪心算法实现
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了94.68%的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了76.06%的用户
     */
    public int maxSubArray04(int[] nums) {
        int max = nums[0];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            max = Math.max(max, sum);
            // 如果sum<0 那么更新sum为0  因为sum是负数 那么加任何数 都是递减的
            if (sum <= 0) {
                sum = 0;
            }
        }
        return max;
    }


    public static void main(String[] args) {
        _03_sub_arr_max_sum arrMaxSum = new _03_sub_arr_max_sum();
        int[] arr = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int i = arrMaxSum.maxSubArray(arr);
        System.out.println(i);
    }


}
