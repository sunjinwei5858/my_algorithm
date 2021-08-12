package com.sunjinwei._03_tree02;

import com.sunjinwei.domain.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @program: com.sunjinwei._03_tree02
 * @author: sun jinwei
 * @create: 2021-08-13 07:19
 * @description: 力扣199 二叉树的右视图
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * 思路：
 * 其实就是找右子树 最右节点，使用层序遍历和变种的前序遍历即可
 **/
public class _07_Right_Side_View {

    public List<Integer> res = new ArrayList<>();

    /**
     * 方法1： dfs 前序遍历变种（根节点 右子树 左子树）
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {

        if (root == null) {
            return res;
        }
        dfs(root, 0);
        return res;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        // 如果当前节点所在深度还没有出现在res里 说明该节点在该深度 是第一次访问
        if (depth == res.size()) {
            res.add(depth);
        }
        dfs(root.right, depth + 1);
        dfs(root.left, depth + 1);
    }


    /**
     * 方法2：层序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> rightSideView2(TreeNode root) {

        if (root == null) {
            return res;
        }
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
                // 最右边的节点
                if (i == size - 1) {
                    res.add(node.val);
                }
            }
        }
        return res;
    }

}