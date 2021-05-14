package com.sunjinwei.test;

import java.util.Arrays;

/**
 * @program: com.sunjinwei.test
 * @author: sun jinwei
 * @create: 2021-05-13 08:08
 * @description: 最长递增子序列
 * 1返回长度
 * 2返回最长递增子序列
 **/
public class _22_longest_sub_sequence_increasing {

    /**
     * 1.返回长度
     * 方法1：动态规划
     * <p>
     * 状态定义: dp[i]是以arr[i]结尾的数组最长的子序列长度
     * 状态初始化：单个元素就是1 所以应该初始化为1
     * 状态方程: dp[i]和[0,i-1]中的最长递增子序列
     *
     * @param arr
     * @return
     */
    public int longestIncreasingSequence(int[] arr) {

        // 生成dp数组
        int[] dp = generateDp(arr);
        // 处理长度
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    private int[] generateDp(int[] arr) {
        // 状态定义：dp[i]是以arr[i]结尾的最长递增子序列的长度
        int[] dp = new int[arr.length];
        // 状态方程
        for (int i = 0; i < arr.length; i++) {
            // 状态初始化
            dp[i] = 1;
            // 内层循环：判断[0,i-1]之间
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    // 在[0,i-1]之间 哪个递增子序列长度最长 就选那个长度 然后+1
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp;
    }


    /**
     * 2返回最长递增子序列
     * 思路：
     * 1根据dp数组找出最长递增子序列的长度len和索引index，该索引的值就是子序列的最大值，并且是最右边的位置
     * 2接着从右到左找出子序列每个位置的值
     * 3需要满足的条件，
     */
    public int[] longestIncreasingSequence2(int[] arr) {
        // 生成dp数组
        int[] dp = generateDp(arr);
        // 找出最长子序列的长度和索引
        int len = 0;
        int index = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > len) {
                len = dp[i];
                index = i;
            }
        }
        // 根据index和res反推出数组 从右到左
        int[] newArr = new int[len];
        // bug!!! 此时数组的最大值就是arr中index的值
        len--;
        newArr[len] = arr[index];
        // 此时循环数组从index位置开始 在[0,index]范围里面找到递增的子序列 从右到左开始遍历
        for (int i = index; i >= 0; i--) {
            if (arr[i] < arr[index] && dp[i] == dp[index] - 1) {
                len--;
                newArr[len] = arr[i];
                index = i;
            }
        }
        return newArr;
    }

    public static void main(String[] args) {
        _22_longest_sub_sequence_increasing sequenceIncreasing = new _22_longest_sub_sequence_increasing();
        int[] arr = new int[]{2, 5, 3, 9, 7, 8};
        int maxLen = sequenceIncreasing.longestIncreasingSequence(arr);
        System.out.println(maxLen);

        int[] newArr = sequenceIncreasing.longestIncreasingSequence2(arr);

        System.out.println(Arrays.toString(newArr));


    }


}
