package com.sunjinwei.test._01_stack;

import java.util.Stack;

/**
 * 使用递归逆序一个栈
 */
public class _03_Reverse_Stack {

    /**
     * 思路：要逆序 也就是栈底的元素要放在栈顶，写一个获取到栈底元素并且弹出的方法，然后再继续递归调用reverse方法
     *
     * @param stack
     */
    public void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int last = getMiddleAndRemoveElement(stack);
        reverse(stack);
        stack.push(last);
    }

    /**
     * 递归处理：获取栈底元素并且弹出
     *
     * @param stack
     * @return
     */
    private int getMiddleAndRemoveElement(Stack<Integer> stack) {
        Integer i = stack.pop();
        if (stack.isEmpty()) {
            return i;
        }
        int ans = getMiddleAndRemoveElement(stack);
        stack.push(i);
        return ans;
    }

    public static void main(String[] args) {
        _03_Reverse_Stack reverseStack = new _03_Reverse_Stack();
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        reverseStack.reverse(stack);

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

    }


}
