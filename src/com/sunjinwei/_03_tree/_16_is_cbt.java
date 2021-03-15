package com.sunjinwei._03_tree;

import com.sunjinwei.domain.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 是否是完全二叉树 【左神】
 * 思路：通过以下判断会变得易懂且容易实现
 * 层序遍历
 * 分为以下两种情况：
 * 1.有右孩子 但是没有左孩子 返回false
 * 2.如果当前节点并不是都有左右孩子，那么下一个节点必须是叶子节点 否则返回false
 */
public class _16_is_cbt {

    /**
     * 方法1：层序遍历
     *
     * @param root
     * @return
     */
    public boolean isCbt(TreeNode root) {

        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode left = null;
        TreeNode right = null;

        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            // 1 左孩子为空且右孩子不为空 返回false
            if (left == null && right != null) {
                return false;
            }
            // 2 如果当前节点并不是都有左右孩子 ==》也就是左孩子不为空 右孩子为空 此时左孩子必须是叶子节点
            if (left != null && right == null) {
                if (left.left != null || left.right != null) {
                    return false;
                }
            }
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
        }
        return true;
    }


    /**
     * 方法2：层序遍历 【左神的写法】【对自己的代码进行重构】
     */
    public boolean isCbt_02(TreeNode root) {

        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 1声明是否是叶子节点的布尔变量
        boolean leafIs = false;
        // 2左孩子
        TreeNode left = null;
        // 3右孩子
        TreeNode right = null;

        while (!queue.isEmpty()) {
            root = queue.poll();
            left = root.left;
            right = root.right;
            // 判断1：有右孩子但是没有左孩子 返回false
            if (left == null && right != null) {
                return false;
            }
            // 判断2：是叶子节点 但是左右孩子可能不为空 返回false
            if (leafIs && (left != null || right != null)) {
                return false;
            }
            // 处理左孩子
            if (left != null) {
                queue.offer(left);
            }
            // 处理右孩子
            if (right != null) {
                // 走到这里 右孩子入队列
                queue.offer(right);
            } else {
                // 因为走到了 排除了左树为空 右树不为空的情况
                // 所以走到这里 肯定是左树已经为空 右树如果也为空 修改为叶子节点状态
                leafIs = true;
            }
        }
        return true;
    }

}
