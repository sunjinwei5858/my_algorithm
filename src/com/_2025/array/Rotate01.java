package com._2025.array;

/**
 * 数组循环移动k位
 * 给定一个长度为 n 的字符串，将其向右循环移动 k 位，求循环移动之后的字符串。
 * 输入：s="abcdefg", k=3
 * 输出："efgabcd"
 * <p>
 * 输入：s="abcdefg", k=13
 * 输出："bcdefga"
 * <p>
 * 难度：简单
 */
public class Rotate01 {

    /**
     * 核心思路：向右循环移动 k 位，相当于把字符串的后 k 位移到前面，前面的 n-k 位顺序后移。
     * 一句话总结：向右循环移动 k 位 = 后 k 位 + 前 n-k 位，注意 k 取模。
     * 一定要取模，防止k大于n
     *
     * @param s
     * @param k
     * @return
     */
    public String rotate(String s, int k) {
        if (k == 0) {
            return s;
        }
        int n = s.length();
        if (n == 0) {
            return s;
        }
        // 取模, 防止k大于n
        k = n / k;
        return s.substring(n - k) + s.substring(0, k);
    }

}
