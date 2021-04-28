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
     * 前序递归实现： 自己的写法
     * 前序：先处理头节点 然后左子树 最后右子树
     *
     * @param root
     * @return
     */
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) {
            return root;
        }
        // 前序1：处理头节点
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = right;
        root.right = left;

        // 前序2：递归处理左子树
        invertTree1(root.left);
        // 前序3：递归处理右子树
        invertTree1(root.right);
        return root;
    }

    /**
     * 前序迭代实现
     */
    public TreeNode invertTree2(TreeNode root) {
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


    /**
     * 中序递归实现：左 根 右
     *
     * @param root
     * @return
     */
    public TreeNode invertTree3(TreeNode root) {
        if (root == null) {
            return null;
        }

        invertTree3(root.left);
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = right;
        root.right = left;
        // 注意 这里应该右子树
        invertTree3(right); // 即root.left
        return root;
    }

    /**
     * 中序迭代实现：左 根 右
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36 MB, 在所有 Java 提交中击败了30.25%的用户
     *
     * @param root
     * @return
     */
    public TreeNode invertTree5(TreeNode root) {
        if (root == null) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();

        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {

            // 一直将左子树进栈
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // 从栈弹出 开始 左 根 右 的处理
            if (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                TreeNode left = pop.left;
                TreeNode right = pop.right;
                pop.right = left;
                pop.left = right;
                // 注意!!! 这里应该是right 右子树
                curr = right;
            }
        }
        return root;
    }


    /**
     * 后序递归遍历实现： 官方题解写法
     * 后序：先处理左子树 然后右子树 最后头节点
     *
     * @param root
     * @return
     */
    public TreeNode invertTree4(TreeNode root) {
        if (root == null) {
            return root;
        }
        // 后序1：递归处理左子树
        TreeNode left = invertTree4(root.left);
        // 后序2：递归处理右子树
        TreeNode right = invertTree4(root.right);

        // 后序3：最后处理头节点
        root.left = right;
        root.right = left;
        return root;
    }


}
