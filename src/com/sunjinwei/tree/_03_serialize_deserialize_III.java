package com.sunjinwei.tree;

import com.sunjinwei.domain.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 序列化和反序列化：层序遍历
 */
public class _03_serialize_deserialize_III {

    /**
     * 序列化：层序遍历,方法一：空节点也进入队列
     */
    public String serialize(TreeNode root) {
        // 初始化队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        String s = "";
        // 第一种方式进行处理：空节点也加入到队列
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            if (treeNode != null) {
                s += treeNode.val + "!";
            } else {
                s += "#!";
                // 如果该节点为空 那么直接continue
                continue;
            }
            queue.offer(treeNode.left);
            queue.offer(treeNode.right);
        }
        return s;
    }

    /**
     * 序列化：层序遍历,方法二：空节点不进入队列，提前拼接到字符串中
     */
    public String serialize_02(TreeNode root) {
        if (root == null) {
            return "#!";
        }
        String s = root.val + "!";
        Queue<TreeNode> queue = new LinkedList<>();
        // 先处理头节点
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            if (treeNode.left != null) {
                s += treeNode.left.val + "!";
                queue.offer(treeNode.left);
            } else {
                s += "#!";
            }
            if (treeNode.right != null) {
                s += treeNode.right.val + "!";
                queue.offer(treeNode.right);
            } else {
                s += "#!";
            }
        }
        return s;
    }

    /**
     * 反序列化：
     */
    public TreeNode deserialize(String data) {
        if (data == null || data == "") {
            return null;
        }
        String[] split = data.split("!");
        Queue<String> queue = new LinkedList<>();
        for (String s : split) {
            queue.offer(s);
        }
        String poll = queue.poll();
        TreeNode root = new TreeNode(Integer.parseInt(poll));
        while (!queue.isEmpty()) {
            String s = queue.poll();
            if (s != "#") {

            } else {

            }
        }
        return new TreeNode(1);
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

        _03_serialize_deserialize_III deserialize_iii = new _03_serialize_deserialize_III();
        String serialize = deserialize_iii.serialize(root);
        System.out.println(serialize);
    }
}
