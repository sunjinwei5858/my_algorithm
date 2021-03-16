package com.sunjinwei._03_tree;

import com.sunjinwei.domain.TreeNode;

/**
 * bst的中序结果还原bst 【自己】
 * <p>
 * 思路：
 * 1。从二叉搜索树的特点出发：左子树小于根节点 右子树大于根节点
 * 2。二叉树中序遍历的特点：左子树 根节点 右子树，根节点位于数组的中间元素
 */
public class _08_b_rebuild_bst_II {


    private int[] preorder;

    public TreeNode bstFromPreorder(int[] preorder) {
        // 鲁棒性
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        this.preorder = preorder;
        // 左闭右闭
        return help(0, preorder.length - 1);
    }

    private TreeNode help(int left, int right) {
        if (left > right) {
            return null;
        }
        // 根节点索引
        int middle = findMiddle(left, right);
        // 创建根节点
        TreeNode root = new TreeNode(preorder[middle]);
        // 左子树
        root.left = help(left, middle - 1);
        // 右子树
        root.right = help(middle + 1, right);
        return root;
    }

    /**
     * 寻找中点
     */
    private int findMiddle(int left, int right) {

        return left + ((right - left) >> 1);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3};
        _08_b_rebuild_bst_II b_rebuild_bst_ii = new _08_b_rebuild_bst_II();
        TreeNode treeNode = b_rebuild_bst_ii.bstFromPreorder(arr);
        System.out.println(treeNode);
    }


}
