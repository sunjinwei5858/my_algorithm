package com.sunjinwei._03_tree;

import com.sunjinwei.domain.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 求二叉树最大深度 【力扣104】
 * ps：
 * 1理解高度和深度的意思，高度是自底向上(后序遍历)，深度是自顶向上(前序遍历)
 * 2这道题可以联想到[判断是否是平衡树]，因为平衡树是判断高度
 */
public class _07_tree_depth {

    /**
     * 方法1：后序遍历【这样相当于求根节点的高度】
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了80.58%
     * 的用户
     *
     * @param root
     * @return
     */
    public int getTreeDepth_01(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 左树
        int left = getTreeDepth_01(root.left);
        // 右树
        int right = getTreeDepth_01(root.right);
        // 根节点
        int depth = Math.max(left, right) + 1;
        return depth;
    }

    /**
     * 方法2：前序遍历【求最大深度 理论上是从上到下 所以前序遍历才是最大深度的理论姿势】
     *
     * @param root
     */
    private int result = 0;

    public int getTreeDepth_02(TreeNode root) {
        if (root == null) {
            return 0;
        }
        process(root, 1);
        return result;
    }

    private void process(TreeNode root, int depth) {
        if (depth > result) {
            result = depth;
        }
        // 终止条件1
        if (root == null) {
            return;
        }
        // 终止条件2
        if (root.left == null && root.right == null) {
            return;
        }
        process(root.left, depth + 1);
        process(root.right, depth + 1);
    }


    /**
     * 方法3：层序遍历
     * 执行用时：1 ms, 在所有 Java 提交中击败了21.95%的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了98.70%的用户
     *
     * @param root
     * @return
     */
    public int getTreeDepth_03(TreeNode root) {
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
