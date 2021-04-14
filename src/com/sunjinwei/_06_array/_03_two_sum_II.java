package com.sunjinwei._06_array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 两数之和变种：返回元素的值 那么还可以使用双指针的做法
 */
public class _03_two_sum_II {

    /**
     * 双指针法, 返回元素的值
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {

        // 1先进行排序
        Arrays.sort(nums);
        // 2双指针处理
        int left = 0;
        int right = nums.length - 1;
        int sum = 0;
        while (left < right) {
            sum = nums[left] + nums[right];
            // 注意去重
            // nums = [1,1,1,2,2,3,3], target=4，[1,3]不能重复
            // 所以每个if判断都需要进行去重
            if (sum > target) {
                right--;
            } else if (sum < target) {
                left++;
            } else {
                return new int[]{nums[left], nums[right]};
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * 找出两数之和的二元组，但是不能包含重复的二元组
     *
     * @param nums
     * @param target
     * @return
     */
    private List<List<Integer>> twoSum2(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        // 排序
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length - 1;
        int sum = 0;
        while (left < right) {
            sum = nums[left] + nums[right];
            // 记录索引最初的值
            int l1 = nums[left];
            // 记录索引最初的值
            int r1 = nums[right];
            if (sum > target) {
                // 优化
                while (left < right && l1 == nums[left]) {
                    left++;
                }
            } else if (sum < target) {
                // 优化
                while (left < right && r1 == nums[right]) {
                    right--;
                }
            } else {
                ArrayList<Integer> integers = new ArrayList<>();
                integers.add(nums[left]);
                integers.add(nums[right]);
                res.add(integers);
                // 优化
                // 处理left 如果left++还是和之前的值一样 那么继续++ 这样做 刚好进行了去重
                while (left < right && l1 == nums[left]) {
                    left++;
                }
                // 处理right 如果right--还是和之前的值一样 那么继续-- 这样做 刚好进行了去重
                while (left < right && r1 == nums[right]) {
                    right--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] ints = new int[]{3, 2, 4, 3};
        _03_two_sum_II twoSum = new _03_two_sum_II();
        List<List<Integer>> sum2 = twoSum.twoSum2(ints, 6);

        for (List<Integer> integers : sum2) {
            System.out.println(integers.toString());
        }

    }

}
