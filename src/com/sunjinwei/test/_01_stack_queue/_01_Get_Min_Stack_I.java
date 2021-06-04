package com.sunjinwei.test._01_stack_queue;

import java.util.Stack;

/**
 * 设计数据结构实现最小栈
 * 方法1：每个元素都对应一个min 很好理解和处理
 * push的时候需要花时间 pop的时候很简单
 */
public class _01_Get_Min_Stack_I {

    private Stack<Integer> dataStack;

    private Stack<Integer> minStack;

    public _01_Get_Min_Stack_I() {
        this.dataStack = new Stack<>();
        this.minStack = new Stack<>();
    }

    /**
     * 在push的同时 对应minStack也保存一个对应的最小值
     *
     * @param val
     */
    public void push(Integer val) {
        dataStack.push(val);
        if (minStack.isEmpty()) {
            minStack.push(val);
        } else {
            // 如果最小值小于当前的val值  那么继续在minStack中存入这个最小值
            if (minStack.peek() < val) {
                minStack.push(minStack.peek());
            } else {
                // 如果最小值大于等于当前的val值 那么更新最小值
                minStack.push(val);
            }
        }
    }

    /**
     * 鲁棒性判断
     *
     * @return
     */
    public Integer pop() {
        if (dataStack.isEmpty()) {
            return -1;
        }
        Integer res = dataStack.pop();
        minStack.pop();
        return res;
    }

    /**
     * 鲁棒性判断
     *
     * @return
     */
    public Integer getMin() {
        if (minStack.isEmpty()) {
            return -1;
        }
        return minStack.peek();
    }

    public Boolean isEmpty() {
        return dataStack.isEmpty();
    }


}
