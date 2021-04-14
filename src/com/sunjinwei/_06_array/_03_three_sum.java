package com.sunjinwei._06_array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 分析：
 * 根据两数之和推出三数之和
 */
public class _03_three_sum {

    public List<List<Integer>> threeSum(int[] nums) {

        return threeSum(nums, 0);
    }

    private List<List<Integer>> threeSum(int[] nums, int target) {
        // 排序复杂度：nlogn
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        // for循环:O(n)
        for (int i = 0; i < nums.length; i++) {

            if (i > 1 && nums[i] == nums[i - 1]) {
                continue;
            }
            List<List<Integer>> lists = twoSum(nums, i + 1, target - nums[i]);
            for (List<Integer> integerList : lists) {
                integerList.add(nums[i]);
                res.add(integerList);
            }

        }
        return res;
    }

    /**
     * 找出两数之和的二元组，但是不能包含重复的二元组
     *
     * @param nums
     * @param target
     * @return
     */
    private List<List<Integer>> twoSum(int[] nums, int start, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int left = start;
        int right = nums.length - 1;
        int sum = 0;
        while (left < right) {
            sum = nums[left] + nums[right];
            // 记录索引最初的值
            int le = nums[left];
            // 记录索引最初的值
            int ri = nums[right];
            if (sum > target) {
                // 优化
                while (left < right && le == nums[left]) {
                    left++;
                }
            } else if (sum < target) {
                // 优化
                while (left < right && ri == nums[right]) {
                    right--;
                }
            } else {
                ArrayList<Integer> l = new ArrayList<>();
                l.add(nums[left]);
                l.add(nums[right]);
                res.add(l);
                // 优化
                // 处理left 如果left++还是和之前的值一样 那么继续++ 这样做 刚好进行了去重
                while (left < right && le == nums[left]) {
                    left++;
                }
                // 处理right 如果right--还是和之前的值一样 那么继续-- 这样做 刚好进行了去重
                while (left < right && ri == nums[right]) {
                    right--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        _03_three_sum threeSum = new _03_three_sum();
        int[] nums = {0, 0, 0, 0};
        List<List<Integer>> lists = threeSum.threeSum(nums);
        for (List<Integer> list : lists) {
            System.out.println(list.toString());
        }
    }

}
