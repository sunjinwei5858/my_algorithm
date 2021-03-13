package com.sunjinwei.tree;


import com.sunjinwei.domain.TreeNode;

/**
 * 剑指offer面试题27：bst与双向链表
 * <p>
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 * <p>
 * 思路：
 * 1.转化为如何将一个节点curr加入到双向链表中，只需要处理当前节点curr和双向链表的最后一个节点last的关系即可
 * 2.bst的中序遍历可以进行排序，从小到大，那么就可以在中序遍历递归中完成关系的处理
 */
public class _11_bst_to_double_list_I {


    /**
     * 方法1：剑指offer
     * 思路：分成三部分：左子树，根节点，右子树
     * 将 二叉搜索树 转换成一个 “排序的双向链表” ，其中包含两个要素：
     * <p>
     * 排序链表： 节点应从小到大排序，因此应使用 中序遍历 “从小到大”访问树的节点。
     * 双向链表： 在构建相邻节点的引用关系时，设前驱节点 pre 和当前节点 cur ，不仅应构建 pre.right = cur ，也应构建 cur.left = pre 。
     *
     * @param root
     * @return
     */

    /**
     * 声明全局的变量lastNodeMiddleOrder，为双向链表的尾巴节点
     */
    private TreeNode lastNode;

    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 将bst转为双向链表
        convertTreeNode(root);
        // 根据尾节点 一路left 找到头节点
        TreeNode head = lastNode;
        while (head != null && head.left != null) {
            head = head.left;
        }
        return head;
    }

    /**
     * 该方法的思路：利用中序遍历，节点的值从小到大，因此双向链表也是从小到大
     * 所以参数有两个：当前节点curr 双向链表的最后一个节点last
     * 处理：
     * 1：中序遍历：处理左子树
     * 2：curr的left指针指向last，last的right指向curr
     * 3：此时curr就是双向链表的最后一个节点，设置curr为last节点
     * 4：中序遍历：处理右子树
     *
     * @param curr 当前节点
     */
    private void convertTreeNode(TreeNode curr) {
        if (curr == null) {
            return;
        }
        // 处理左子树
        convertTreeNode(curr.left);
        // 处理根节点
        // 1.当前节点的左指针
        curr.left = lastNode;
        // 2.尾巴节点的右指针
        if (lastNode != null) {
            lastNode.right = curr;
        }
        // 3走到这里 尾巴节点设置为curr
        lastNode = curr;
        // 处理右子树
        convertTreeNode(curr.right);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;

        _11_bst_to_double_list_I bst_to_double_list = new _11_bst_to_double_list_I();
        TreeNode treeNode = bst_to_double_list.treeToDoublyList(root);
        System.out.println(treeNode.val);

    }

}
