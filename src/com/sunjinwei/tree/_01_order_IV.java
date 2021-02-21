package com.sunjinwei.tree;

import com.sunjinwei.domain.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的层序遍历
 */
public class _01_order_IV {

    /**
     * 方法一：使用队列实现，其实是广度优先遍历
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了94.41%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了77.58%的用户
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
