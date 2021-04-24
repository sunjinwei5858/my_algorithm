package com.sunjinwei.test;

import com.sunjinwei.domain.TreeNode;

import java.util.Stack;

/**
 * 翻转二叉树 力扣226
 * 二叉树的镜像  剑指
 * 这两道是一样的题目 只是叫法不一样
 */
public class _04_invert_tree {


    /**
     * 前序遍历实现： 自己的写法
     * 前序：先处理头节点 然后左子树 最后右子树
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        // 前序1：处理头节点
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = right;
        root.right = left;

        // 前序2：递归处理左子树
        invertTree(root.left);
        // 前序3：递归处理右子树
        invertTree(root.right);
        return root;
    }

    /**
     * 后序遍历实现： 官方题解写法
     * 后序：先处理左子树 然后右子树 最后头节点
     *
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return root;
        }
        // 后序1：递归处理左子树
        TreeNode left = invertTree2(root.left);
        // 后序2：递归处理右子树
        TreeNode right = invertTree2(root.right);

        // 后序3：最后处理头节点
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 迭代实现 使用栈实现前序遍历
     */
    public TreeNode invertTree3(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
            TreeNode left = pop.left;
            TreeNode right = pop.right;
            pop.left = right;
            pop.right = left;
        }
        return root;
    }


}
