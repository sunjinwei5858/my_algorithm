package com.sunjinwei.test;

/**
 * 删除排序数组的重复项II  力扣80
 * 给你一个有序数组nums，请你原地删除重复出现的元素，使每个元素最多出现两次, 返回删除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在原地修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 输入：nums = [1,1,1,2,2,3]
 * 输出：5, nums = [1,1,2,2,3]
 * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 关键字：不使用额外空间，删除之后还需要保证排序，往前挪动，【最多出现两次】
 */
public class _13_removeDuplicates_II {

    /**
     * 这道题是基于上一题的变种，这里是可以两个数重复
     * 那么快慢指针就从第三个数开始进行比较，因为如果数组只有两个元素 那么直接进行返回即可
     * 使用for循环来判断 那么就不需要提前判断size的情况
     * 分析：
     * 如果left-2慢指针
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {

        // 相当于从第三个元素开始处理
        int left = 2;
        for (int right = 2; right < nums.length; right++) {
            // 需要检查结果数组[0,left)中上上个元素是否和当前处理的元素相等 也就是left-2
            if (nums[left - 2] != nums[right]) {
                nums[left] = nums[right];
                left++;
            }
        }
        // 因为left++的顺序是放在了后面 所以这里数组的长度直接返回left即可 不需要进行left+1
        return left;
    }

    /**
     * 通用写法：继续抽象，也就是将原数组的值重新赋值给新的数组 这样来思考，如果是上一题，只允许重复的数出现一次 那么就是index-1
     *
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        int index = 0;
        for (int num : nums) {
            // 特殊情况: index < 2 此时直接赋值即可
            // 因为数组是有序的 所以直接进行赋值即可  nums[index-2]<num
            // 进行抽象
            if (index < 2 || nums[index - 2] < num) {
                nums[index] = num;
                index++;
            }
        }
        // 因为index++的顺序是放在了后面执行 所以数组的长度直接为index即可
        return index;
    }


    public static void main(String[] args) {

        // [0,0,1,1,1,1,2,3,3]
        // [0,0,1,1,2,1,3]
        // [0,0,1,1,2,3,3]
        _13_removeDuplicates_II removeDuplicates_ii = new _13_removeDuplicates_II();

        int[] arr1 = new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3};
        int[] arr2 = new int[]{1, 1, 1, 2, 2, 3};

        int i = removeDuplicates_ii.removeDuplicates(arr1);
        System.out.println(i);
    }
}
