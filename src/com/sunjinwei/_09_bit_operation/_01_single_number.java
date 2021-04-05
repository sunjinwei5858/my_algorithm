package com.sunjinwei._09_bit_operation;

/**
 * 只出现一次的数字 力扣136
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 要求：O(1)内存
 * 思路：
 * 使用位运算，异或
 * 性质1：自己异或自己，为0
 * 性质2：一个数和0进行异或，是它本身
 * 性质3：异或满足结合律和交换律
 * 分析：其余元素都是偶数次，那么异或得到的结果是0，根据性质2，0和这个出现奇数次的元素进行异或，那么得到的是该奇数次数元素本身
 */
public class _01_single_number {

    /**
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            res = res ^ nums[i];
        }
        return res;
    }


}
