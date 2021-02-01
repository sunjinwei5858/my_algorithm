package com.sunjinwei.stack;

import java.util.LinkedList;

/**
 * 用队列实现栈，225
 * 方法一：两个队列 在push的时候 做文章，其余不需要管
 */
public class _02_two_queue_stack_I {

    private LinkedList<Integer> queue01;

    /**
     * queue02只是临时存储的容器
     */
    private LinkedList<Integer> queue02;

    /**
     * Initialize your data structure here.
     */
    public _02_two_queue_stack_I() {
        queue01 = new LinkedList<>();
        queue02 = new LinkedList<>();
    }

    /**
     * Push element x onto stack. 在push方法 保证先进后出 将新添加的元素放到队列的第一个
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.3 MB, 在所有 Java 提交中击败了41.53%的用户
     */
    public void push(int x) {
        // 将新元素放到队列的第一个 借助queue02保存
        queue02.offer(x);
        // 将queue01的元素转移到queue02
        while (!queue01.isEmpty()) {
            queue02.offer(queue01.poll());
        }
        // 将queue01和queue02置换
        LinkedList<Integer> temp = queue01;
        queue01 = queue02;
        queue02 = temp;

    }

    /**
     * Removes the element on top of the stack and returns that element.
     */
    public int pop() {
        return queue01.poll();
    }

    /**
     * Get the top element.
     */
    public int top() {
        return queue01.peek();

    }

    /**
     * Returns whether the stack is empty.
     */
    public boolean empty() {
        return queue01.isEmpty();
    }
}
