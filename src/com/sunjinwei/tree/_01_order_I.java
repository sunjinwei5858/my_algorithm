package com.sunjinwei.tree;

import com.sunjinwei.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 前序遍历 先根节点 左子树 右子树
 * 方法：递归和非递归
 */
public class _01_order_I {

    private List<Integer> result = new ArrayList<>();

    /**
     * 递归实现
     *
     * @param node
     * @return
     */
    public List<Integer> preOrder(TreeNode node) {

        if (node == null) {
            return result;
        }
        result.add(node.val);
        preOrder(node.left);
        preOrder(node.right);
        return result;
    }

    /**
     * 非递归：第一种写法 这种思想可以利用到后续遍历的两个栈解法上
     *
     * @param node
     * @return
     */
    public List<Integer> preOrder_02(TreeNode node) {

        if (node == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.empty()) {
            // 1根节点出栈
            TreeNode pop = stack.pop();
            result.add(pop.val);
            // 2右子树进栈
            if (pop.right != null) {
                stack.push(pop.right);
            }
            // 3左子树进栈
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
        return result;
    }

    /**
     * 非递归：第二种写法
     */
    public List<Integer> preOrder_03(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                // 前序遍历原则：第一次碰到就打印
                result.add(curr.val);
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                curr = curr.right;
            }
        }
        return result;
    }

    /**
     * 非递归：第三种写法
     *
     * @param root
     * @return
     */
    public List<Integer> preOrder_04(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                // 前序遍历原则：第一次碰到就打印
                result.add(curr.val);
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            curr = curr.right;
        }
        return result;
    }


}
