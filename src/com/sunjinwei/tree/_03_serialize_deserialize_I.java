package com.sunjinwei.tree;

import com.sunjinwei.domain.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的序列化和反序列化: 前序遍历
 * 注意：
 * 1.两个标记：空节点使用"#",每个节点都附加上"!"
 * 2.单独抽取两个方法：a.将节点转为字符串 b.将字符串转为节点
 */
public class _03_serialize_deserialize_I {

    /**
     * 前序实现序列化
     *
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        // 1鲁棒性
        if (root == null) {
            return convertTreeNode2String(root);
        }
        // 2根节点
        String rootStr = convertTreeNode2String(root);
        // 3左子树
        rootStr += serialize(root.left);
        // 4右子树
        rootStr += serialize(root.right);
        return rootStr;
    }

    /**
     * 将节点变成字符串的方法
     *
     * @param node
     * @return
     */
    private String convertTreeNode2String(TreeNode node) {
        if (node == null) {
            return "#!";
        }
        return node.val + "!";
    }

    /**
     * 将字符串变成节点的方法
     *
     * @param s
     * @return
     */
    private TreeNode convertString2TreeNode(String s) {
        if ("#".equals(s)) {
            return null;
        }
        return new TreeNode(Integer.valueOf(s));
    }

    /**
     * 前序实现反序列化：依靠队列实现
     *
     * @param data
     */
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] arr = data.split("!");
        // 将数组转换成队列存储 使用Arrays工具类的asList方法 使代码更加优雅
        Queue<String> queue = new LinkedList<>(Arrays.asList(arr));
        // 前序递归处理
        return deserialize_help(queue);
    }

    private TreeNode deserialize_help(Queue<String> queue) {
        // 1终止条件1：队列为空
        if (queue.isEmpty()) {
            return null;
        }
        TreeNode treeNode = convertString2TreeNode(queue.poll());
        // 2终止条件2：如果是空节点 返回null即可
        if (treeNode == null) {
            return null;
        }
        // 3左子树
        treeNode.left = deserialize_help(queue);
        // 4右子树
        treeNode.right = deserialize_help(queue);
        return treeNode;
    }

    public static void main(String[] args) {
        _03_serialize_deserialize_I serializeDeserialize = new _03_serialize_deserialize_I();
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;

        TreeNode left2 = new TreeNode(4);
        TreeNode right3 = new TreeNode(5);

        right.left = left2;
        right.right = right3;


        String s = serializeDeserialize.serialize(root);
        System.out.println(s);

        TreeNode treeNode = serializeDeserialize.deserialize(s);
        System.out.println(treeNode.val);
    }
}
