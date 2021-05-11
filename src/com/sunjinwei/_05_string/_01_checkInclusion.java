package com.sunjinwei._05_string;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 【滑动窗口题目】
 * 字符串的排列 力扣567 [这道题是两个字符串]
 * 给定两个字符串s1和s2，写一个函数来判断s2是否包含s1的排列。
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 * <p>
 * 示例 1：
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * 示例 2：
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 */
public class _01_checkInclusion {

    /**
     * 使用滑动窗口，窗口大小为s1的长度，在字符串s2上面进行滑动
     * key为字符，value为字符出现的次数
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        // 1将字符串s1的字符和出现次数 存储到map中
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }
        // 2窗口的大小[left,right]
        int left = 0;
        int right = s1.length() - 1;
        // 3统计第一个窗口在s2中字符的情况 但是这里先设置窗口大小为[left,right-1]
        for (int i = left; i <= right - 1; i++) {
            char c = s2.charAt(i);
            map2.put(c, map2.getOrDefault(c, 0) + 1);
        }
        // 4窗口在在字符串s2中进行滑动
        while (right < s2.length()) {
            // 把right位置的元素放到map2
            char c = s2.charAt(right);
            map2.put(c, map2.getOrDefault(c, 0) + 1);
            // 如果滑动窗口内各个元素出现的次数跟 s1 的元素出现次数完全一致，返回 True
            if (check(map1, map2)) {
                return true;
            }
            // 否则窗口继续向右移动 把当前left位置元素出现次数进行--
            char d = s2.charAt(left);
            // 如果left位置的元素出现次数就是1次 那么--之后就变成0 此时直接从map2中移除
            // 如果当前left位置的元素出现次数为0，需要从字典中删除，否则这个出现次数为0的元素会影响两map之间的比较
            map2.put(d, map2.getOrDefault(d, 0) - 1);
            if (map2.getOrDefault(d, 0) == 0) {
                map2.remove(d);
            }
            // 窗口向右移动
            left++;
            right++;
        }
        return false;
    }

    private boolean check(HashMap<Character, Integer> ori, HashMap<Character, Integer> cnt) {
        Iterator iter = ori.entrySet().iterator();
        System.out.println("ori：" + ori.toString() + "===" + "cnt:" + cnt.toString());
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Character key = (Character) entry.getKey();
            Integer val = (Integer) entry.getValue();
            if (!cnt.getOrDefault(key, 0).equals(val)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        _01_checkInclusion checkInclusion = new _01_checkInclusion();
        boolean b = checkInclusion.checkInclusion("ab", "eidbaooo");
        System.out.println(b);


        HashMap<Character, Integer> hashMap = new HashMap<>();
        hashMap.put('a', 2);
        hashMap.put('c', 3);

    }
}
