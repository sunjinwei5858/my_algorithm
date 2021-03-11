package com.sunjinwei.tree;

import com.sunjinwei.domain.TreeNode;

import java.util.Stack;

/**
 * 返回二叉树的镜像
 */
public class _10_mirror_tree {

    /**
     * 方法1：递归
     * 思路：递归处理 开始是根节点 交换左右孩子，然后左子树进行交换 将大问题转换为小问题
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.8 MB, 在所有 Java 提交中击败了59.46%的用户
     *
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.right = left;
        root.left = right;
        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }

    /**
     * 方法2：使用迭代 使用栈或者队列实现
     *
     * @param root
     */
    public TreeNode mirrorTree_02(TreeNode root) {
        if (root == null) {
            return null;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
        }
        return root;
    }


    public static void main(String[] args) {
        _10_mirror_tree mirror_tree = new _10_mirror_tree();
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;

        TreeNode left1 = new TreeNode(4);
        TreeNode right1 = new TreeNode(5);
        left.left = left1;
        left.right = right1;

        TreeNode treeNode = mirror_tree.mirrorTree_02(root);
        System.out.println(treeNode.val);
    }

}
