package com.sunjinwei.stack;

import java.util.LinkedList;

/**
 * 用队列实现栈，225
 * 方法三： 只用一个队列 在push的时候做文章
 * <p>
 * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
 * 内存消耗：36.3 MB, 在所有 Java 提交中击败了33.37%的用户
 */
public class _02_two_queue_stack_III {

    private LinkedList<Integer> queue;

    /**
     * Initialize your data structure here.
     */
    public _02_two_queue_stack_III() {
        queue = new LinkedList<>();
    }

    /**
     * Push element x onto stack. 只用一个队列实现栈，保证元素先进后出
     */
    public void push(int x) {
        // 在push前 先获取队列的size
        int size = queue.size();
        // 将该元素push到队列
        queue.offer(x);
        // 将队列在push元素之前的都推出然后重新push 妙哉!!!
        for (int i = 0; i < size; i++) {
            queue.offer(queue.poll());
        }
    }

    /**
     * Removes the element on top of the stack and returns that element.
     * 在pop方法 将队列的最后一个元素推出
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了22.30%的用户
     */
    public int pop() {
        return queue.poll();
    }

    /**
     * Get the top element.
     * 还需要对获取顶部元素进行改造 因为LinkedList 是双端队列 支持从头部/尾部 进行增删改查
     * 自己感觉这并不是一个好办法，理论上应该是单向队列考虑
     */
    public int top() {
        return queue.peek();
    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return queue.isEmpty();
    }
}
