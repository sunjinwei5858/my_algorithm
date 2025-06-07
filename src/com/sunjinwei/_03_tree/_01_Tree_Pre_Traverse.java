package com.sunjinwei._03_tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @program: com._2023.tree
 * @author: sun jinwei
 * @create: 2023-10-10 09:01
 * @description: 二叉树的前序遍历
 **/
public class _01_Tree_Pre_Traverse {

    static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;
    }

    /**
     * 非递归写法: 先根节点 再左子树 最后右子树
     * 利用栈的先进后出，将所有的节点压入栈中进行处理，先将根节点入栈，进行while循环，然后将右子树入栈，再将左子树入栈
     *
     * @param root
     * @return
     */
    public static List<TreeNode> preTraverse(TreeNode root) {
        List<TreeNode> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            // 前序遍历 先处理根节点
            res.add(cur);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.value = 1;
        TreeNode left1 = new TreeNode();
        left1.value = 2;
        TreeNode left1_left = new TreeNode();
        left1_left.value = 4;
        TreeNode left1_right = new TreeNode();
        left1_right.value = 5;
        TreeNode right1 = new TreeNode();
        right1.value = 3;
        TreeNode right1_left = new TreeNode();
        right1_left.value = 6;
        TreeNode right1_right = new TreeNode();
        right1_right.value = 7;

        root.left = left1;
        root.right = right1;
        left1.left = left1_left;
        left1.right = left1_right;
        right1.left = right1_left;
        right1.right = right1_right;
        List<TreeNode> treeNodes = preTraverse(root);
        treeNodes.forEach(it -> System.out.println(it.value));
    }

}