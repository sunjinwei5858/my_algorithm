package com.sunjinwei.tree;

import com.sunjinwei.domain.TreeNode;

/**
 * bst的最近公共祖先 力扣235
 * 思路：
 * 利用bst的特性：左子树的值小于根节点 根节点的值小于右子树
 * 如果p,q的值 都大于根节点 那么去右子树中找
 * 如果p,q的值 都小于根节点 那么去左子树中找
 * 如果一个大于 一个小于，那么最近公共祖先就是root
 * <p>
 * 注意：base case 都是 一个节点也可以是它自己的祖先
 */
public class _13_lowest_common_ancestor_III {

    /**
     * 方法1：递归，利用bst的性质
     * <p>
     * 执行用时：6 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了43.63%的用户
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // base case
        if (p.val == root.val || q.val == root.val) {
            return root;
        }
        // 右子树找
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        // 左子树找
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        // 一个在左树 一个在右树
        return root;
    }
}
