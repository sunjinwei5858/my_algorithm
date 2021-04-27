package com.sunjinwei.test;

import com.sunjinwei.domain.TreeNode;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 翻转二叉树 力扣226
 * 二叉树的镜像  剑指
 * 这两道是一样的题目 只是叫法不一样
 */
public class _04_invert_tree_II {


    /**
     * bfs实现
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.2 MB, 在所有 Java 提交中击败了91.15%的用户
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            TreeNode left = node.left;
            TreeNode right = node.right;
            node.left = right;
            node.right = left;

            if (left != null) {
                queue.offer(left);
            }
            if (right != null) {
                queue.offer(right);
            }

        }
        return root;
    }


    /**
     * dfs实现
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.9 MB, 在所有 Java 提交中击败了49.11%的用户
     *
     * @param root
     * @return
     */
    public TreeNode invertTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            TreeNode left = node.left;
            TreeNode right = node.right;
            node.left = right;
            node.right = left;

            if (left != null) {
                stack.push(left);
            }
            if (right != null) {
                stack.push(right);
            }
        }

        return root;
    }


}
