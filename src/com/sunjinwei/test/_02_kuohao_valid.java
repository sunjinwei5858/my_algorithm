package com.sunjinwei.test;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 括号合法
 */
public class _02_kuohao_valid {

    /**
     * 将左括号入栈
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        // 左括号集合
        List<Character> list = Arrays.asList('(', '[', '{');
        // 处理规则：
        // 左括号必须在前面 所以走到else逻辑 那么栈必定不为空
        for (char c : s.toCharArray()) {
            // 遇到左括号进栈
            if (list.contains(c)) {
                stack.push(c);
            } else {
                // 进入到这里 如果栈为空 直接返回false
                // 判断栈顶元素是不是对应的左括号
                if (!stack.isEmpty() && stack.peek() == change(c)) {
                    stack.pop();
                } else {
                    // 说明一开始都是右括号 那么为false
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    private char change(char c) {
        if (c == ')') {
            return '(';
        } else if (c == '}') {
            return '{';
        } else {
            return '[';
        }
    }

    public static void main(String[] args) {
        _02_kuohao_valid kuohaoValid = new _02_kuohao_valid();
        boolean validValid = kuohaoValid.isValid("})");
        System.out.println(validValid);
    }
}
