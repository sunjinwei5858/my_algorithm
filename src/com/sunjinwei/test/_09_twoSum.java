package com.sunjinwei.test;

import java.util.HashMap;

/**
 * 1两数之和
 * <p>
 * 给定一个整数数组nums和一个整数目标值target，请你在该数组中找出和为目标值的那两个整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 * <p>
 * 167两数之和变种II 输入有序数组
 */
public class _09_twoSum {

    /**
     * 1 使用哈希表
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {

        // key为值 value为索引
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashMap.containsKey(target - nums[i])) {
                return new int[]{hashMap.get(target - nums[i]), i};
            }
            hashMap.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }


    /**
     * 167 先排序 后使用双指针，但是这样会改变元素的索引, 所以这样的做法的前提是数组有序
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;
        int sum = 0;
        // 双指针
        while (left < right) {
            sum = nums[left] + nums[right];
            if (target == sum) {
                return new int[]{left, right};
            } else if (target > sum) {
                left++;
            } else {
                right--;
            }

        }
        return new int[]{-1, -1};
    }
}
