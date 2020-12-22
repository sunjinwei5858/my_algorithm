package com.sunjinwei.bfs_dfs_huisu;


import java.util.*;

/**
 * 括号相关的两道题：
 * 1。括号生成：回溯 这个也是常考的面试题 比较有意思 力扣22
 * 2。括号合法性判断：栈 力扣20
 * <p>
 * 括号的性质【解题思路】
 * 1。一个「合法」括号组合的左括号数量一定等于右括号数量，这个很好理解。
 * 2。对于一个「合法」的括号字符串组合 p，必然对于任何 0 <= i < len(p) 都有：子串 p[0..i] 中左括号的数量都大于或等于右括号的数量。
 * 【从左往右算的话，肯定是左括号多嘛，到最后左右括号数量相等，说明这个括号组合是合法的。】
 */
public class _01_kuohao {

    /**
     * 1。括号生成
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且有效的括号组合。
     */
    public List<String> generateParenthesis(int n) {
        // 记录所有合法的括号组合
        List<String> result = new ArrayList<>();
        if (n == 0) {
            return result;
        }
        // 回溯过程的路径
        String track = "";
        // 可用的左括号和右括号的数量初始化为n
        //generateParenthesis(result, n, n, track);
        generateParenthesis02(result, 0, 0, n, track);
        return result;

    }

    /**
     * 第一种方法: 做减法
     *
     * @param list
     * @param left  可用的左括号
     * @param right 可用的右括号
     * @param s
     */
    private void generateParenthesis01(List<String> list, int left, int right, String s) {
        // 结束条件1: 括号刚好用完 做减法 所以左右如果都为0
        if (left == 0 && right == 0) {
            list.add(s);
            return;
        }
        // 结束条件2 左括号大于右括号 因为是先左括号减一 如果左大于右 那肯定要stop
        if (left > right) {
            return;
        }
        // 结束条件3 剪枝 不需要递归了 左右都不可能为0
        if (left < 0 || right < 0) {
            return;
        }

        //尝试放左括号
        s += "(";
        generateParenthesis01(list, left - 1, right, s);
        // 撤销选择
        s = s.substring(0, s.length() - 1);

        //尝试放右括号
        s += ")";
        generateParenthesis01(list, left, right - 1, s);
        // 撤销选择
        s = s.substring(0, s.length() - 1);
    }

    /**
     * 第二种方法：做加法
     *
     * @param list
     * @param left
     * @param right
     * @param n
     * @param s
     */
    private void generateParenthesis02(List<String> list, int left, int right, int n, String s) {
        // 结束条件1: 括号刚好用完
        if (left == n && right == n) {
            list.add(s);
            return;
        }
        // 结束条件2 因为是左边先加1 所以不能出现左边小于右边
        if (left < right) {
            return;
        }
        // 结束条件3 因为是做加法 左边和右边都不能大于n
        if (left > n || right > n) {
            return;
        }

        //尝试放左括号
        s += "(";
        generateParenthesis02(list, left + 1, right, n, s);
        // 撤销选择
        s = s.substring(0, s.length() - 1);

        //尝试放右括号
        s += ")";
        generateParenthesis02(list, left, right + 1, n, s);
        // 撤销选择
        s = s.substring(0, s.length() - 1);
    }


    public static void main(String[] args) {
        _01_kuohao a01kuohao = new _01_kuohao();
        List<String> strings = a01kuohao.generateParenthesis(3);
        System.out.println(strings.toString());


    }

    /**
     * 2。括号合法性
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     */
    public boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        if ("" == s) {
            return true;
        }
        // 使用一个map 存储左右括号 右括号为key !!!!! 左括号为value
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '('); // 右括号为key 是一个非常好的思路
        map.put(']', '[');
        map.put('}', '{');

        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : chars) {
            // 左括号都要进栈
            if (!map.containsKey(c)) {
                stack.push(c);
            } else {
                // 右括号要做的事情就是和栈顶的元素做对比
                if (!stack.isEmpty()) {
                    Character character = map.get(c);
                    Character pop = stack.pop();
                    if (!character.equals(pop)) {
                        return false;
                    }
                } else {
                    // 如果先进来的是右括号
                    return false;
                }
            }
        }
        // 遍历结束 如果栈不为空 那么不合法
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }


}
