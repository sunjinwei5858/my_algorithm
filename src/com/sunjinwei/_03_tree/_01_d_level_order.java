package com.sunjinwei._03_tree;

import com.sunjinwei.domain.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的层序遍历
 * 方法1：bfs
 * 方法2：dfs
 */
public class _01_d_level_order {

    /**
     * 方法一：使用队列实现，其实是广度优先遍历bfs
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

    /**
     * 方法二：递归，其实是dfs 深度优先遍历
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了34.33%的用户
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder_02(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // 使用辅助函数: 外层的list的size和level才有关系
        // 层序遍历 使用dfs 那么其实就是前序遍历
        // 提前创建好每一层的容器
        help(root, 0, result);
        return result;
    }

    private void help(TreeNode root, int level, List<List<Integer>> result) {
        // 终止条件
        if (root == null) {
            return;
        }
        int size = result.size();
        // 关键逻辑：
        // 第一种情况：level=size 保证根节点
        if (level >= size) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            result.add(arrayList);
        }
        // 获取到当前层的存储集合
        List<Integer> integers = result.get(level);
        integers.add(root.val);
        // 进入下一层
        level++;
        // 左子树
        help(root.left, level, result);
        // 右子树
        help(root.right, level, result);
    }


}
