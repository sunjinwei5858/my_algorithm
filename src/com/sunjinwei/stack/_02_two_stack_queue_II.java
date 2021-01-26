package com.sunjinwei.stack;

import java.util.Stack;

/**
 * 力扣232 用栈实现队列
 * <p>
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列的支持的所有操作（push、pop、peek、empty）：
 * 实现 MyQueue 类：
 * void push(int x) 将元素 x 推到队列的末尾
 * int pop() 从队列的开头移除并返回元素
 * int peek() 返回队列开头的元素
 * boolean empty() 如果队列为空，返回 true ；否则，返回 false
 */
public class _02_two_stack_queue_II {

    private Stack<Integer> stackPush;
    private Stack<Integer> stackPop;

    /**
     * Initialize your data structure here.
     */
    public _02_two_stack_queue_II() {
        stackPush = new Stack<>();
        stackPop = new Stack<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        stackPush.push(x);
        oneStepPush();
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        oneStepPush();
        if (stackPop.isEmpty()) {
            return -1;
        }
        return stackPop.pop();

    }

    /**
     * Get the front element.
     */
    public int peek() {
        oneStepPush();
        if (stackPop.isEmpty()) {
            return -1;
        }
        return stackPop.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        oneStepPush();
        return stackPop.isEmpty();
    }

    /**
     * 抽取：
     * 如果stackPop为空 将stackPush的数据一次性压入
     */
    private void oneStepPush() {
        oneStepPush();
        if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
    }

}
