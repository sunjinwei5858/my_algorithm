package com.sunjinwei._11_huisu;

/**
 * 力扣494 目标和
 * 给你一个整数数组 nums 和一个整数 target 。
 * <p>
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * <p>
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 */
public class _06_target_sum {

    public int res = 0;

    /**
     * 回溯解法
     * 执行用时：438 ms, 在所有 Java 提交中击败了40.50%的用户
     * 内存消耗：35.7 MB, 在所有 Java 提交中击败了91.02%的用户
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {

        process(nums, target, 0);
        return res;
    }

    /**
     * 回溯
     *
     * @param nums
     * @param target
     * @param startIndex
     */
    private void process(int[] nums, int target, int startIndex) {
        if (startIndex == nums.length) {
            if (target == 0) {
                res++;
            }
            return;
        }
        // !!! 这里其实就是二叉树的遍历
        process(nums, target - nums[startIndex], startIndex + 1);
        process(nums, target + nums[startIndex], startIndex + 1);
    }

    public static void main(String[] args) {
        _06_target_sum targetSum = new _06_target_sum();
        int[] arr = new int[]{1, 1, 1, 1};
        int target = 3;

        targetSum.findTargetSumWays(arr, target);


    }


}
