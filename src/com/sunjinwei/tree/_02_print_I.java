package com.sunjinwei.tree;

import com.sunjinwei.domain.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 剑指offer32-I：从上到下打印二叉树I
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 * <p>
 * 题目要求的二叉树的 从上至下 打印（即按层打印），又称为二叉树的 广度优先搜索（BFS）。
 * BFS 通常借助 队列 的先入先出特性来实现。
 */
public class _02_print_I {

    /**
     * 方法一：层序遍历
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.76%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了59.24%的用户
     *
     * @param root
     * @return
     */
    public int[] levelOrder(TreeNode root) {
        // 1鲁棒性
        if (root == null) {
            return new int[0];
        }
        // 2声明队列
        LinkedList<TreeNode> queue = new LinkedList();
        // 3声明结果集合
        List<Integer> list = new ArrayList();
        // 4初始化队列
        queue.offer(root);
        // 5处理
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        // 6将集合转为数组
        int[] arr = new int[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}
