package com.sunjinwei.stack;

import java.util.Stack;

/**
 * 栈排序
 * 编写程序，对栈进行排序使最小元素位于栈顶。最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构（如数组）中。
 * 该栈支持如下操作：push、pop、peek 和 isEmpty。当栈为空时，peek 返回 -1。
 */
public class _04_stack_sort {

    private Stack<Integer> stack;


    public _04_stack_sort() {
        stack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        sortStack(stack);
    }

    public void pop() {
        sortStack(stack);
        stack.pop();
    }

    public int peek() {
        sortStack(stack);
        return stack.peek();
    }

    public boolean isEmpty() {
        sortStack(stack);
        return stack.isEmpty();
    }

    /**
     * 抽取对栈进行排序的方法: 从大到小
     */
    private void sortStack(Stack<Integer> stack) {
        // 辅助栈 最大的放栈顶 从大到小
        Stack<Integer> stackHelp = new Stack<>();
        while (!stack.isEmpty()) {
            Integer curr = stack.pop();
            while (!stackHelp.isEmpty() && stackHelp.peek() < curr) {
                stack.push(stackHelp.pop());
            }
            stackHelp.push(curr);
        }
        stack.push(stackHelp.pop());
    }

}
