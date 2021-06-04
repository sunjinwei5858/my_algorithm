package com.sunjinwei.test._01_stack_queue;

import java.util.Arrays;
import java.util.Stack;

/**
 * 单调栈
 * 题目1：数组不含有重复元素 找出arr[i]左边和右边比arr[i]小的元素的位置
 * 题目2：数组中存在重复元素
 */
public class _07_Single_Stack {

    /**
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

    public static void main(String[] args) {
        _07_Single_Stack singleStack = new _07_Single_Stack();

        int[] arr = new int[]{3, 4, 1, 5, 6, 2, 7};

        int[][] nearMinByNoRepeat = singleStack.getNearMinByNoRepeat(arr);

        for (int[] ints : nearMinByNoRepeat) {
            System.out.println(Arrays.toString(ints));
        }

    }

}
