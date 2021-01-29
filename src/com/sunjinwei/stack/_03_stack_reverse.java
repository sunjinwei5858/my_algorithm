package com.sunjinwei.stack;

import java.util.Stack;

/**
 * 如何仅用递归函数和栈操作逆序一个栈
 * 【题目】
 * 一个栈依次压入1、2、3、4、5，那么从栈顶到栈底分别为5、4、3、2、1。
 * 将这个栈转置后，从栈顶到栈底为 1、2、3、4、5，也就是实现栈中元素的逆序，但是只能用递归函数来实现，不能用其他数据结构。
 */
public class _03_stack_reverse {

    /**
     * 递归一：实现逆序
     * 只能使用递归来实现，不能使用while来实现
     *
     * @param stack
     */
    public void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        // 找到栈底元素并且移除
        int value = getLastElementAndRemove(stack);
        reverse(stack);
        // 将找到的栈底元素依次push 实现了反转
        stack.push(value);
    }

    /**
     * 递归二：将栈stack的栈底元素返回并移除。
     *
     * @param stack
     * @return
     */
    private int getLastElementAndRemove(Stack<Integer> stack) {
        Integer first = stack.pop();
        if (stack.isEmpty()) {
            return first;
        }
        int lastElement = getLastElementAndRemove(stack);
        stack.push(first);
        return lastElement;
    }

    public static void main(String[] args) {
        _03_stack_reverse stack_reverse = new _03_stack_reverse();
        Stack<Integer> stack = new Stack<>();
        stack.push(4);
        stack.push(2);
        stack.push(3);

        // 反转
       /* stack_reverse.reverse(stack);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());*/

        // 找到stack中底部的元素
        int bottomData = stack_reverse.getBottomData(stack);
        System.out.println("底部元素：" + bottomData);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());

    }

    /**
     * 如何找到栈底的元素 并且不移除栈底元素
     */
    private int getBottomData(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return -1;
        }
        Integer pop = stack.pop();
        if (stack.isEmpty()) {
            // 不移除栈底元素 就需要进行push
            stack.push(pop);
            return pop;
        }
        // 递归
        int bottomData = getBottomData(stack);
        // 将不是底部的元素继续推到stack中
        stack.push(pop);
        return bottomData;
    }


}



