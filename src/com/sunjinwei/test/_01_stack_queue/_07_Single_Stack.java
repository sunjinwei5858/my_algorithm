package com.sunjinwei.test._01_stack_queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 单调栈
 * 题目1：数组不含有重复元素 找出arr[i]左边和右边比arr[i]小的元素的位置
 * 题目2：数组中存在重复元素
 */
public class _07_Single_Stack {

    /**
     * 题目一：数组中不含有重复元素
     * 1。维持单调栈的单调性
     *
     * @param arr
     * @return
     */
    public int[][] getNearMinByNoRepeat(int[] arr) {

        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            // 1维持单调栈的单调递减的特性 并且找出比i小的 左边的值和右边的值
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                // 此时弹出的元素就可以处理左边和右边
                Integer curr = stack.pop();
                res[curr][0] = stack.isEmpty() ? -1 : stack.peek();
                res[curr][1] = i;
            }
            // 2将元素放入栈中
            stack.push(i);
        }
        // 3处理还在栈中的元素 此时栈中栈顶到栈底都是从大到小排列 单调递减的顺序 那么右边都是为-1
        while (!stack.isEmpty()) {
            Integer curr = stack.pop();
            // 左边
            res[curr][0] = stack.isEmpty() ? -1 : stack.peek();
            // 右边
            res[curr][1] = -1;
        }
        return res;
    }


    /**
     * 题目二：数组中含有重复元素
     * <p>
     * 继续使用单调栈的思路，重复元素使用list进行存储
     *
     * @param arr
     */
    public int[][] getNearMinByHasRepeat(int[] arr) {
        // 创建二维数组
        int[][] res = new int[arr.length][2];
        // 创建list的栈
        Stack<List<Integer>> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            // 1 处理：维持单调栈的单调递增
            while (!stack.isEmpty() && arr[stack.peek().get(0)] >= arr[i]) {
                // 弹出索引大的元素
                List<Integer> list = stack.pop();
                // 处理弹出元素集合左边的索引 取list中索引最大的那个
                int leftIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                // 处理弹出元素集合右边的索引 全部都是i
                for (Integer integer : list) {
                    res[integer][0] = leftIndex;
                    res[integer][1] = i;
                }
            }
            // 2处理：将索引入栈
            // 简洁写法 只要当前栈顶集合对应数组的值是一样的 那么直接add 否则都需要新建list
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                List<Integer> arrayList = new ArrayList<>();
                arrayList.add(i);
                stack.push(arrayList);
            }
        }
        // 3如果栈不为空 处理剩余的元素
        while (!stack.isEmpty()) {
            List<Integer> pop = stack.pop();
            // 右边小的元素都没了
            int rightIndex = -1;
            // 左边小的元素取栈顶元素集合中的最后一个
            int leftIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);

            for (Integer integer : pop) {
                res[integer][0] = leftIndex;
                res[integer][1] = rightIndex;
            }
        }
        return res;
    }


    public static void main(String[] args) {
        _07_Single_Stack singleStack = new _07_Single_Stack();

        int[] arr = new int[]{3, 4, 1, 5, 6, 2, 7};

        int[][] nearMinByNoRepeat = singleStack.getNearMinByNoRepeat(arr);

        for (int[] ints : nearMinByNoRepeat) {
            System.out.println(Arrays.toString(ints));
        }

    }

}
