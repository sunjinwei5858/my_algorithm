package com.sunjinwei._06_array;

/**
 * 数组基本功：反转数组
 * 力扣344 反转字符串
 * <p>
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * <p>
 * 输入：s = ["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 * <p>
 * 难度：简单
 */
public class Reverse {

    /**
     * 解法：双指针
     *
     * @param s
     */
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        handlerReverse(s, left, right);
    }

    /**
     * 抽取一个方法
     *
     * @param s
     * @param left
     * @param right
     */
    private void handlerReverse(char[] s, int left, int right) {
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }


}
