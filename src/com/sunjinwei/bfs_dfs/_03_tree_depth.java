package com.sunjinwei.bfs_dfs;

import com.sunjinwei.domain.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 求树的最小深度和最大深度
 */
public class _03_tree_depth {

    /**
     * 最小深度 解法一：bfs
     *
     * @param root
     * @return
     */
    public Integer minDepthBfs(TreeNode root) {
        int depth = 0;
        if (root == null) {
            return depth;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                if (treeNode.left == null && treeNode.right == null) {
                    return depth;
                }
                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }

                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
            }
            depth++;
        }
        return depth;
    }

    /**
     * 最小深度 解法二：dfs
     * 思路：递归，但是要注意有坑，需要注意二叉树变成一颗斜树即链表
     *
     * @param root
     * @return
     */
    public Integer minDepthDfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 判断是不是斜树 鲁棒性
        if (root.left == null) {
            return 1 + minDepthDfs(root.right);
        }
        // 判断是不是斜树 鲁棒性
        if (root.right == null) {
            return 1 + minDepthDfs(root.left);
        }
        Integer left = minDepthDfs(root.left);
        Integer right = minDepthDfs(root.right);
        return Math.min(left, right) + 1;
    }

    /**
     * 最大深度 解法一：bfs
     *
     * @param root
     * @return
     */
    public Integer maxDepthBfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            depth++;
        }
        return depth;
    }

    /**
     * 最大深度 解法二：dfs
     * 思想：递归
     *
     * @param root
     * @return
     */
    public Integer maxDepthDfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Integer left = maxDepthDfs(root.left);
        Integer right = maxDepthDfs(root.right);
        return Math.max(left, right) + 1;
    }


}
