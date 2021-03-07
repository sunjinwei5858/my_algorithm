package com.sunjinwei.tree;


import com.sunjinwei.domain.TreeNode;

/**
 * 根据根节点验证二叉搜索树 力扣98
 * 思路一：
 * 1.首先要知道二叉搜索树有什么性质可以给我们利用，
 * 2.
 * 性质1：如果该二叉树的左子树不为空，则左子树上所有节点的值均小于根节点的值，
 * 性质2：如果该二叉树的右子树不为空，则右子树上所有节点的值均大于根节点的值
 * 根据性质，可以启示设计一个递归函数helper(root, lower, upper) 来递归判断
 * 3.根据二叉搜索树的性质，在递归调用左子树时，需要把上界upper改为root.val
 * 因为左子树里所有节点的值均小于它的根节点的值
 * <p>
 * 注意：如下自己写出了错误方法和正确方法，方便自己比较
 * <p>
 * 思路二：
 * 使用中序遍历
 */
public class _04_a_is_bst_by_node {

    /**
     * 错误写法：二叉搜索树的性质有：BST的每个节点应该小于右子树的所有节点
     * 这种写法不能保证剩下的子树 比如左子树所有节点的值小于根节点 右子树所有节点的值大于根节点
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null && root.val <= root.left.val) {
            return false;
        }
        if (root.right != null && root.val >= root.right.val) {
            return false;
        }
        return isValidBST(root.left) && isValidBST(root.right);
    }

    /**
     * 正确写法： 使用辅助函数，其实就是除了当前节点 还需要传父节点 这样就可以保证左小于父，父小于右
     *
     * @param root
     * @return
     */
    public boolean isValidBST02(TreeNode root) {

        return help(root, null, null);
    }

    /**
     * 辅助函数： 其实思想是前序遍历
     * 当前节点是左节点 就需要传入最大值root
     * 当前节点是右节点 就需要传入最小值root
     *
     * @param root
     * @param min  对于右子树来说，根节点是最小值
     * @param max  对于左子树来说，根节点是最大值
     * @return
     */
    private boolean help(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        // 如果min不为空 说明是右子树和根节点比较
        // 传入右子树和根节点 根节点作为min传入 如果根节点的值大于等于右子树的值 返回false
        if (min != null && min.val >= root.val) {
            return false;
        }
        // 如果max不为空 说明是左子树和根节点比较
        // 传入左子树和根节点 根节点作为max传入 如果根节点的值小于等于左子树的值 返回false
        if (max != null && max.val <= root.val) {
            return false;
        }
        // 前序遍历逻辑完
        // 处理左子树
        boolean leftFlag = help(root.left, min, root);
        // 处理右子树
        boolean rightFlag = help(root.right, root, max);
        return leftFlag && rightFlag;
    }

    /**
     * 正确解法二：中序遍历,
     * 判断一棵二叉树是否为搜索二叉树，只要改写一个二叉树中序遍历，在遍历的过程中看节点值是否都是递增的即可
     * 中序遍历时，判断当前节点是否大于中序遍历的前一个节点，如果大于，说明满足 BST，继续遍历；否则直接返回 false。
     */
    public boolean isBst(TreeNode root) {

        return help_02(root, Integer.MIN_VALUE);
    }

    private boolean help_02(TreeNode root, int preValue) {
        if (root == null) {
            return true;
        }
        // 访问左子树
        boolean left = help_02(root.left, preValue);
        if (!left) {
            return false;
        }
        // 访问当前节点
        if (root.val <= preValue) {
            return false;
        }
        preValue = root.val;
        // 访问右子树
        boolean right = help_02(root, preValue);
        return right;
    }


}
