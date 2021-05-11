package com.sunjinwei._05_string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 【滑动窗口题目】
 * 给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。 力扣3
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * <p>
 * 滑动窗口题目:
 * 3. 无重复字符的最长子串
 * 30. 串联所有单词的子串
 * 76. 最小覆盖子串
 * 159. 至多包含两个不同字符的最长子串
 * 209. 长度最小的子数组
 * 239. 滑动窗口最大值
 * 567. 字符串的排列
 * 632. 最小区间
 * 727. 最小窗口子序列
 */
public class _01_length_longest_substring {

    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = -1;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i + 1);
        }
        return ans;
    }

    /**
     * 什么是滑动窗口？
     * 其实就是一个队列,比如例题中的 abcabcbb，进入这个队列（窗口）为 abc 满足题目要求，
     * 当再进入 a，队列变成了 abca，这时候不满足要求。所以，我们要移动这个队列！
     * 如何移动？
     * 我们只要把队列的左边的元素移出就行了，直到满足题目要求！
     * 一直维持这样的队列，找出队列出现最长的长度时候，求出解！
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        if (s.length() == 0) {
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            /**
             1、首先，判断当前字符是否包含在map中，如果不包含，将该字符添加到map[字符，字符在数组下标],
             此时没有出现重复的字符，左指针不需要变化。此时不重复子串的长度为：i-left+1，与原来的maxLen比较，取最大值；

             2、如果当前字符 ch 包含在 map中，此时有2类情况：
             1）当前字符包含在当前有效的子段中，如：abca，当我们遍历到第二个a，当前有效最长子段是 abc，我们又遍历到a，
             那么此时更新 left 为 map.get(a)+1=1，当前有效子段更新为 bca；
             2）当前字符不包含在当前最长有效子段中，如：abba，我们先添加a,b进map，此时left=0，我们再添加b，发现map中包含b，
             而且b包含在最长有效子段中，就是1）的情况，我们更新 left=map.get(b)+1=2，此时子段更新为 b，而且map中仍然包含a，map.get(a)=0；
             随后，我们遍历到a，发现a包含在map中，且map.get(a)=0，如果我们像1）一样处理，就会发现 left=map.get(a)+1=1，实际上，left此时
             应该不变，left始终为2，子段变成 ba才对。

             为了处理以上2类情况，我们每次更新left，left=Math.max(left , map.get(ch)+1).
             另外，更新left后，不管原来的 s.charAt(i) 是否在最长子段中，我们都要将 s.charAt(i) 的位置更新为当前的i，
             因此此时新的 s.charAt(i) 已经进入到 当前最长的子段中！
             */
            if (map.containsKey(s.charAt(i))) {
                // 更新left 取最大
                // 情况一：abca 此时left取map.get(s.charAt(i)) + 1，更新为bca
                // 情况二：abb，添加b的时候 发现重复，此时
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            // 这里进行取最大 是为了处理情况二的发生
            max = Math.max(max, i - left + 1);
        }
        return max;

    }

    public static void main(String[] args) {
        _01_length_longest_substring longestSubstring = new _01_length_longest_substring();
        int length = longestSubstring.lengthOfLongestSubstring2("abba");
        System.out.println(length);
    }


}
