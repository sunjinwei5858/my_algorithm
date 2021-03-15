package com.sunjinwei._03_tree;

import com.sunjinwei.domain.TreeNode;

/**
 * 二叉搜索树的前序结果还原二叉搜索树 力扣1008
 * <p>
 * 思路：
 * 1。从二叉搜索树的特点出发：左子树小于根节点 右子树大于根节点
 * 2。二叉树后序遍历的特点：左子树 右子树 根节点，根节点位于数组的最后一个元素
 */
public class _08_b_rebuild_bst_II {


    private int[] preorder;

    public TreeNode bstFromPreorder(int[] preorder) {
        // 鲁棒性
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        this.preorder = preorder;
        return bstFromPreorder_help(0, preorder.length - 1);
    }

    /**
     * 思路：
     * 1。前序遍历 那么根节点就是数组的第一个元素
     * 2。接下来是左子树 然后是右子树
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了73.37%的用户
     *
     * @param begin
     * @param end
     * @return
     */
    private TreeNode bstFromPreorder_help(int begin, int end) {
        //  终止条件
        if (begin > end) {
            return null;
        }
        // 创建根节点
        TreeNode root = new TreeNode(preorder[begin]);
        // 找左子树的最大索引less 右子树的最小索引
        int less = begin;
        int more = end + 1;
        for (int i = begin + 1; i <= end; i++) {
            if (preorder[begin] > preorder[i]) {
                less = i;
            } else {
                if (more == end + 1) {
                    more = i;
                }
            }
        }
        root.left = bstFromPreorder_help(begin + 1, less);
        root.right = bstFromPreorder_help(more, end);
        return root;
    }

    public static void main(String[] args) {
        _08_b_rebuild_bst_II rebuild_tree_iiii = new _08_b_rebuild_bst_II();
        // 前序遍历数组
        int[] arr = new int[]{2, 3, 4, 5};

        TreeNode treeNode = rebuild_tree_iiii.bstFromPreorder(arr);
        System.out.println(treeNode.val);
    }
}
