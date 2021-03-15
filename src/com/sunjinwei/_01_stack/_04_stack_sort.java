package com.sunjinwei._01_stack;

import java.util.Stack;

/**
 * 栈排序
 * 编写程序，对栈进行排序使最大元素位于栈顶或者最小元素位于栈顶。最多只能使用一个其他的临时栈存放数据，但不得将元素复制到别的数据结构（如数组）中。
 * 该栈支持如下操作：push、pop、peek 和 isEmpty。当栈为空时，peek 返回 -1。
 */
public class _04_stack_sort {

    private Stack<Integer> stack;

    public _04_stack_sort() {
        stack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);
        sortStackMax(stack);
    }

    public void pop() {
        sortStackMax(stack);
        stack.pop();
    }

    public int peek() {
        sortStackMax(stack);
        return stack.peek();
    }

    public boolean isEmpty() {
        sortStackMax(stack);
        return stack.isEmpty();
    }

    /**
     * 最大元素位于栈顶：
     * 要使最大元素位于栈顶 那么辅助栈的栈顶就是最小元素
     * 将要排序的栈记为stack，申请的辅助栈记为help。在stack上执行pop操作，弹出的元素记为cur。
     * 满足的条件：
     * 1。辅助栈栈顶元素保证最小，将cur与辅助栈栈顶元素比较
     * 2。如果cur更小 直接进栈
     * 3。如果cur更大，需要将辅助栈顶元素弹出 放入stack，cur进入辅助栈【可以理解为交换元素】
     * 4。最后stack为空，将help辅助栈元素push到stack
     * 完成排序
     */
    private void sortStackMax(Stack<Integer> stack) {
        // 辅助栈 最大的放栈顶
        Stack<Integer> stackHelp = new Stack<>();
        while (!stack.isEmpty()) {
            Integer curr = stack.pop();
            while (!stackHelp.isEmpty() && stackHelp.peek() < curr) {
                // 辅助栈元素更小
                // 交换
                stack.push(stackHelp.pop());
            }
            stackHelp.push(curr);
        }
        while (!stackHelp.isEmpty()) {
            stack.push(stackHelp.pop());
        }
    }

    /**
     * 最小元素位于栈顶：
     * 要使最小元素位于栈顶，辅助栈的栈顶元素就是最大元素
     * 需要满足的条件：
     * 1。辅助栈顶元素保证最大，将cur与辅助栈栈顶元素比较
     * 2。如果cur更大 直接进栈
     * 3。如果cur更小 需要进行交换
     *
     * @param stack
     */
    private void sortStackMin(Stack<Integer> stack) {
        // 辅助栈 保证辅助栈栈顶元素最大 从大到小
        Stack<Integer> stackHelp = new Stack<>();
        while (!stack.isEmpty()) {
            Integer curr = stack.pop();
            while (!stackHelp.isEmpty() && stackHelp.peek() > curr) {
                // 辅助栈元素更大
                // 交换 是为了将更小元素位于这个辅助栈元素的下面
                Integer pop = stackHelp.pop();
                stack.push(pop);
            }
            // 将弹出的元素加入到辅助栈中
            stackHelp.push(curr);
        }
        while (!stackHelp.isEmpty()) {
            stack.push(stackHelp.pop());
        }
    }


    public static void main(String[] args) {
        _04_stack_sort stack_sort = new _04_stack_sort();
        Stack<Integer> stack = new Stack<>();
        stack.push(5);
        stack.push(3);
        stack.push(4);

        //
        stack_sort.sortStackMax(stack);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }

}
