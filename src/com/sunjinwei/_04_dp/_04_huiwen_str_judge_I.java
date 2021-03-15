package com.sunjinwei._04_dp;

/**
 * 判断是不是回文串I 125 难度 简单
 * 题目描述：给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * 方法1：api法 直接将字符串反转 判断是不是相等
 * 方法2：左右指针
 */
public class _04_huiwen_str_judge_I {

    /**
     * 方法一：API法 利用StringBuffer的reverse方法和
     * 1先去除一些特殊字符 将字母和数字找出来
     * 2反转
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            // 1利用Character提供的isLetterOrDigit方法 判断是不是字符或者数字
            if (Character.isLetterOrDigit(s.charAt(i))) {
                // 2利用Character提供的toLowerCase方法
                stringBuffer.append(Character.toLowerCase(s.charAt(i)));
            }
        }
        // 将buffer反转
        String buf = stringBuffer.toString();
        String reverseBuf = new String(stringBuffer.reverse());
        // reverse和buffer比较
        return buf.equals(reverseBuf);
    }


    /**
     * 方法二： 双指针
     *
     * @param s
     */
    public boolean isPalindrome_02(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            // 使用while
            // 边界条件1：左指针不是数字或者字母
            // 并且left<right
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            // 使用while
            // 边界条件2：右指针不是数字或者字母
            // 并且left<right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            // 此时左右指针对应的是数字或者字母
            if (left < right && Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    public static void main(String[] args) {
        _04_huiwen_str_judge_I huiwen_judge = new _04_huiwen_str_judge_I();
        System.out.println(huiwen_judge.isPalindrome_02("A man, a plan, a canal: Panama"));
        System.out.println(huiwen_judge.isPalindrome_02("race a car"));


    }

}
