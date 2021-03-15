package com.sunjinwei._01_stack;

import java.util.LinkedList;

/**
 * 用队列实现栈，225
 * 方法三： 只用一个队列 在push的时候做文章
 * 需要满足的条件：
 * 队列的头部必须保证是刚push的元素，也就是push的元素要把他放在队列的头部位置，这里使用一个队列的话 那就是先将x进行push 然后把
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
     * Push element x onto _01_stack. 只用一个队列实现栈，保证元素先进后出
     */
    public void push(int x) {
        // 在push前 先获取队列的size
        int size = queue.size();
        // 将该元素push到队列
        queue.offer(x);
        // 将队列的元素推出，然后重新放到队列 这样保证队列的元素在 当前元素 x的后面 妙哉!!!
        for (int i = 0; i < size; i++) {
            queue.offer(queue.poll());
        }
    }

    /**
     * Removes the element on top of the _01_stack and returns that element.
     */
    public int pop() {
        return queue.poll();
    }

    /**
     * Get the top element.
     */
    public int top() {
        return queue.peek();
    }

    /**
     * Returns whether the _01_stack is empty.
     */
    public boolean empty() {
        return queue.isEmpty();
    }
}
