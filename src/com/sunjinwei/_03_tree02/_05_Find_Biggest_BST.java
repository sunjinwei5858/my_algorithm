package com.sunjinwei._03_tree02;

import com.sunjinwei.domain.TreeNode;

/**
 * 找到二叉树中最大的二叉搜索树，返回头节点
 */
public class _05_Find_Biggest_BST {

    public TreeNode findBiggestBst(TreeNode root) {

        // 树形dp处理：后序遍历
        return process(root).maxTreeNode;
    }

    /**
     * 后序遍历
     * 情况1：在左树中
     * 情况2：在右树中
     * 情况3：root节点就是
     *
     * @param root
     * @return
     */
    private BstReturnType process(TreeNode root) {

        if (root == null) {
            return new BstReturnType(null, 0, 0, 0);
        }

        BstReturnType leftReturn = process(root.left);
        BstReturnType rightReturn = process(root.right);

        // 1 maxSize: 情况1和情况2中取最大
        int maxSize = Math.max(leftReturn.maxSize, rightReturn.maxSize);
        // 2 maxTreeNode: 情况1和情况2取最大
        TreeNode maxTreeNode = leftReturn.maxSize > rightReturn.maxSize ? leftReturn.maxTreeNode : rightReturn.maxTreeNode;
        // 3 min
        int min = Math.min(root.val, Math.min(leftReturn.rightTreeMin, rightReturn.rightTreeMin));
        // 4 max
        int max = Math.min(root.val, Math.max(leftReturn.leftTreeMax, rightReturn.leftTreeMax));
        // 5 根据rightTreeMin和leftTreeMax 判断是否存在情况3
        if (root.left == leftReturn.maxTreeNode && root.right == rightReturn.maxTreeNode
                && root.val > leftReturn.leftTreeMax && root.val < rightReturn.rightTreeMin
        ) {
            maxSize = leftReturn.maxSize + rightReturn.maxSize + 1;
            maxTreeNode = root;
        }

        return new BstReturnType(maxTreeNode, maxSize, min, max);
    }

}

/**
 * 树形dp返回值
 */
class BstReturnType {

    TreeNode maxTreeNode;

    // 表示节点个数
    int maxSize;

    // 右子树上的最小值
    int rightTreeMin;

    // 左子树上的最大值
    int leftTreeMax;

    public BstReturnType(TreeNode maxTreeNode, int maxSize, int rightTreeMin, int leftTreeMax) {
        this.maxTreeNode = maxTreeNode;
        this.maxSize = maxSize;
        this.rightTreeMin = rightTreeMin;
        this.leftTreeMax = leftTreeMax;
    }
}
