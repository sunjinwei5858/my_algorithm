package com.sunjinwei._03_tree;

import com.sunjinwei.domain.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 剑指offer32-II：从上到下打印二叉树II
 * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 * 注意：同一层的节点从左到右
 * <p>
 * 题目要求的二叉树的 从上至下 打印（即按层打印），又称为二叉树的 广度优先搜索（BFS）。
 * BFS 通常借助 队列 的先入先出特性来实现。
 */
public class _02_print_II {

    /**
     * 方法一：层序遍历
     * 执行用时：1 ms, 在所有 Java 提交中击败了93.49%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了30.06%的用户
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = deque.poll();
                list.add(treeNode.val);
                if (treeNode.left != null) {
                    deque.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    deque.offer(treeNode.right);
                }
            }
            result.add(list);
        }
        return result;

    }
}
