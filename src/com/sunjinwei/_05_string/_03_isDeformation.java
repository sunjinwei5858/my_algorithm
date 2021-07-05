package com.sunjinwei._05_string;

/**
 * 有效的字母异位词【力扣242】难度 简单
 * 进阶：如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？【判断两个字符串是否互为变形词【左神】】
 * <p>
 * 给定两个字符串str1和str2，
 * 如果str1和str2中出现的字符种类一样且每种字符出现的次数也一样，那么str1与str2互为变形词。请实现函数判断两个字符串是否互为变形词。
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。难度 简单
 */
public class _03_isDeformation {

    /**
     * 力扣242
     * 你可以假设字符串只包含小写字母。
     */
    public boolean isAnagram(String s, String t) {
        // 鲁棒性1 如果有一个为空 直接返回false
        // 鲁棒性2 如果长度不相等 直接返回false
        if (s == null || t == null) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }
        // 直接使用数组代替哈希表
        // 如果只包含小写字母 那么使用长度为26的数组即可
        int[] record = new int[26];
        // 将第一个字符串进行统计
        for (char c : s.toCharArray()) {
            int count = record[c - 'a'];
            count++;
            record[c - 'a'] = count;
        }
        // 处理第二个字符串
        for (char c : t.toCharArray()) {
            int count = record[c - 'a'];
            count--;
            if (count < 0) {
                return false;
            }
            record[c - 'a'] = count;
        }
        return true;
    }

    /**
     * 力扣242进阶/左神
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
            arr[arr2[i]] = count;
        }
        return true;
    }


    public static void main(String[] args) {

        /**
         * "anagram"
         * "nagaram"
         */
        _03_isDeformation isDeformation = new _03_isDeformation();
        boolean anagram = isDeformation.isDeformation("anaagram", "nagaram");
        System.out.println(anagram);
    }


}
