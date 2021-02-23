package com.sunjinwei.tree;

import com.sunjinwei.domain.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化和反序列化
 */
public class _03_serialize_deserialize {

    /**
     * 序列化: 将二叉树序列化为一个字符串
     * 遇到空节点，记为“#!”
     * 如果不是空节点 获取到节点的val，拼接“!”
     * !：代表一个节点的结束
     *
     * @param root
     * @return
     */
    public String serialByPre(TreeNode root) {
        if (root == null) {
            return "#!";
        }
        return doSerializeByPre(root, "");
    }

    private String doSerializeByPre(TreeNode root, String s) {
        if (root == null) {
            return s + "#!";
        }
        s = s + root.val + "!";
        s = doSerializeByPre(root.left, s);
        s = doSerializeByPre(root.right, s);
        return s;
    }

    /**
     * 反序列化
     *
     * @param data
     */
    public TreeNode deSerializeByPre(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] arr = data.split("!");
        // 将数组转换成队列存储
        Queue<String> queue = new LinkedList<>();
        for (String s1 : arr) {
            queue.offer(s1);
        }
        return dodeSerializeByPre(queue);
    }

    private TreeNode dodeSerializeByPre(Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        String s = queue.poll();
        if (s.equals("#")) {
            return null;
        }
        TreeNode treeNode = new TreeNode(Integer.parseInt(s));
        treeNode.left = dodeSerializeByPre(queue);
        treeNode.right = dodeSerializeByPre(queue);
        return treeNode;
    }

    public static void main(String[] args) {
        _03_serialize_deserialize serializeDeserialize = new _03_serialize_deserialize();
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;

        TreeNode left2 = new TreeNode(4);
        TreeNode right3 = new TreeNode(5);

        right.left = left2;
        right.right = right3;


        String s = serializeDeserialize.serialByPre(root);
        System.out.println(s);

        TreeNode treeNode = serializeDeserialize.deSerializeByPre(s);
        System.out.println(treeNode.val);
    }
}
