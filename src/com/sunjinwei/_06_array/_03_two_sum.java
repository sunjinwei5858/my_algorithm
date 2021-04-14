package com.sunjinwei._06_array;

import java.util.HashMap;

/**
 * 两数之和
 * 方法1：哈希表
 * 方法2：双指针
 */
public class _03_two_sum {

    /**
     * 方法1：哈希表
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int res = target - nums[i];
            //!!!! 这里需要判断不能重复
            if (hashMap.containsKey(res) && hashMap.get(res) != i) {
                return new int[]{hashMap.get(res), i};
            }
        }
        return new int[]{-1, -1};
    }


    /**
     * 双指针法
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum2(int[] nums, int target) {
        // TODO: 2021/4/14
        return new int[0];
    }

}
