package com.sunjinwei._06_array;

/**
 * 力扣26 删除有序数组中的重复项
 * 难度：简单
 * 给你一个非严格递增排列的数组 nums ，请你原地删除重复出现的元素，使每个元素只出现一次 ，
 * 返回删除后数组的新长度。元素的相对顺序应该保持一致 。然后返回nums中唯一元素的个数。
 * 考虑nums的唯一元素的数量为k ，你需要做以下事情确保你的题解可以被通过：
 * 更改数组nums，使nums的前k个元素包含唯一元素，并按照它们最初在nums中出现的顺序排列。nums的其余元素与nums的大小不重要。
 * 返回k 。
 * 输入：nums = [1,1,2]
 * 输出：2, nums = [1,2,_]
 */
public class RemoveDuplicates {

    /**
     * 思路：有序-》立马想到双指针->再想到使用快慢指针
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        // 慢指针，慢指针就是不含重复的数组大小
        int slow = 0;
        // 快指针
        int fast = 1;
        while (fast < nums.length) {
            // 如果相等，说明是重复元素，快指针继续前进。
            // 如果不相等，说明遇到新元素，慢指针前进一步，并把新元素赋值到慢指针位置
            if (nums[slow] != nums[fast]) {
                // 如果快慢指针索引的值不相等 说明慢指针可以继续++
                slow++;
                // 然后将快指针的值赋值给慢指针，这样是为了保证[o,slow]维护的元素一直是不含重复的
                nums[slow] = nums[fast];
            }
            fast++;
        }
        // 因为slow是从0开始的
        return slow + 1;
    }
}
