package com.sunjinwei.test;

import com.sunjinwei.domain.TreeNode;

/**
 * 翻转二叉树 力扣226
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


}
