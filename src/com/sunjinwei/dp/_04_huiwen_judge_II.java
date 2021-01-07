package com.sunjinwei.dp;

/**
 * 判断是不是回文串的变种 力扣680 难度：简单
 * 描述：
 * 给定一个非空字符串s，最多删除一个字符。判断是否能成为回文字符串。
 * 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
 */
public class _04_huiwen_judge_II {

    /**
     * 自己的错误写法：不能使用切割，因为如果刚开始左右是相等的，此时left和right进行了++，再去切割的话 会少两个字符串，而不是少一个字符串
     * 思路：这里的处理是一个for循环 将i和j都进行一步处理
     * 不要想的那么复杂，如果左右指针指向的字符不相等，那么就判断left+1到right的字符串，或者left到right+1的字符串，是不是回文串
     *
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {

        // 使用while的方式构造回文串
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                boolean flagLeft = true;
                boolean flagRight = true;
                // 切割1
                String leftStr = s.substring(left + 1, s.length());
                for (int i = 0, j = leftStr.length() - 1; i < j; i++, j--) {
                    if (s.charAt(i) != s.charAt(j)) {
                        flagLeft = false;
                        break;
                    }
                }
                // 切割2
                String rightStr = s.substring(left, s.length() - 1);
                for (int i = 0, j = rightStr.length() - 1; i < j; i++, j--) {
                    if (s.charAt(i) != s.charAt(j)) {
                        flagRight = false;
                        break;
                    }
                }
                return flagLeft || flagRight;
            }
        }
        return true;

    }

    /**
     * 正确写法一：单独抽取一个判断的方法
     *
     * @param s
     */
    public boolean validPalindrome_03(String s) {

        // 使用while的方式构造回文串
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return valid(s, left + 1, right) || valid(s, left, right - 1);
            }
        }
        return true;
    }

    public boolean valid(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 正确写法二 进行优化 首尾判断在一个for循环中判断
     *
     * @param s
     * @return
     */
    public boolean validPalindrome_02(String s) {

        // 使用while的方式构造回文串
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                boolean flagLeft = true;
                boolean flagRight = true;
                for (int i = left, j = right - 1; i < j; i++, j--) {
                    if (s.charAt(i) != s.charAt(j)) {
                        flagLeft = false;
                        break;
                    }
                }
                for (int i = left + 1, j = right; i < j; i++, j--) {
                    if (s.charAt(i) != s.charAt(j)) {
                        flagRight = false;
                        break;
                    }
                }
                return flagLeft || flagRight;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        _04_huiwen_judge_II huiwen_judge_ii = new _04_huiwen_judge_II();
        System.out.println(huiwen_judge_ii.validPalindrome_03("abca"));

        String s = "abcd";
        System.out.println(s.substring(0, s.length() - 1));
        System.out.println(s.substring(1, s.length()));
    }


}
