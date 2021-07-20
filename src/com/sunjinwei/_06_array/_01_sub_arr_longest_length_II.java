package com.sunjinwei._06_array;


import java.util.HashMap;

/**
 * 变种：
 * 未排序数组中累加和为给定值的最长子数组长度【可正可负可0数组】
 * 关键字：未排序，可正可负可0，累加和，定值，子数组长度，最长
 * 联想：两数之和 套路 就是两个数相加
 */
public class _01_sub_arr_longest_length_II {

    /**
     * 最优解：时间复杂度O(N),空间复杂度为O(N)
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
        // 长度
        int len = 0;
        // 累加和
        int sum = 0;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        // 初始化 重要
        hashMap.put(0, -1);
        // 关键点：
        // s[i]代表[0..i]的数组和
        // s[j..i]代表arr[j..i]的数组和
        // 那么arr[j+1..i]的和就是s[i]-s[j]
        // s[i] = sum, s[j] = sum - k, 那么arr[j..i]就是s[i]-s[j] = k
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            // sum-k存在 说明存在等于k的子数组
            // sum-k不存在 说明还没有出现等于k的子数组
            // 这一步类似两数之和
            if (hashMap.containsKey(sum - k)) {
                // 获取arr[j..i]的长度： i - hashMap.get(sum - k)
                // 和当前的len进行比较
                // 取最大 更新len
                len = Math.max(i - hashMap.get(sum - k), len);
            }
            // 将sum存储到map中
            if (!hashMap.containsKey(sum)) {
                hashMap.put(sum, i);
            }
        }
        return len;
    }

}
