package com.sunjinwei._03_tree;

import com.sunjinwei.domain.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 序列化和反序列化：层序遍历
 * 注意：
 * 1.每个节点都需要做一个标记，作为结束，使用!
 * 2.序列化和反序列化：空节点都不进入队列中去
 */
public class _03_serialize_deserialize_III {

    /**
     * 后缀
     */
    private String suffix = "!";

    private String nullMark = "#";

    /**
     * 序列化：空节点不进入队列，提前拼接到字符串中
     * <p>
     * 执行用时：123 ms, 在所有 Java 提交中击败了17.85%的用户
     * 内存消耗：40.9 MB, 在所有 Java 提交中击败了35.60%的用户
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return nullMark + suffix;
        }
        String s = root.val + suffix;
        Queue<TreeNode> queue = new LinkedList<>();
        // 先处理头节点
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.poll();
            if (treeNode.left != null) {
                s += treeNode.left.val + suffix;
                queue.offer(treeNode.left);
            } else {
                s += nullMark + suffix;
            }
            if (treeNode.right != null) {
                s += treeNode.right.val + suffix;
                queue.offer(treeNode.right);
            } else {
                s += nullMark + suffix;
            }
        }
        return s;
    }

    /**
     * 反序列化：提前处理好了头节点，和序列化方法2 serialize_02 照相呼应 【左神做法】
     */
    public TreeNode deserialize(String data) {
        // 1 这里的第二个equals判断 直接就是对根节点为空的判断 放在此处位置非常好
        if (data == null || data.equals(nullMark + suffix)) {
            return null;
        }
        // 2 切割
        String[] split = data.split(suffix);
        // 3 处理头节点
        int index = 0;
        TreeNode root = transformString2TreeNode(split[index]);
        Queue<TreeNode> queue = new LinkedList<>();
        // 4 将头节点入队列
        queue.offer(root);
        // 5 声明一个node变量 那么while就不需要重复new节点对象
        TreeNode node = null;
        /**
         * 队列中只存放不为null的节点 所以可以保证[++index]不会索引越界
         */
        while (!queue.isEmpty()) {
            // 队列中存放的都是父节点
            node = queue.poll();
            // 父节点对应左侧节点的值
            node.left = transformString2TreeNode(split[++index]);
            if (node.left != null) {
                queue.offer(node.left);
            }
            // 父节点对应右侧节点的值
            node.right = transformString2TreeNode(split[++index]);
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }

    /**
     * 将字符串转化为节点的方法
     *
     * @param s
     * @return
     */
    private TreeNode transformString2TreeNode(String s) {
        if (nullMark.equals(s)) {
            return null;
        }
        return new TreeNode(Integer.parseInt(s));
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
        String data = deserialize_iii.serialize(root);
        System.out.println(data);
        TreeNode deserialize = deserialize_iii.deserialize(data);
        System.out.println(deserialize);


    }
}
