package com.sunjinwei.tree;

import com.sunjinwei.domain.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 求二叉树的深度 ps: 最大深度
 * 方法1：递归
 * 方法2：迭代
 */
public class _07_tree_depth {

    /**
     * 方法2：递归，思路：前序遍历 根左右 O(n)
     * 1如果二叉树为空 二叉树深度为0
     * 2如果二叉树不为空 二叉树深度=max(左子树，右子树)+1
     *
     * @param root
     * @return
     */
    public int getTreeDepth_01(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getTreeDepth_01(root.left);
        int right = getTreeDepth_01(root.right);
        return Math.max(left, right) + 1;
    }


    /**
     * 方法2：迭代，思路：层序遍历
     *
     * @param root
     * @return
     */
    public int getTreeDepth_02(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 声明队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 初始化深度
        int depth = 0;
        // 处理深度
        while (!queue.isEmpty()) {
            // 每次都将队列的节点全部处理
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            // 等队列同一层的所有节点处理完 此时深度++
            depth++;
        }
        return depth;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;

        TreeNode left2 = new TreeNode(4);
        TreeNode right3 = new TreeNode(5);

        right.left = left2;
        right.right = right3;

        _07_tree_depth tree_depth = new _07_tree_depth();
        int treeDepth_02 = tree_depth.getTreeDepth_02(root);
        System.out.println(treeDepth_02);
    }
}
