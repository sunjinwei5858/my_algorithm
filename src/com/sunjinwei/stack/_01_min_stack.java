package com.sunjinwei.stack;

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

    public void push(int x) {
        // 第一个元素
        if (stackData.isEmpty()) {
            stackData.push(x);
            stackMin.push(x);
        } else {
            stackData.push(x);
            // 比较当前元素和最小值
            Integer min = stackMin.pop();
            if (min > x) {
                // 如果栈顶元素不是最小值了 调整push的顺序
                stackMin.push(x);
            } else if (min < x) {
                stackMin.push(min);
            } else {
                stackMin.push(min);
                stackMin.push(min);
            }
        }
    }

    public int pop() {
        Integer pop = stackData.pop();
        // 如果栈顶元素就是最小值 需要调整stackMin维护的栈顶最小值
        if (!stackMin.isEmpty()) {
            if (pop.equals(stackMin.peek())) {
                stackMin.pop();
                if (stackData.size() == 1 && stackMin.isEmpty()) {
                    stackMin.push(stackData.peek());
                }
            }

        }
        return pop;
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
