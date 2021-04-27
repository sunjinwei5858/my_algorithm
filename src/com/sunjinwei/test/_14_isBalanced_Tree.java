package com.sunjinwei.test;

import com.sunjinwei.domain.TreeNode;

/**
 * @program: com.sunjinwei.test
 * @author: sun jinwei
 * @create: 2021-04-28 07:09
 * @description: 是否是平衡二叉树
 **/
public class _14_isBalanced_Tree {


    /**
     * 方法1：从上到下
     * <p>
     * 从根节点，然后判断左子树高度，右子树高度
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 计算左子树高度
        int left = height(root.left);
        // 计算右子树高度
        int right = height(root.right);

        // 对于根节点 判断高度差
        // 判断左子树所有高度
        // 判断右子树所有高度
        return Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    /**
     * 计算树的高度
     *
     * @param node
     * @return
     */
    private int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }


    /**
     * 方法2：从下向上
     * <p>
     * 自底向上递归的做法类似于后序遍历，对于当前遍历到的节点，先递归地判断其左右子树是否平衡，再判断以当前节点为根的子树是否平衡。
     * 如果一棵子树是平衡的，则返回其高度（高度一定是非负整数），否则返回 −1。如果存在一棵子树不平衡，则整个二叉树一定不平衡。
     *
     * @param root
     * @return
     */
    public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return help(root) != -1;
    }

    private int help(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = help(root.left);
        int right = help(root.right);
        if (left == -1 || right == -1) {
            return -1;
        }
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }


}