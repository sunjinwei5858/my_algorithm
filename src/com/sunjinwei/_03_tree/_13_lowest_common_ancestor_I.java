package com.sunjinwei._03_tree;

import com.sunjinwei.domain.TreeNode;

/**
 * 二叉树的最近公共祖先 左神 力扣236  剑指offer
 * <p>
 * 百度百科中最近公共祖先的定义为：
 * “对于有根树T的两个节点p、q，最近公共祖先表示为一个节点x，满足x是p、q的祖先且x的深度尽可能大（一个节点也可以是它自己的祖先）。”
 */
public class _13_lowest_common_ancestor_I {

    /**
     * 方法1：
     * 思路：分为以下三种情况
     * 1。如果p和q都在以root为根的树中，函数返回的是p和q的最近公共祖先节点
     * 2。如果p和q都不再root为根的树中，返回null
     * 3。如果p和q只有一个存在于root为根的树中，返回存在的那个节点
     * <p>
     * 执行用时：7 ms, 在所有 Java 提交中击败了99.88%的用户
     * 内存消耗：40.5 MB, 在所有 Java 提交中击败了67.16%的用户
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // base case1：如果root为空 那么直接返回空
        if (root == null) {
            return null;
        }
        // base case2：如果p,q有其中一个等于root 那么直接返回root 【一个节点也可以是它自己的祖先】
        if (root == p || root == q) {
            return root;
        }
        // base case3：如果p和q不等于root 那么去root的左子树和右子树中找
        // 后序遍历处理：左树
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 后序遍历处理：右树
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 后序遍历处理：根节点
        // 情况1：如果left和right都不为空 说明在左子树中找到了p或q 说明在右树中找到了p或q 因为是后序遍历 所以最近的祖先可以确定就是root
        if (left != null && right != null) {
            return root;
        }
        // 情况2：如果left和right都为空 说明p和q不存在root为根节点的树中
        if (left == null && right == null) {
            return null;
        }
        // 情况3：一个为空 一个不为空 说明其中一个是 那么返回不为空的即可
        return left == null ? right : left;

    }
}
