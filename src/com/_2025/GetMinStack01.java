package com._2025;

import java.util.Stack;

/**
 * 设计一个栈，满足栈的基本功能，还要可以获取栈的最小值
 * 思路：使用两个栈，一个栈保证栈的基本功能，另外一个栈维护栈的最小值
 * 如果有思路，实现出来了，千万要记住异常处理，有没有可能空指针，对于stack，要进行判空
 */
public class GetMinStack01 {

    private final Stack<Integer> data;
    private final Stack<Integer> minData;

    public GetMinStack01(Stack<Integer> data, Stack<Integer> minData) {
        this.data = data;
        this.minData = minData;
    }

    public void push(Integer num) {
        data.push(num);
        if (minData.empty()) {
            minData.push(num);
        } else {
            if (minData.peek() >= num) {
                minData.push(num);
            } else {
                minData.push(minData.peek());
            }
        }
    }

    public Integer pop() {
        // 考虑异常情况
        if (data.isEmpty()) {
            return -1;
        }
        Integer res = data.pop();
        minData.pop();
        return res;
    }

    public Integer getMin() {
        if (minData.isEmpty()) {
            return -1;
        }
        return minData.peek();
    }
}
