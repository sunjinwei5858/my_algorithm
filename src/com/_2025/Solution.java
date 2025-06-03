package com._2025;

import java.util.*;

/**
 * 招行笔试算法题
 */
public class Solution {

    /**
     * 将两个字符串数组，按照长度从小到大进行合并
     *
     * @param first
     * @param second
     * @return
     */
    public String[] mergeArray(String[] first, String[] second) {
        int a = first.length;
        List<String> list = new ArrayList<String>();
        int b = second.length;
        // 1 添加到集合
        list.addAll(Arrays.asList(first));
        list.addAll(Arrays.asList(second));
        // 2 对集合进行排序
        list.sort(Comparator.comparingInt(String::length));
        // 3 放到数组
        String[] res = new String[a + b];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    /**
     * 将金额转化为中文
     *
     * @param num
     * @return
     */
    public String toChinese(float num) {
        // 1初始化
        HashMap<Character, String> initMap = new HashMap<>();
        initMap.put('9', "玖");
        initMap.put('8', "捌");
        initMap.put('7', "柒");
        initMap.put('6', "陆");
        initMap.put('5', "伍");
        initMap.put('4', "肆");
        initMap.put('3', "叁");
        initMap.put('2', "贰");
        initMap.put('1', "壹");
        initMap.put('0', "零");
        initMap.put('.', "");
        // 2转成字符串
        String s = String.valueOf(num);
        StringBuilder res = new StringBuilder();
        // 2.1 判断是否有小数点
        boolean contains = s.contains(".");
        for (int i = 0; i < s.length(); i++) {
            String s1 = initMap.get(s.charAt(i));
            res.append(s1);
            // 2.11没有小数点的情况
            if (!contains) {
                // 情况1：5位 那就是万，4位仟，3位佰，2位拾
            }
        }
        // 3对小数点后几位做判断处理
        if (contains) {
            String[] split = s.split("\\.");
            // 对小数点前几位做处理
            int lenBefore = split[1].length();


            // 对小数点后几位做处理
            int length = split[1].length();
            if (length == 1) {
                res.append("角");
            } else if (length == 2) {
                res.append("分");
            } else if (length == 3) {
                res.append("厘");
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = solution.toChinese(8520.2f);
        System.out.println(s);
    }
}
