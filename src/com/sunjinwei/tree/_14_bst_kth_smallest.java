package com.sunjinwei.tree;

import com.sunjinwei.domain.TreeNode;

/**
 * 二叉搜索树中第k小的元素 【力扣230】
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 */
public class _14_bst_kth_smallest {

    private int k;

    private int val;

    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        inorder(root);
        return val;
    }

    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        // 左子树
        inorder(root.left);
        k--;
        if (k == 0) {
            this.val = root.val;
            return;
        }
        inorder(root.right);
    }

}
