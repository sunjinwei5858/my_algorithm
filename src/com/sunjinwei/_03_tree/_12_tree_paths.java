package com.sunjinwei._03_tree;

import com.sunjinwei.domain.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * 二叉树的所有路径 力扣257 难度：简单
 * <p>
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 */
public class _12_tree_paths {

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

    /**
     * 回溯处理
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了32.54%的用户
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths2(TreeNode root) {
        if (root == null) {
            return result;
        }
        LinkedList<Integer> path = new LinkedList<>();
        process(root, path);
        return result;
    }

    private void process(TreeNode root, LinkedList<Integer> path) {

        path.add(root.val);

        // 终止条件：找到叶子节点
        if (root.left == null && root.right == null) {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < path.size() - 1; i++) {
                stringBuffer.append(path.get(i)).append("->");
            }
            stringBuffer.append(path.get(path.size() - 1));
            result.add(stringBuffer.toString());
            return;
        }
        if (root.left != null) {
            // 递归
            process(root.left, path);
            // 回溯：因为path不能一直添加节点 还要删除节点
            // 回溯和递归是一一对应的，有一个递归，就要有一个回溯
            // 这行代码不能放在if外面
            path.removeLast();
        }
        if (root.right != null) {
            // 递归
            process(root.right, path);
            // 回溯
            path.removeLast();
        }
    }


    /**
     * 回溯精简代码 代码虽然精简但把很多重要的点隐藏在了代码细节里
     * <p>
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths3(TreeNode root) {
        if (root == null) {
            return result;
        }
        String path = new String();
        process2(root, path);
        return result;
    }

    private void process2(TreeNode root, String path) {
        // 根节点
        path += root.val;
        if (root.left == null && root.right == null) {
            result.add(path);
            return;
        }
        // 左子树
        if (root.left != null) {
            // 回溯就隐藏在这里 因为是把 path+"->" 作为方法参数，这样并没有改变path的数值，执行完递归函数之后 path依然是之前的数值!!!!
            process2(root.left, path + "->");
        }
        // 右子树
        if (root.right != null) {
            process2(root.right, path + "->");
        }
    }

    /**
     * 回溯精简代码 代码虽然精简但把很多重要的点隐藏在了代码细节里
     * <p>
     *
     * @param root
     * @return
     */
    public List<String> binaryTreePaths4(TreeNode root) {
        if (root == null) {
            return result;
        }
        String path = new String();
        process3(root, path);
        return result;
    }

    private void process3(TreeNode root, String path) {
        // 根节点
        path += root.val;
        if (root.left == null && root.right == null) {
            result.add(path);
            return;
        }
        // 左子树
        if (root.left != null) {
            path += "->";
            // 回溯就隐藏在这里 因为是把 path+"->" 作为方法参数，这样并没有改变path的数值，执行完递归函数之后 path依然是之前的数值
            process3(root.left, path);
            // 回溯操作 removeLast
            // 回溯操作 removeLast
        }
        // 右子树
        if (root.right != null) {
            path += "->";
            process3(root.right, path);
            // 回溯操作 removeLast
            // 回溯操作 removeLast
        }
    }


    public static void main(String[] args) {
        _12_tree_paths binary_tree_paths = new _12_tree_paths();
        TreeNode root = new TreeNode(2);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;

        List<String> strings = binary_tree_paths.binaryTreePaths2(root);
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
