package com.sunjinwei.tree;

import com.sunjinwei.domain.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 序列化和反序列化：层序遍历
 */
public class _03_serialize_deserialize_III {

    /**
     * 序列化：层序遍历
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return "#!";
        }
        // 初始化队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        String s = "";
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll == null) {
                s += "#!";
                continue;
            } else {
                s += poll.val + "!";
            }
            if (poll.left != null) {
                queue.offer(poll.left);
            } else {
                queue.offer(null);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            } else {
                queue.offer(null);
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
