package com.sunjinwei.test;

/**
 * 删除排序数组的重复项 力扣26
 * 给你一个有序数组nums ，请你原地删除重复出现的元素，使每个元素只出现一次 ，返回删除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在原地修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 * 输出：5, nums = [0,1,2,3,4]
 * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 关键字：不使用额外空间，删除之后还需要保证排序，往前挪动，只出现一次（也就是去重）
 */
public class _13_removeDuplicates {

    /**
     * 使用双指针,left和right都从左边开始移动
     * 1如果left和right的值相同 left不需要动 此时right继续移动
     * 2如果left和right的值不相同，此时left进行移动 然后交换left和right的值 【为了保证有序】
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {

        int left = 0;
        for (int right = 1; right < nums.length; right++) {
            if (nums[left] != nums[right]) {
                left++;
                nums[left] = nums[right];
            }
        }
        return left + 1;
    }
}
