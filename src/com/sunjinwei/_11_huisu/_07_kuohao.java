package com.sunjinwei._11_huisu;

import java.util.ArrayList;
import java.util.List;

/**
 * 22 括号生成 回溯方法解决
 * 数字n代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且有效的括号组合。
 * 示例 1：
 * <p>
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：["()"]
 * ===》现在有 2n 个位置，每个位置可以放置字符 ( 或者 )，组成的所有括号组合中，有多少个是合法的？
 * 合法：左括号个数大于等于右括号
 * <p>
 * 自己的思考：其实也可以想像成一颗二叉树，因为只能选择左括号和右括号，所以左节点就是左括号 右节点就是右括号，一直往下延伸
 */
public class _07_kuohao {

    List<String> result = new ArrayList<>();

    public List<String> generateParenthesis(int n) {
        String path = "";
        helper(n, n, path);
        return result;
    }

    /**
     * 两种写法：1回溯写法 2深度优先遍历写法
     *
     * @param left
     * @param right
     * @param path
     */
    private void helper(int left, int right, String path) {
        // 剪枝1：左括号数量先使用 那么剩余的左括号数量是小于右括号的
        if (left > right) {
            return;
        }
        // 剪枝2：数量小于0肯定也不合法
        if (left < 0 || right < 0) {
            return;
        }
        // 终止条件
        if (left == 0 && right == 0) {
            result.add(path);
            return;
        }
        // 第一种写法： 回溯的风格
        // 尝试放左括号
       /* path += "(";
        helper(left - 1, right, path);
        path = path.substring(0, path.length() - 1);
        // 尝试放右括号
        path += ")";
        helper(left, right - 1, path);
        path = path.substring(0, path.length() - 1);*/

        // 第二种写法：深度优先遍历 dfs 类似树的遍历
        if (left > 0) {
            helper(left - 1, right, path + "(");
        }
        if (right > 0) {
            helper(left, right - 1, path + ")");
        }
    }

    public static void main(String[] args) {
        _07_kuohao kuohao = new _07_kuohao();
        List<String> strings = kuohao.generateParenthesis(3);
        System.out.println(strings.toString());


    }

}
