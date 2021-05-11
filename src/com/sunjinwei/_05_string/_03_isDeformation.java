package com.sunjinwei._05_string;

/**
 * 判断两个字符串是否互为变形词
 * <p>
 * 给定两个字符串str1和str2，
 * 如果str1和str2中出现的字符种类一样且每种字符出现的次数也一样，那么str1与str2互为变形词。请实现函数判断两个字符串是否互为变形词。
 */
public class _03_isDeformation {

    /**
     * 方法1：使用数组[0,255]来代替哈希表
     *
     * @param str1
     * @param str2
     * @return
     */
    public boolean isDeformation(String str1, String str2) {

        // 鲁棒性：如果有一个为null 直接返回false
        if (str1 == null || str2 == null) {
            return false;
        }
        // 鲁棒性：首先判断长度是不是相同 不相同 直接返回false
        if (str1.length() != str2.length()) {
            return false;
        }

        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();

        // 如果长度相同，假设出现字符的编码值在0～255之间，那么先申请一个长度为256的整型数组map
        int[] arr = new int[256];

        for (int i = 0; i < arr1.length; i++) {
            int count = arr[arr1[i]];
            count++;
            arr[arr1[i]] = count;
        }

        for (int i = 0; i < arr2.length; i++) {
            int count = arr[arr2[i]];
            count--;
            if (count == 0) {
                return false;
            }
        }
        return true;
    }
}
