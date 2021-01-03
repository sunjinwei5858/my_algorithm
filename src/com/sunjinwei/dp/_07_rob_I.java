package com.sunjinwei.dp;

/**
 * 打家劫舍I 力扣198 难度：简单 线性排列 动态规划的入门题目
 * 描述：
 * 你是一个专业的小偷，计划偷窃沿街的房屋。
 * 每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，
 * 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你不触动警报装置的情况下，一夜之内能够偷窃到的最高金额。
 */
public class _07_rob_I {

    /**
     * 动态规划解法: 一维数组 O(n)
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        // 特殊条件1
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 特殊条件2
        if (nums.length == 1) {
            return nums[0];
        }
        // 状态定义：dp[i]代表 数组长度为i的最高金额
        int m = nums.length;
        int[] dp = new int[m];
        // base case 状态初始化
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        // 状态转移方程
        for (int i = 2; i < m; i++) {
            // 选择nums[i]
            int s1 = dp[i - 2] + nums[i];
            // 不选择nums[i]
            int s2 = dp[i - 1];
            dp[i] = Math.max(s1, s2);

        }
        return dp[m - 1];
    }

    /**
     * 降低维度 O(1)
     * <p>
     * 考虑到每间房屋的最高总金额只和该房屋的前两间房屋的最高总金额相关，因此可以使用滚动数组，在每个时刻只需要存储前两间房屋的最高总金额。
     *
     * @param nums
     * @return
     */
    public int rob_02(int[] nums) {
        // 特殊条件1
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 特殊条件2
        if (nums.length == 1) {
            return nums[0];
        }
        // 状态定义：dp[i]代表 数组长度为i的最高金额
        int m = nums.length;
        // base case 状态初始化
        int pre = nums[0];
        int curr = Math.max(nums[0], nums[1]);
        // 状态转移方程
        for (int i = 2; i < m; i++) {
            // 选择nums[i]
            int temp = curr;
            curr = Math.max(pre + nums[i], curr);
            pre = temp;
        }
        return curr;
    }


    /**
     * 解法三：递归
     *
     * @param nums
     */
    public int rob_03(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return digui(nums, 0);
    }

    private int digui(int[] nums, int index) {
        // 终止条件
        if (index >= nums.length) {
            return 0;
        }
        // 选择 去下下间房子
        int s1 = nums[index] + digui(nums, index + 2);
        // 不选择 去下间房子
        int s2 = digui(nums, index + 1);
        return Math.max(s1, s2);
    }


    public static void main(String[] args) {
        int[] array = {2, 7, 9, 1, 3};
        _07_rob_I rob_i = new _07_rob_I();
        System.out.println(rob_i.rob(array));

    }
}
