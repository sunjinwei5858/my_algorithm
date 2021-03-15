package com.sunjinwei._01_stack;

import java.util.Stack;

/**
 * 剑指 Offer 30. 包含min函数的栈 难度：简单
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 */
public class _01_min_stack {

    private Stack<Integer> stackData;

    private Stack<Integer> stackMin;

    /**
     * initialize your data structure here.
     */
    public _01_min_stack() {
        stackData = new Stack<>();
        stackMin = new Stack<>();
    }

    /**
     * 其实有两种方法，第一种是：辅助栈stackMin只存储部分min元素，第二种是完全填充，每一个data都对应一个min
     *
     * @param x
     */
    public void push(int x) {
        stackData.push(x);
        // 保证辅助栈的栈顶是最小值
        // 1辅助栈为空 进行push
        // 2辅助栈栈顶大于x 进行push
        if (stackMin.isEmpty() || this.getMin() >= x) {
            stackMin.push(x);
        }

    }

    public int pop() {
        Integer value = stackData.pop();
        // 判断push的是不是最小值
        if (value == this.getMin()) {
            stackMin.pop();
        }
        return value;
    }

    public int top() {
        if (stackData.isEmpty()) {
            return 0;
        }
        return stackData.peek();
    }

    public int getMin() {
        if (stackMin.isEmpty()) {
            return 0;
        }
        return stackMin.peek();
    }

    /**
     * ["MinStack","push","push","push","push","getMin","pop","getMin","pop","getMin","pop","getMin"]
     * [[],[2],[0],[3],[0],[],[],[],[],[],[],[]]
     * 预期结果：
     * [null,null,null,null,null,0,null,0,null,0,null,2]
     *
     * @param args
     */
    public static void main(String[] args) {
        _01_min_stack minStack = new _01_min_stack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        //minStack.push(0);
        System.out.println(minStack.getMin()); //0
        System.out.println(minStack.pop()); // null
        System.out.println(minStack.getMin()); // 0
        System.out.println("======");
        System.out.println(minStack.pop()); // null
        System.out.println(minStack.getMin()); // 0
        System.out.println("=============");
        System.out.println(minStack.pop()); // null
        System.out.println(minStack.getMin()); // 2


    }
}
