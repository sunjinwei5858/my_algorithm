package com.sunjinwei._06_array;


/**
 * 未排序正数数组中累加和为给定值的最长子数组长度【正数数组】【左神是等于target 找最长】
 * 关键字：未排序，正数，累加和，定值，子数组长度，最长
 * <p>
 * 题目：
 * 给定一个数组arr，该数组无序，但每个值均为正数，再给定一个正数k。
 * 求arr的所有子数组中所有元素相加和为k的最长子数组长度。
 * 例如，arr=[1，2，1，1，1]，k=3。累加和为3的最长子数组为[1，1，1]，所以结果返回3。
 * <p>
 * 变种：
 * 长度最小的子数组（子数组的和大于等于给定值的最小长度子数组），力扣209 字节面试题【力扣是大于等于target 找最短】
 */
public class _01_sub_arr_longest_length_I {

    /**
     * 【等于target 找最长子数组】
     * 最优解：时间复杂度O(N),空间复杂度为O(1)
     *
     * @param arr 数组
     * @param k   定值k
     * @return 返回最长子数组长度
     */
    public int getLongestLength(int[] arr, int k) {
        // 鲁棒性1
        if (arr == null || arr.length == 0 || k < 0) {
            return 0;
        }
        // 1使用左右指针标记子数组的左右两头 代表子数组arr[left, right]
        int left = 0;
        int right = 0;
        // 2声明sum为子数组arr[left, right]的和
        int sum = arr[0];
        // 3声明变量len 一直记录累加和为k的所有子数组中最大子数组的长度，开始时，len=0
        int len = 0;
        // 4比较sum的值和k的大小
        while (right < arr.length) {
            if (sum > k) {
                // 4.1 sum>k:说明目前累加和大于k 需要left++
                sum = sum - arr[left];
                left++;
            } else if (sum < k) {
                // 4.2 sum<k:说明目前累加和大于k 需要right++ 注意索引越界
                // 累加和大 right需要右移
                right++;
                // 判断索引不要越界
                if (right == arr.length) {
                    break;
                }
                // right++后还需要处理
                sum = sum + arr[right];
            } else {
                // 4.4 sum=k:说明目前累加和等于k，此时比较len和当前arr的长度 取最大，
                // 并且还需要继续找后面还有最大的了，所以需要调整sum的值，和left的索引
                int arrNow = right - left + 1;
                len = Math.max(arrNow, len);
                sum = sum - arr[left];
                left++;
            }
        }
        return len;
    }

    /**
     * 变种：子数组的和大于等于target，找出最短的子数组长度【大于等于target 找最短子数组】
     * <p>
     * 执行用时：2 ms, 在所有 Java 提交中击败了74.82%的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了74.74%的用户
     *
     * @param nums
     * @param target
     */
    public int getLongestLength2(int[] nums, int target) {
        // 鲁棒性
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 声明子数组的区间arr[left, right] 初始化left和right都为0
        int left = 0;
        int right = 0;
        // 声明子数组的和sum变量 用于记录 初始化sum=nums[0]
        int sum = nums[0];
        // 声明最短的子数组长度 初始化为0
        int len = 0;
        // 声明子数组的长度
        int arrSize = 0;
        while (right < nums.length) {
            if (sum >= target) {
                // sum大于等于target 满足题意 计算子数组的长度
                arrSize = right - left + 1;
                if (len == 0) {
                    len = arrSize;
                } else {
                    // 取最小
                    len = Math.min(len, arrSize);
                }
                // left向右移动 那么就需要更新sum 减去当前left对应的值
                sum = sum - nums[left];
                left++;
            } else {
                // sum小于target right继续向右移动即可
                right++;
                // !!!! 鲁棒性
                if (right == nums.length) {
                    break;
                }
                // right向右移动 那么需要更新sum 加上当前right++对应的值
                sum = sum + nums[right];
            }
        }
        return len;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 1, 1, 1};
        _01_sub_arr_longest_length_I length_i = new _01_sub_arr_longest_length_I();
        int lonestLength = length_i.getLongestLength(arr, 3);
        System.out.println(lonestLength);

    }
}
