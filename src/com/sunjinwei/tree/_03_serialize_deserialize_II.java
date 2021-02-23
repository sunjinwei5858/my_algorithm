package com.sunjinwei.tree;

import com.sunjinwei.domain.TreeNode;

import java.util.LinkedList;

/**
 * 二叉树的序列化和反序列化: 后序遍历的方式
 * 序列化：直接使用后序遍历模板
 * 反序化：使用双端队列，处理顺序：根右左，因为双端队列，队尾是根节点 接着是右子树 接着是左子树
 * 两处标记：
 * 标记1：空节点记为 “#!”
 * 标记2：不是空节点，需要进行拼接：“!”
 * <p>
 * 执行用时：22 ms, 在所有 Java 提交中击败了50.28%的用户
 * 内存消耗：40.6 MB, 在所有 Java 提交中击败了60.30%的用户
 */
public class _03_serialize_deserialize_II {

    /**
     * 序列化: 将二叉树序列化为一个字符串
     *
     * @param root
     * @return
     */
    public String serializeByPost(TreeNode root) {
        if (root == null) {
            return "#!";
        }
        String s = serializeByPost(root.left);
        s += serializeByPost(root.right);
        s += root.val + "!";
        return s;
    }

    /**
     * 反序列化: 使用双端队列进行存储 可以保证序列化的是：左右根
     * 反序列化的顺序从 根右左 这样来处理即可 和前序遍历找相呼应
     *
     * @param data
     */
    public TreeNode deSerializeByPost(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        String[] arr = data.split("!");
        // 将数组转换成队列存储
        LinkedList<String> queue = new LinkedList<>();
        for (String s1 : arr) {
            queue.offer(s1);
        }
        return dodeSerializeByPost(queue);
    }

    private TreeNode dodeSerializeByPost(LinkedList<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        // 队列的尾部就是根节点
        String s = queue.removeLast();
        if (s.equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(s));
        // 先处理右子树
        root.right = dodeSerializeByPost(queue);
        root.left = dodeSerializeByPost(queue);
        return root;
    }

    public static void main(String[] args) {
        _03_serialize_deserialize_II serializeDeserialize = new _03_serialize_deserialize_II();
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;

        TreeNode left2 = new TreeNode(4);
        TreeNode right3 = new TreeNode(5);

        right.left = left2;
        right.right = right3;


        String s = serializeDeserialize.serializeByPost(root);
        System.out.println(s);

        TreeNode treeNode = serializeDeserialize.deSerializeByPost(s);
        System.out.println(treeNode.val);
    }
}
