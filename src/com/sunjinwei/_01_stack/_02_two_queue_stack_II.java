package com.sunjinwei._01_stack;

import java.util.LinkedList;

/**
 * 用队列实现栈，225
 * 方法二：两个队列 在pop的时候和top的时候 做文章 pop和top必须同时处理 这点比较坑 不是一个好思路
 */
public class _02_two_queue_stack_II {

    private LinkedList<Integer> queue01;

    /**
     * queue02只是临时存储的容器
     */
    private LinkedList<Integer> queue02;

    /**
     * Initialize your data structure here.
     */
    public _02_two_queue_stack_II() {
        queue01 = new LinkedList<>();
        queue02 = new LinkedList<>();
    }

    /**
     * Push element x onto _01_stack.
     */
    public void push(int x) {
        queue01.offer(x);
    }

    /**
     * Removes the element on top of the _01_stack and returns that element.
     * 在pop方法 将队列的最后一个元素推出
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了22.30%的用户
     */
    public int pop() {
        // 将queue01队列的最后一个元素保留
        while (queue01.size() > 1) {
            // 将queue01除了最后一个元素 全部保存到queue02
            queue02.offer(queue01.poll());
        }
        // 此时queue01还有一个元素，然后弹出
        Integer result = queue01.poll();
        // 此时queue01是空的
        LinkedList<Integer> temp = queue01;
        // 将queue02的元素放置到queue01 然后将queue02置空
        queue01 = queue02;
        queue02 = temp;
        return result;
    }

    /**
     * Get the top element.
     * 还需要对获取顶部元素进行改造 因为LinkedList 是双端队列 支持从头部/尾部 进行增删改查
     * 自己感觉这并不是一个好办法，理论上应该是单向队列考虑
     */
    public int top() {
        return queue01.peekLast();
    }

    /**
     * Returns whether the _01_stack is empty.
     */
    public boolean empty() {
        return queue01.isEmpty();
    }

    public static void main(String[] args) {
        _02_two_queue_stack_II two_queue_stack_ii = new _02_two_queue_stack_II();
        two_queue_stack_ii.push(1);
        two_queue_stack_ii.push(2);

        System.out.println(two_queue_stack_ii.top());
        System.out.println(two_queue_stack_ii.pop());
    }
}
