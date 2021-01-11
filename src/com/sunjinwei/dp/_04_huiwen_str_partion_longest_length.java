package com.sunjinwei.dp;

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
     * 思路一：
     * 1.先判断是不是回文串 如果是 那么最大长度就是本身
     * 2。如果不是回文串 那么最长长度就需要匹配 偶数 奇数
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
            int num = count[c];
            count[c] = num++;
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

    public static void main(String[] args) {
        String s = "abc";
        System.out.println(s.charAt(0));

        System.out.println(7 / 3); // 2

        System.out.println(7 % 3); // 1

        System.out.println(8 % 4); // 0

    }


}
