package com.sunjinwei.tree;


import com.sunjinwei.domain.TreeNode;

/**
 * 剑指offer36：bst与循环双向链表，属于上一题目的变种
 * <p>
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的【循环双向】链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 * 思路：
 * 1。这道题属于上一题的变种，不仅是双向，还是循环，也就是首尾相连
 */
public class _11_bst_to_double_list_II {


    /**
     * 方法1：剑指offer
     * 思路：分成三部分：左子树，根节点，右子树
     * 将 二叉搜索树 转换成一个 “排序的【循环】【双向】链表” ，其中包含三个要素：
     * <p>
     * 排序链表： 节点应从小到大排序，因此应使用 中序遍历 “从小到大”访问树的节点。
     * 双向链表： 在构建相邻节点的引用关系时，设前驱节点 pre 和当前节点 cur ，不仅应构建 pre.right = cur ，也应构建 cur.left = pre 。
     * 循环链表： 设链表头节点 head 和尾节点 tail ，则应构建 head.left = tail 和 tail.right = head 。
     *
     * 执行用时：
     * 0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：
     * 37.9 MB, 在所有 Java 提交中击败了53.52%的用户
     * @param root
     * @return
     */

    /**
     * 1.双向链表的尾巴节点
     */
    private TreeNode lastNode;

    /**
     * 2.双向链表的首节点
     */
    private TreeNode headNode;

    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 将bst转为双向链表
        convertTreeNode(root);
        // 将双向链表转为循环链表
        headNode.left = lastNode;
        lastNode.right = headNode;
        return headNode;
    }

    /**
     * 思路：
     * 1。不仅仅需要记录尾巴节点 还需要记录头节点
     * 2. 注意：也可以不记录头节点 跟上一题一样 如果是这样只能得到尾巴节点，需要一直left又遍历一次双向链表 找到头节点，再进行首尾相连，转为循环链表，时间复杂度高了
     *
     * @param curr 当前节点
     */
    private void convertTreeNode(TreeNode curr) {
        // 鲁棒性
        if (curr == null) {
            return;
        }
        // 左子树
        if (curr.left != null) {
            convertTreeNode(curr.left);
        }
        // 根节点 逻辑
        // 处理left指针
        curr.left = lastNode;
        if (lastNode != null) {
            // 如果有尾巴节点 需要处理尾巴节点和当前节点的关系 right指针
            lastNode.right = curr;
        } else {
            // 如果lastNode为空 说明是头节点
            headNode = curr;
        }
        lastNode = curr;
        // 右子树
        if (curr.right != null) {
            convertTreeNode(curr.right);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;

        _11_bst_to_double_list_II bst_to_double_list = new _11_bst_to_double_list_II();
        TreeNode treeNode = bst_to_double_list.treeToDoublyList(root);
        System.out.println(treeNode.val);

    }

}
