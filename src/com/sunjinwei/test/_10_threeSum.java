package com.sunjinwei.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15三数之和
 * <p>
 * 给你一个包含n个整数的数组nums，判断nums中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 关键字：结果不能重复
 * 方法：使用双指针 先进行排序 因为这里是返回值 所以可以进行排序
 * <p>
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 */
public class _10_threeSum {


    /**
     * 官方题解
     *
     * 时间复杂度O(n2)
     * 空间复杂度O(1)
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return ans;
        }
        // 排序
        Arrays.sort(nums);
        // 双指针
        for (int i = 0; i < nums.length; i++) {
            // 如果排序之后 第一个元素大于0 那么直接返回
            if (nums[i] > 0) {
                return ans;
            }
            // 去重
            // 对于重复元素：跳过，避免出现重复解
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 真正进行双指针处理
            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int temp = nums[i] + nums[l] + nums[r];
                if (temp == 0) {
                    ans.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    // 进行优化处理
                    // 判断左界和右界是否和下一位置重复，去除重复解
                    while (l < r && nums[l] == nums[l + 1]) {
                        l++;
                    }
                    while (l < r && nums[r] == nums[r - 1]) {
                        r--;
                    }
                    // 这里需要进行++操作 不要遗忘
                    l++;
                    r--;
                } else if (temp > 0) {
                    r--;
                } else {
                    l++;
                }
            }
        }
        return ans;
    }

}
