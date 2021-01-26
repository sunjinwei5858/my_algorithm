package com.sunjinwei.stack;

import java.util.Stack;

/**
 * 剑指offer09：
 * 两个栈实现一个队列
 * <p>
 * 用两个栈实现一个队列。队列的声明如下，
 * 请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。
 * (若队列中没有元素，deleteHead 操作返回 -1 )
 * <p>
 * 需要满足的条件：
 * 1 必须一次性将栈中的数据push到辅助栈
 * 2 如果辅助栈不为空 不能push
 * 以上两个条件 缺一不可 这道题目看似简单 其实没有那么简单
 */
public class _02_2stack_deque {

    private Stack<Integer> stackPush;

    private Stack<Integer> stackPop;

    public _02_2stack_deque() {
        stackPush = new Stack<>();
        stackPop = new Stack<>();
    }

    /**
     * 队列尾部插入整数
     *
     * @param value
     */
    public void appendTail(int value) {
        stackPush.push(value);
        handler();
    }

    /**
     * 队列头部删除整数
     *
     * @return
     */
    public int deleteHead() {
        handler();
        if (stackPop.isEmpty()) {
            return -1;
        }
        return stackPop.pop();
    }

    /**
     * 抽取方法: 1。一次性压入 2。如果辅助pop栈不为空 不能进行压入
     *
     * @param
     */
    private void handler() {
        if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
    }

    public static void main(String[] args) {
        _02_2stack_deque stack_deque = new _02_2stack_deque();
        stack_deque.appendTail(1);
        stack_deque.appendTail(2);

        System.out.println(stack_deque.deleteHead()); // 1

        stack_deque.appendTail(3);

        System.out.println(stack_deque.deleteHead()); // 2
        System.out.println(stack_deque.deleteHead()); // 3
        System.out.println(stack_deque.deleteHead()); // -1
    }


}
