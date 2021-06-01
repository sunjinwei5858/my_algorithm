package com.sunjinwei.test._01_stack;

import java.util.Stack;

/**
 * 设计数据结构，使用两个栈实现队列
 */
public class _02_Two_Stack_Realize_Queue {

    private Stack<Integer> stackPush;

    private Stack<Integer> stackPop;

    public _02_Two_Stack_Realize_Queue() {
        this.stackPush = new Stack<>();
        this.stackPop = new Stack<>();
    }

    /**
     * 公共方法：如果stackPop不为空 那么一次性将stackPush所有元素都装到stackPop中
     */
    private void handleData() {
        if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
    }

    public void push(Integer val) {
        stackPush.push(val);
        handleData();
    }

    public Integer pop() {
        handleData();
        if (stackPop.isEmpty()) {
            return -1;
        }
        return stackPop.pop();
    }

    public Boolean isEmpty() {
        handleData();
        return stackPop.isEmpty() && stackPush.isEmpty();
    }

    public Integer peek() {
        handleData();
        if (stackPop.isEmpty()) {
            return -1;
        }
        return stackPop.peek();
    }


}
