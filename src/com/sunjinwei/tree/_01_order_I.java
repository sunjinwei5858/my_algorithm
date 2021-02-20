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
        if (node.left != null) {
            preOrder(node.left);
        }
        if (node.right != null) {
            preOrder(node.right);
        }
        return result;
    }

    /**
     * 栈实现
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


}
