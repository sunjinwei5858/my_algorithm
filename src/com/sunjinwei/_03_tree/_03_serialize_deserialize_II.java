package com.sunjinwei._03_tree;

import com.sunjinwei.domain.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树的序列化和反序列化: 后序遍历
 * 注意事项：
 * 1。该抽取的方法和前序一样
 * 2。需要注意，使用双端队列，那么处理就可以从尾巴开始，接着是右子树，最后是左子树
 * 【除了利用双端队列 当不是双端队列时 还可以放入栈中 这样栈的头部就是头右左了 然后进行处理】
 * 3。总结：
 * 其实前序和后序都可以实现序列化，但是反序列化 其实都是用的前序的思想处理，
 * a.前序序列化，那么反序列化，也直接前序，队列直接从头部开始 根节点->左子树->右子树
 * b.后序序列化，那么反序列化，思想也是前序，队列直接从尾巴开始 根节点->右子树->左子树 反过来的 但是思想和前序是一样的
 * <p>
 * 执行用时：22 ms, 在所有 Java 提交中击败了50.28%的用户
 * 内存消耗：40.6 MB, 在所有 Java 提交中击败了60.30%的用户
 */
public class _03_serialize_deserialize_II {

    /**
     * 后序实现序列化
     *
     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        // 1鲁棒性
        if (root == null) {
            return convertTreeNode2String(root);
        }
        // 2左子树
        String s = serialize(root.left);
        // 3右子树
        s += serialize(root.right);
        // 4根节点
        s += convertTreeNode2String(root);
        return s;
    }

    /**
     * 双端队列实现反序列化
     *
     * @param data
     */
    public TreeNode deserialize(String data) {
        // 1
        if (data == null || data.length() == 0) {
            return null;
        }
        // 2 切割
        String[] arr = data.split("!");
        // 3将数组转换成双端队列存储
        LinkedList<String> queue = new LinkedList<>(Arrays.asList(arr));
        // 4递归
        return dodeSerialize_help(queue);
    }

    private TreeNode dodeSerialize_help(LinkedList<String> queue) {
        // 1鲁棒性
        if (queue.isEmpty()) {
            return null;
        }
        // 2队列的尾部就是根节点
        String s = queue.removeLast();
        TreeNode root = convertString2TreeNode(s);
        if (root == null) {
            return null;
        }
        // 3处理右子树 和前序要反过来
        root.right = dodeSerialize_help(queue);
        // 4处理左子树
        root.left = dodeSerialize_help(queue);
        return root;
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
     * 方法2：使用栈代替双端队列来代替反转
     * 后序：左右头--》栈（头右左）
     *
     * @param queue
     */
    public TreeNode serialize02(Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        Stack<String> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(stack.pop());
        }
        return help(stack);
    }

    private TreeNode help(Stack<String> stack) {
        String value = stack.pop();
        if (value == null) {
            return null;
        }
        TreeNode root = convertString2TreeNode(value);
        root.right = help(stack);
        root.left = help(stack);
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


        String s = serializeDeserialize.serialize(root);
        System.out.println(s);

        TreeNode treeNode = serializeDeserialize.deserialize(s);
        System.out.println(treeNode.val);
    }
}
