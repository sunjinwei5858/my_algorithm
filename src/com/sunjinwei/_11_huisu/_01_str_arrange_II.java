package com.sunjinwei._11_huisu;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 字符串的全部排列 剑指offer 38
 * 输入一个字符串，打印出该字符串中字符的所有排列，你可以以任意顺序返回这个字符串数组【但里面不能有重复元素】
 * <p>
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 * <p>
 * "aac"
 * ["aca","aac","caa"]
 */
public class _01_str_arrange_II {

    /**
     * 方法1：使用回溯
     * 回溯有模板，直接套用模板
     * 将排列想象成一颗树
     *
     * @param s
     * @return
     */
    public String[] permutation(String s) {
        // 使用hashset进行存储
        // 因为字符串可能会出现相同的字符 此时是需要判重的!!!!
        HashSet<String> res = new HashSet<>();
        // 使用布尔数组 表示该字符是否被使用过
        boolean[] visited = new boolean[s.length()];
        // 处理
        backTrack(s, "", visited, res);
        // 将set结果转为数组
        return res.toArray(new String[res.size()]);
    }

    private void backTrack(String s, String path, boolean[] visited, HashSet<String> res) {
        if (path.length() == s.length()) {
            System.out.println("====" + path);
            res.add(path);
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            // 剪枝1
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            backTrack(s, path + s.charAt(i), visited, res);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        _01_str_arrange_II strArrange = new _01_str_arrange_II();
        String[] strings = strArrange.permutation("abc");
        System.out.println(Arrays.toString(strings));
    }
}
