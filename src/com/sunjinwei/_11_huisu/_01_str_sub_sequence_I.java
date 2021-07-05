package com.sunjinwei._11_huisu;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串的子序列
 */
public class _01_str_sub_sequence_I {

    /**
     * 暴力递归的方法
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
        // 将字符串转为数组
        char[] arr = s.toCharArray();
        // 记录路径
        String path = "";
        process(arr, res, 0, path);
        return res;
    }

    private void process(char[] arr, List<String> res, int index, String path) {
        if (index == arr.length) {
            res.add(path);
            return;
        }
        // 不做选择
        String no = path;
        process(arr, res, index + 1, no);
        // 做选择
        String yes = path + String.valueOf(arr[index]);
        process(arr, res, index + 1, yes);
    }

    public static void main(String[] args) {
        _01_str_sub_sequence_I subSequenceI = new _01_str_sub_sequence_I();
        List<String> subSequence = subSequenceI.strSubSequence("abc");
        System.out.println(subSequence.toString());
    }



}
