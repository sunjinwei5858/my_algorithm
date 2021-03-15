package com.sunjinwei._01_stack;

import java.util.LinkedList;

/**
 * 单调队列实现：保证是单调递增
 */
public class SingleDeque {

    private LinkedList<Integer> queue;

    public SingleDeque() {
        this.queue = new LinkedList<>();
    }

    /**
     * 添加元素，满足的条件：如果新元素比尾部元素大 那么弹出
     *
     * @param val
     */
    public void push(int val) {
        while (!queue.isEmpty() && queue.peekLast() < val) {
            queue.removeLast();
        }
        queue.addLast(val);
    }

    /**
     * 获取单调队列的最大值：头部元素
     *
     * @return
     */
    public int max() {
        return queue.peekFirst();
    }

    /**
     * 窗口移除元素，如果队头元素等于窗口移除元素 那么推出
     *
     * @param val
     */
    public void pop(int val) {
        if (!queue.isEmpty() && queue.peekFirst() == val) {
            queue.removeFirst();
        }
    }

}
