package com.sunjinwei._04_dp;

/**
 * 判断是不是回文数 力扣9 难度：简单
 * 描述：
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 方法1：将int转成字符串，
 */
public class _04_huiwen_digit_judge {

    /**
     * 方法1：将int转成string，转化为判断是不是回文串
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 方法2：直接反转int 但是只反转数字的一半 处理一半 可以使用双指针
     * 边界条件1：如果是负数 那么不符合
     * 边界条件2：如果反转之后大于Max_Value 那么也不行，所以考虑反转int的一半
     * 取余
     *
     * @param x
     * @return
     */
    public boolean isPalindrome_02(int x) {
        // 边界条件1 负数肯定不是回文数
        if (x < 0) {
            return false;
        }
        // 个位如果是0的 肯定也不是回文数
        if (x > 10 && x % 10 == 0) {
            return false;
        }
        // 声明反转的数字
        int revertNum = 0;
        while (x > revertNum) {
            // 右边
            revertNum = x % 10 + revertNum * 10;
            // 左边
            x = x / 10;
        }
        return revertNum == x;
    }

    public static void main(String[] args) {
        int s = 1312;
        int right = s % 10;
        int left = s / 10;

        _04_huiwen_digit_judge huiwen_judge_digit = new _04_huiwen_digit_judge();
        System.out.println(huiwen_judge_digit.isPalindrome_02(s));
    }
}
