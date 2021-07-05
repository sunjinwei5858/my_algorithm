package com.sunjinwei._11_huisu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 字符串的全部排列
 * 输入一个字符串，打印出该字符串中字符的所有排列 你可以以任意顺序返回这个字符串数组
 */
public class _01_str_arrange_I {

    /**
     * 左神的做法：不申请额外的空间操作
     * 因为全排列 比如abc 第一个位置有3种选择 第二个位置有2种 第三个位置1种选择
     *
     * @param s
     * @return
     */
    public String[] permutation2(String s) {
        // 使用hashset进行存储
        // 因为字符串可能会出现相同的字符 此时是需要判重的!!!!
        List<String> res = new ArrayList<>();
        char[] arr = s.toCharArray();
        // 处理
        process(arr, res, 0);
        // 将set结果转为数组
        return res.toArray(new String[res.size()]);

    }

    private void process(char[] arr, List<String> res, int index) {
        // 如果index到达了终点
        if (index == arr.length) {
            res.add(String.valueOf(arr));
        }
        // 如果index还没到终点位置
        for (int i = index; i < arr.length; i++) {
            // 做选择
            swap(arr, index, i);
            process(arr, res, index + 1);
            // 撤销选择
            swap(arr, index, i);
        }
    }

    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        _01_str_arrange_I strArrange = new _01_str_arrange_I();
        String[] strings = strArrange.permutation2("abc");
        System.out.println(Arrays.toString(strings));
    }
}
