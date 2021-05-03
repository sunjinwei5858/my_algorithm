package com.sunjinwei.test;

/**
 * 8 字符串转为整数
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个32位有符号整数（类似 C/C++ 中的 atoi 函数）。
 * 函数 myAtoi(string s) 的算法如下：
 * 1读入字符串并丢弃无用的前导空格
 * 2检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 * 3读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 * 如果整数数超过 32 位有符号整数范围 [−2^31,  2^31 − 1] ，需要截断这个整数，使其保持在这个范围内。
 * 具体来说，小于 −23^1 的整数应该被固定为 −2^31 ，大于 2^31 − 1 的整数应该被固定为 2^31 − 1 。
 * 返回整数作为最终结果。
 * 注意：
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
 * <p>
 * 输入：s = "   -42"  输出：-42
 * 输入：s = "4193 with words" 输出：4193
 * 输入：s = "words and 987"   输出：0
 * 输入：s = "-91283472332"    输出：-2147483648
 */
public class _16_myAtoi {

    /**
     * 自己的方法：恶心的判断 可能还有bug
     * 1自己使用了long类型是不对的 因为字符串可能也超过long的输入
     *
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        // 1从不是空格的元素开始处理+判断是不是正负号 空格字符
        // 2如果超过 [−2^31,  2^31 − 1]
        // 3如果是字母开头 那么直接返回0 字母有26个 如何判断是不是字母 使用Character工具类的isLetter方法判断

        // 鲁棒性
        if (s == null || s.length() == 0) {
            return 0;
        }
        // 判断1：字母开头 直接返回
        if (Character.isLetter(s.charAt(0))) {
            return 0;
        }
        String result = "";
        // 是否出现过了负号或者数字
        // 统计负号出现的次数
        int count = 0;
        // 判断是否开始整数的处理
        Boolean flag = false;
        for (char c : s.toCharArray()) {
            // 判断2：忽略空格
            if (!flag) {
                // 1忽略空格
                if (Character.isWhitespace(c)) {
                    continue;
                }
                // 2如果还是字母 返回0
                if (count == 1 && Character.isLetter(c)) {
                    return 0;
                }

            } else {
                // 如果已经是数字了 那么不能为负号了
                if (!Character.isDigit(c)) {
                    break;
                }
            }
            // 开始出现跟数字相关的 比如负号或者纯数字
            // 判断3：处理正负号
            if (c == '-' || c == '+') {
                count += count;
                if (c == '-') {
                    result = result + c;
                }
            } else {
                // 判断4： 走到这里 说明必须要是数字了 如果不是 直接返回0
                if (!Character.isDigit(c)) {
                    return 0;
                }
                flag = true;
                // 处理数字
                result = result + c;
            }
        }

        if (!flag) {
            return 0;
        }
        // 如何判断这个数字是不是超过int的范围
        // 直接转化Long接收  然后判断范围
        Long num = new Long(result);
        if (num > (Integer.MAX_VALUE)) {
            return Integer.MAX_VALUE;
        }
        if (num < (Integer.MIN_VALUE)) {
            return Integer.MIN_VALUE;
        }
        return new Integer(result);
    }


    /**
     * 题解的方法
     * 1去掉前导空格
     * 2再处理正负号
     * 3识别数字 注意越界情况
     *
     * @param s
     */
    public int myAtoi2(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        // 遍历的指针
        int index = 0;
        // 1去掉前导空格
        while (index < n && Character.isWhitespace(chars[index])) {
            index++;
        }
        // 2判断去除空格之后 index是否到了数组尾巴
        if (index == n) {
            return 0;
        }
        // 3再去处理正负号
        boolean flag = false;
        if (chars[index] == '+') {
            // 遇到正号
            index++;
        } else if (chars[index] == '-') {
            index++;
            flag = true;
        } else if (!Character.isDigit(chars[index])) {
            // 其他符号!!! 这里直接将这个判断放置这里 秒!!!
            return 0;
        }
        int ans = 0;
        // 本来应该是 ans * 10 + digit > Integer.MAX_VALUE
        // 但是 *10 和 + digit 都有可能越界，所有都移动到右边去就可以了。
        while (index < n && Character.isDigit(chars[index])) {
            int digit = chars[index] - '0';
            if (ans > (Integer.MAX_VALUE - digit) / 10) {
                return flag ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            // 乘10
            ans = ans * 10 + digit;
            index++;
        }
        // 最后处理正负号 秒!!!
        return flag ? -ans : ans;
    }


    public static void main(String[] args) {
        _16_myAtoi myAtoi = new _16_myAtoi();
        System.out.println(myAtoi.myAtoi(" fsdfa 232"));
    }


}
