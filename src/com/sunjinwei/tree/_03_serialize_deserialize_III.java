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
        // 第一种方式进行处理：空节点也加入到队列
        handleQueue_01(queue, s);
        // 第二种处理方式：空节点不加入队列 直接拼接到字符串中
        //handleQueue_02(queue,s);
        return s;
    }

    /**
     * 第一种处理方式：队列中存放空节点
     */
    private void handleQueue_01(Queue<TreeNode> queue, String s) {
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            if (treeNode != null) {
                s += treeNode.val + "!";
            } else {
                s += "#!";
                // 到达叶子节点之后 直接continue 叶子节点已经为空 不需要再进行加入任何空的节点
                continue;
            }
            queue.offer(treeNode.left);
            queue.offer(treeNode.right);
        }
    }


    /**
     * 第二种处理方式：队列中不放空节点
     */
    private void handleQueue_02(Queue<TreeNode> queue, String s) {
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            s += treeNode.val + "!";
            if (treeNode.left != null) {
                queue.offer(treeNode.left);
            } else {
                s += "#!";
            }
            if (treeNode.right != null) {
                queue.offer(treeNode.right);
            } else {
                s += "#!";
            }
        }
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
