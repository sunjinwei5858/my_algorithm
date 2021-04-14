package com.sunjinwei._06_array;

import java.util.HashMap;

/**
 * 两数之和,返回这两个元素的索引!!!! 注意是索引!!!!
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 方法：哈希表
 * <p>
 */
public class _03_two_sum_I {

    /**
     * 方法1：哈希表
     *
     * @param nums
     * @param target
     * @return 返回元素的索引
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            hashMap.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int res = target - nums[i];
            //!!!! 这里需要判断不能重复
            // 数组中同一个元素在答案里不能重复出现
            if (hashMap.containsKey(res) && hashMap.get(res) != i) {
                return new int[]{hashMap.get(res), i};
            }
        }
        return new int[]{-1, -1};
    }

}
