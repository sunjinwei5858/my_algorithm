package com.sunjinwei._03_tree;

import com.sunjinwei.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;


/**
 * 二叉树的所有路径 力扣257 难度：简单
 * <p>
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 */
public class _12_binary_tree_paths {

    private List<String> result = new ArrayList<>();


    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) {
            return result;
        }
        help(root, "");
        return result;
    }


    private void help(TreeNode root, String path) {
        // 直接stop
        if (root == null) {
            return;
        }
        path += root.val;
        // 如果是叶子节点：左孩子为空并且右孩子也为空
        if (root.left == null && root.right == null) {
            result.add(path);
        } else {
            // 说明不是叶子 继续递归
            path += "->";
            help(root.left, path);
            help(root.right, path);
        }
    }

    public static void main(String[] args) {
        _12_binary_tree_paths binary_tree_paths = new _12_binary_tree_paths();
        TreeNode root = new TreeNode(2);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;

        List<String> strings = binary_tree_paths.binaryTreePaths(root);
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
