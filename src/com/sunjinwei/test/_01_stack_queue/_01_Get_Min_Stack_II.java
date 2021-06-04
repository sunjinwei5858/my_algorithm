package com.sunjinwei.test._01_stack_queue;

import java.util.Stack;

/**
 * 设计数据结构实现最小栈
 * 方法2：当不需要更新最小值 minStack也不需要重复存入值
 */
public class _01_Get_Min_Stack_II {

    private Stack<Integer> dataStack;

    private Stack<Integer> minStack;

    public _01_Get_Min_Stack_II() {
        this.dataStack = new Stack<>();
        this.minStack = new Stack<>();
    }

    /**
     * 在push的同时 如果val大于min，那么不需要操作minStack
     *
     * @param val
     */
    public void push(Integer val) {
        dataStack.push(val);
        if (minStack.isEmpty()) {
            minStack.push(val);
        } else {
            // 如果min大于等于val 此时更新min值 其余情况不处理
            // 大于
            // 等于
            if (minStack.peek() >= val) {
                minStack.push(val);
            }
        }
    }

    /**
     * 鲁棒性判断+如果pop的值
     *
     * @return
     */
    public Integer pop() {
        if (dataStack.isEmpty()) {
            return -1;
        }
        Integer res = dataStack.pop();
        if (res.equals(minStack.peek())) {
            minStack.pop();
        }
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
