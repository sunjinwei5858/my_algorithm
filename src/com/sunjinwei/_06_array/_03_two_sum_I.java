package com.sunjinwei._06_array;

import java.util.Arrays;
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
            //!!!! 这里需要判断不能重复
            // 数组中同一个元素在答案里不能重复出现
            if (hashMap.containsKey(target - nums[i]) && hashMap.get(target - nums[i]) != i) {
                return new int[]{hashMap.get(target - nums[i]), i};
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 方法1：哈希表优化 一个for循环
     *
     * @param nums
     * @param target
     * @return 返回元素的索引
     */
    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            //!!!! 这里需要判断不能重复
            // 数组中同一个元素在答案里不能重复出现
            if (hashMap.containsKey(target - nums[i])) {
                return new int[]{hashMap.get(target - nums[i]), i};
            }
            // 存储key和value
            hashMap.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 7, 9};
        _03_two_sum_I twoSumI = new _03_two_sum_I();
        int[] res = twoSumI.twoSum2(arr, 9);
        System.out.println(Arrays.toString(res));
    }


}
