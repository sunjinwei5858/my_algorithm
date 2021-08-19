package com.sunjinwei._01_stack02;

import java.util.Stack;

/**
 * 两个栈实现队列【虾皮面试题】
 */
public class Stack_To_Queue {

    public Stack<Integer> pushStack;
    public Stack<Integer> popStack;

    /**
     * Initialize your data structure here.
     */
    public Stack_To_Queue() {
        this.popStack = new Stack<>();
        this.pushStack = new Stack<>();
    }


    /**
     * 如果pop栈为空 那么一次性将data栈放入pop栈
     */
    private void process() {
        if (popStack.isEmpty()) {
            while (!pushStack.isEmpty()) {
                popStack.push(pushStack.pop());
            }
        }
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        pushStack.push(x);
        // !!!! 将process过程放到push之后操作
        process();
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        process();
        if (popStack.isEmpty()) {
            return -1;
        }
        return popStack.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        process();
        if (popStack.isEmpty()) {
            return -1;
        }
        return popStack.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        process();
        return popStack.isEmpty() && pushStack.isEmpty();
    }
}
