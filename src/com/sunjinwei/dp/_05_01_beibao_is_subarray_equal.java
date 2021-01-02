package com.sunjinwei.dp;

/**
 * 0-1背包问题 [物品可以装进背包也可以不装进背包]
 * 描述：给你一个可装载重量为w的背包和n个物品，每个物品有重量和价值两个属性
 * 第i个物品的重量为wt[i] 价值为val[i]，求最大价值
 * <p>
 * 力扣上的0-1背包问题：
 * <p>
 * 第416题：分割等和子集（中等）；
 * 第474题：一和零（中等）；
 * 第494题：目标和（中等）；
 * 第879题：盈利计划（困难）；
 * <p>
 * 力扣上的完全背包问题：
 * 第322题：零钱兑换（中等）；
 * 第518题：零钱兑换 II（中等）；
 * 第1449题：数位成本和为目标值的最大数字（困难）。
 * 这里要注意鉴别：力扣第377题，不是完全背包问题。
 */
public class _05_01_beibao_is_subarray_equal {

    /**
     * 01背包的思想运用之一：分割等和子集 力扣416
     * 描述：给定一个只包含正整数的非空数组，是否可以将这个数组分割成两个子集，使得两个子集的元素和相等
     * 注意：
     * 1.每个数组中的元素不会超过100
     * 2.数组的大小不会超过200
     * <p>
     * 给定一个只包含正整数的非空数组nums[]，判断是否可以从数组中选出一些数字，使得这些数字的和等于整个数组的元素和的一半。
     * 因此这个问题可以转换成0-1背包问题。这道题与传统的0-1背包问题的区别在于，
     * 传统的0-1背包问题要求选取的物品的重量之和不能超过背包的总容量，这道题则要求选取的数字的和恰好等于整个数组的元素和的一半。
     * 类似于传统的0-1背包问题，可以使用动态规划求解。
     * <p>
     * 思路：先将数组进行求和sum，如果是划分两个子集，那么每个子集的和就是sum/2,子集中的大小就是nums[i]
     * 转移为01背包问题：
     * 给一个可装载重量为sum/2的背包和N个物品，每个物品的重量为nums[i]。现在让你装物品，是否存在一种装法，能够恰好将背包装满？
     *
     * @param nums
     * @return
     */
    public boolean isSubArrayEqual(int[] nums) {
        int sum = 0;
        // 求和
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
        }
        // 判断是不是奇数
        if (sum % 2 != 0) {
            return false;
        }
        // 状态定义:[i][j]对于前i个物品，当前背包的容量为j时，如果为true 那么背包全部装满
        int n = nums.length;
        int w = sum / 2;
        boolean[][] dp = new boolean[n + 1][w + 1];
        // base case 初始化
        // i个物品 背包容量为0，也就是背包没有空间时 默认也是装满了
        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }
        // 0个物品 背包容量为j，没有物品肯定是不能装满的 所以为false
        // 可以省略这个处理 默认为false
        for (int j = 0; j < w; j++) {
            dp[0][j] = false;
        }
        // 状态转移方程
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                // 第i个物品的重量大于sum/2 也就是背包容量不足 不能放入背包
                if (nums[i - 1] > w) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 可以放入背包：第i个物品的重量小于sum/2 背包容量足够
                    // s1 代表 选择不放入背包
                    boolean s1 = dp[i - 1][j];
                    // s2 代表选择放入背包
                    boolean s2 = dp[i - 1][j - nums[i - 1]];
                    dp[i][j] = s1 || s2;
                }
            }
        }
        return dp[n][w];
    }

    /**
     * todo :还可以进一步优化：状态压缩，将二维转化为一维度
     *
     * 可以发现在计算dp的过程中，每一行的dp值都只与上一行的dp值有关，因此只需要一个一维数组
     * 且需要注意的是第二层的循环我们需要从大到小计算，因为如果我们从小到大更新dp值，那么在计算dp[j]值的时候，
     * dp[j−nums[i]]已经是被更新过的状态，不再是上一行的dp值。
     *
     * @param nums
     */
    public boolean isSubArrayEqual_02(int[] nums) {
        int sum = 0;
        // 求和
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
        }
        // 判断是不是奇数
        if (sum % 2 != 0) {
            return false;
        }
        int n = nums.length;
        int w = sum / 2;
        // 状态定义: 当前背包的容量为j时，如果为true 那么背包全部装满
        boolean[] dp = new boolean[w + 1];
        // base case 初始化 背包容量为0，也就是背包没有空间时 默认也是装满了
        dp[0] = true;
        // 状态转移方程
        for (int i = 1; i <= n; i++) {
            // 内循环需要从大到小更新
            // 因为如果从小到大 那么dp[j−nums[i]]已经是被更新过的状态，不再是上一行的dp值。
            for (int j = w; j >= 1; j--) {
                if (j >= nums[i - 1]) {
                    boolean s1 = dp[j];
                    boolean s2 = dp[j - nums[i - 1]];
                    dp[j] = s1 || s2;
                }
            }
        }
        return dp[w];
    }

    public static void main(String[] args) {
        _05_01_beibao_is_subarray_equal beibao = new _05_01_beibao_is_subarray_equal();
        System.out.println(beibao.isSubArrayEqual_02(new int[]{1, 2, 5}));
    }


}
