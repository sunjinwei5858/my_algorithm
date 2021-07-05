package com.sunjinwei._11_huisu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 字符串的子序列的变种，要求不出现重复字面值的子序列
 */
public class _01_str_sub_sequence_II {

    /**
     * 使用hashset
     *
     * @param s
     * @return
     */
    public List<String> strSubSequence(String s) {
        // 鲁棒性
        List<String> res = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return res;
        }
        HashSet<String> set = new HashSet<>();
        // 将字符串转为数组
        char[] arr = s.toCharArray();
        // 记录路径
        String path = "";
        process(arr, set, 0, path);
        for (String s1 : set) {
            res.add(s1);
        }
        return res;
    }

    private void process(char[] arr, HashSet<String> set, int index, String path) {
        if (index == arr.length) {
            set.add(path);
            return;
        }
        // 不做选择
        String no = path;
        process(arr, set, index + 1, no);
        // 做选择
        String yes = path + String.valueOf(arr[index]);
        process(arr, set, index + 1, yes);
    }

    public static void main(String[] args) {
        _01_str_sub_sequence_II sequenceIi = new _01_str_sub_sequence_II();
        List<String> subSequence = sequenceIi.strSubSequence("aab");
        System.out.println(subSequence.toString());
    }
}
