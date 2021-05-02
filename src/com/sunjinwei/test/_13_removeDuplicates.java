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

        // 1定义结果数组的区间为[0,left] 左闭右闭
        int left = 0;
        // 2因为数组本身是排好序的 所以令right指针为1即可
        for (int right = 1; right < nums.length; right++) {
            // 如果left和right指向的值不想等 说明是需要将right放入结果数组[o,left]中的
            if (nums[left] != nums[right]) {
                // left++ 是因为区间[o,slow]是左闭右闭的
                left++;
                nums[left] = nums[right];
            }
        }
        // 3因为left是指向结果数组中最后一个元素的末尾
        // 因为索引是从0开始 所以返回长度需要left+1
        return left + 1;
    }


    /**
     * 方法2：使用while循环的快慢指针
     *
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        // 1鲁棒性
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 2初始化快慢指针的索引!!!!
        // 慢指针就是结果数组的区间[0,slow]
        // 慢指针已经确定 与前面没有重复的元素的指针
        int slow = 0;
        // 快指针就是用来检测元素的指针
        int fast = 1;
        // 3处理
        while (fast < nums.length) {
            if (nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }
}
