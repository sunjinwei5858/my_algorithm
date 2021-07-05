package com.sunjinwei._01_stack02;

import java.util.Stack;

/**
 * 栈排序: 使用辅助栈完成栈的排序
 */
public class _05_Sort_Stack {

    /**
     * 栈顶到栈底从大到小排序
     *
     * @param stack
     */
    public void sort(Stack<Integer> stack) {

        // 保证辅助栈栈顶到栈底是从小到大
        Stack<Integer> help = new Stack<>();

        while (!stack.isEmpty()) {
            Integer val = stack.pop();
            // 当辅助栈的栈顶元素大于当前值 那么将栈顶元素推出 还需要将推出的元素继续放回原来的栈中
            // 保证辅助栈的元素 栈顶到栈底从小到大
            while (!help.isEmpty() && help.peek() > val) {
                Integer pop = help.pop();
                stack.push(pop);
            }
            help.push(val);
        }
        while (!help.isEmpty()) {
            stack.push(help.pop());
        }
    }

    public static void main(String[] args) {
        _05_Sort_Stack sortStack = new _05_Sort_Stack();
        Stack<Integer> stack = new Stack<>();
        stack.push(4);
        stack.push(7);
        stack.push(5);
        stack.push(1);

        sortStack.sort(stack);

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }


}
