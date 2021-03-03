package com.sunjinwei.tree;


import com.sunjinwei.domain.TreeNode;

/**
 * 根据根节点验证二叉搜索树 力扣98
 * 思路：
 * 1.首先要知道二叉搜索树有什么性质可以给我们利用，
 * 2.
 * 性质1：如果该二叉树的左子树不为空，则左子树上所有节点的值均小于根节点的值，
 * 性质2：如果该二叉树的右子树不为空，则右子树上所有节点的值均大于根节点的值
 * 根据性质，可以启示设计一个递归函数helper(root, lower, upper) 来递归判断
 * 3.根据二叉搜索树的性质，在递归调用左子树时，需要把上界upper改为root.val
 * 因为左子树里所有节点的值均小于它的根节点的值
 */
public class _04_is_bst_I {

    /**
     * 错误写法：
     * 根节点大于左子树的值
     * 根节点小于右子树的值
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null && root.val < root.left.val) {
            return false;
        }
        if (root.right != null && root.val > root.right.val) {
            return false;
        }
        return isValidBST(root.left) && isValidBST(root.right);
    }

    /**
     * 正确写法： 使用辅助函数
     *
     * @param root
     * @return
     */
    public boolean isValidBST02(TreeNode root) {

        return isValidBST02(root, null, null);
    }

    /**
     * 辅助函数：根节点必须大于左子树的最大值，并且根节点小于右子树的最小值
     *
     * @param root
     * @param min  对于右子树来说，根节点是最小值
     * @param max  对于左子树来说，根节点是最大值
     * @return
     */
    public boolean isValidBST02(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        // 如果min不为空 说明是右子树和根节点比较 传入右子树和根节点 根节点作为min传入 如果根节点的值大于等于右子树的值 返回false
        if (min != null && min.val > root.val) {
            return false;
        }
        // 如果max不为空 说明是左子树和根节点比较 传入左子树和根节点 根节点作为max传入 如果根节点的值小于等于左子树的值 返回false
        if (max != null && max.val < root.val) {
            return false;
        }
        return isValidBST02(root.left, min, root) && isValidBST02(root.right, root, max);
    }

    private boolean judgeTreeIsBst(TreeNode root, TreeNode min, TreeNode max) {
        // 鲁棒性1
        if (root == null) {
            return true;
        }
        // 鲁棒性2
        // min不为空 说明是右子树和根节点比较 min是右子树
        if (min != null && root.val < min.val) {
            return false;
        }
        // 鲁棒性3
        // max不为空 说明是左子树和根节点比较 max是左子树
        if (max != null && root.val > max.val) {
            return false;
        }
        return judgeTreeIsBst(root.right, root, max) && judgeTreeIsBst(root.left, min, root);
    }


}
