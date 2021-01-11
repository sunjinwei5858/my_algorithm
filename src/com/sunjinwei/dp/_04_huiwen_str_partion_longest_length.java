package com.sunjinwei.dp;

import java.util.HashSet;

/**
 * 分割/构造 最长回文子串的长度 力扣409 难度 中等
 * <p>
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母【构造】成的最长的回文串。
 * 在【构造】过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 * 注意：是分割 构造
 * 输入:"abccccdd" 输出:7
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是7。
 */
public class _04_huiwen_str_partion_longest_length {


    /**
     * 思路一：使用数组 存储字符出现的次数
     * 思路： 解体的关键在于计算有多少对相同的字符。
     * <p>
     * 执行用时：2 ms, 在所有 Java 提交中击败了70.95%的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了97.03%的用户
     *
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        // 使用数组 并没有使用map进行统计 这里很巧妙 自己常规想到的是用map
        // 因为字符也是属于int类型
        int[] count = new int[128];
        int length = s.length();
        // 统计各个字符出现的次数
        for (int i = 0; i < length; ++i) {
            char c = s.charAt(i);
            // count[c]++;
            count[c] = count[c] + 1;
        }
        // 如果有任何一个字符ch的出现次数v为奇数（即v%2 == 1），那么可以将这个字符作为回文中心，注意只能最多有一个字符作为回文中心。
        // 在代码中，我们用ans存储回文串的长度，由于在遍历字符时，ans每次会增加v/2 * 2，因此ans一直为偶数。
        // 但在发现了第一个出现次数为奇数的字符后，我们将ans增加1，这样ans变为奇数，在后面发现其它出现奇数次的字符时，我们就不改变ans的值了。
        int ans = 0;
        for (int v : count) {
            // 对于每个字符ch，假设它出现了v次，我们可以使用该字符v/2 * 2次
            // 在回文串的左侧和右侧分别放置v/2个字符ch，其中/为整数除法
            ans += v / 2 * 2;
            // 如果ans是偶数 该字符出现的次数v又是奇数的话 那么进行++
            if (v % 2 == 1 && ans % 2 == 0) {
                ans++;
            }
        }
        return ans;
    }

    /**
     * 思路二：自己的方法 直接使用set进行处理:
     * 如果存在相同的元素 直接+2，然后移除这个元素
     * <p>
     * 执行用时：7 ms, 在所有 Java 提交中击败了31.71%的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了8.95%的用户
     *
     * @param s
     */
    public int longestPalindrome_02(String s) {
        int result = 0;
        HashSet<Character> hashSet = new HashSet<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            // 如果重复 那么长度需要+2，同时移除这个元素
            if (hashSet.contains(s.charAt(i))) {
                result = result + 2;
                hashSet.remove(s.charAt(i));
            } else {
                hashSet.add(s.charAt(i));
            }
        }
        // 最后如果set不为空 那么进行+1 即可
        if (!hashSet.isEmpty()) {
            result++;
        }
        return result;
    }


    public static void main(String[] args) {
        String s = "abc";
        System.out.println(s.charAt(0));

        System.out.println(7 / 3); // 2

        System.out.println(7 % 3); // 1

        System.out.println(8 % 4); // 0

        System.out.println("===================");


        String s2 = "abccccdd";

        _04_huiwen_str_partion_longest_length huiwen_str_partion_longest_length = new _04_huiwen_str_partion_longest_length();
        System.out.println(huiwen_str_partion_longest_length.longestPalindrome_02(s2));

        char i = 98;
        int[] arr = new int[8];
        arr[i] = 89;

        char[] array = new char[90];
        array[i] = 30;


    }


}
