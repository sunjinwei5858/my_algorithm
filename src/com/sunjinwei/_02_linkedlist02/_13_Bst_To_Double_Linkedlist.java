package com.sunjinwei._02_linkedlist02;

import com.sunjinwei.domain.BSTReturnType;
import com.sunjinwei.domain.TreeNode;

import java.util.LinkedList;

/**
 * bst二叉查找树转双向链表 变种：bst二叉查找树转循环双向链表(剑指)
 * 二叉树的left和right指针就是双向链表的pre和next指针
 * 方法1：使用队列存储中序遍历的结果，o(n)空间
 * 方法2：使用递归，在中序遍历递归中进行处理，思路：声明双向链表的尾巴节点为全局变量
 * 方法3：树形dp
 */
public class _13_Bst_To_Double_Linkedlist {


    /**
     * 方法1：
     * 左神：只需要将bst转为双向链表即可，所以最后要防止循环链表【队列处理】
     *
     * @param root 二叉搜索树
     * @return 返回双向链表
     */
    public TreeNode bst2DoubleLinkedList(TreeNode root) {

        if (root == null) {
            return null;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        // 中序遍历: 左 中 右
        inOrder(queue, root);
        if (queue.isEmpty()) {
            return null;
        }
        root = queue.poll();
        TreeNode pre = root;
        pre.left = null;
        TreeNode curr = null;
        /**
         * 注意：while循环过程中：只需要处理pre的right和curr的left指向即可，curr的right不需要处理 因为队列可能为空 此时需要判空 也是重复处理了
         */
        while (!queue.isEmpty()) {
            curr = queue.poll();
            pre.right = curr;
            curr.left = pre;
            pre = curr;
        }
        // 防止循环链表 !!!!!
        pre.right = null;
        return root;
    }

    private void inOrder(LinkedList<TreeNode> queue, TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(queue, root.left);
        queue.offer(root);
        inOrder(queue, root.right);
    }

    /**
     * 方法1：
     * 剑指offer：将bts转为循环双向链表【队列处理】
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.7 MB, 在所有 Java 提交中击败了61.76%的用户
     *
     * @param root
     * @return
     */
    public TreeNode bst2DoubleLinkedList2(TreeNode root) {

        if (root == null) {
            return null;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        // 中序遍历: 左 中 右
        inOrder(queue, root);
        if (queue.isEmpty()) {
            return null;
        }
        root = queue.poll();
        TreeNode pre = root;
        TreeNode curr = null;
        while (!queue.isEmpty()) {
            curr = queue.poll();
            pre.right = curr;
            curr.left = pre;
            pre = curr;
        }
        // 处理：转为循环双向链表
        root.left = pre;
        pre.right = root;
        return root;
    }


    /**
     * 方法2：递归+尾巴节点全局变量
     * 全局变量：双向链表的尾巴节点
     */
    private TreeNode tail;

    /**
     * 使用递归进行处理：转为循环链表
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.6 MB, 在所有 Java 提交中击败了77.69%的用户
     *
     * @param root
     * @return
     */
    public TreeNode bst2DoubleLinkedList3(TreeNode root) {

        if (root == null) {
            return null;
        }
        // 中序遍历处理
        inOrder2(root);
        // 根据尾巴节点 一路left找到头节点
        TreeNode head = tail;
        while (head != null && head.left != null) {
            head = head.left;
        }
        // 转为循环链表
        head.left = tail;
        tail.right = head;
        return head;
    }

    private void inOrder2(TreeNode root) {

        if (root == null) {
            return;
        }
        inOrder2(root);
        root.left = tail;
        if (tail != null) {
            tail.right = root;
        }
        tail = root;
        inOrder2(root);
    }

    /**
     * 方法2优化：递归+两个全局变量【双向链表头节点和尾巴节点】
     * 优化：声明两个全局变量，双向链表的头节点和尾巴节点
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.8 MB, 在所有 Java 提交中击败了44.33%的用户
     */
    private TreeNode begin;
    private TreeNode end;

    public TreeNode bst2DoubleLinkedList4(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 中序遍历处理
        inOrder3(root);
        // 只需要将首尾进行相连
        begin.left = end;
        end.right = begin;
        return begin;

    }

    private void inOrder3(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder3(root.left);
        if (begin == null) {
            begin = root;
        }
        root.left = end;
        if (end != null) {
            end.right = root;
        }
        end = root;
        inOrder3(root.right);
    }

    /**
     * 方法3：树形dp
     * 声明结果类ReturnType，里面两个属性 双向链表的头节点 双向链表的尾巴节点
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.8 MB, 在所有 Java 提交中击败了56.46%的用户
     */
    public TreeNode bst2DoubleLinkedList5(TreeNode root) {

        if (root == null) {
            return null;
        }
        // 树形dp处理
        BSTReturnType returnType = process(root);
        // 转为双向
        returnType.begin.left = returnType.end;
        returnType.end.right = returnType.begin;
        return returnType.begin;
    }

    /**
     * 树形dp处理
     *
     * @param root
     * @return
     */
    private BSTReturnType process(TreeNode root) {
        if (root == null) {
            /**
             * 注意：这里进行返回 begin和end属性 可以默认为null
             */
            return new BSTReturnType(null, null);
        }
        // 处理以root.left左子树的返回的双向链表
        BSTReturnType leftReturn = process(root.left);
        // 处理以root.right右子树的返回的双向链表
        BSTReturnType rightReturn = process(root.right);

        // 进行处理双向链表的左右节点指向
        // 1
        if (leftReturn.end != null) {
            leftReturn.end.right = root;
        }
        // 2
        root.left = leftReturn.end;
        // 3
        root.right = rightReturn.begin;
        // 4
        if (rightReturn.begin != null) {
            rightReturn.begin.left = root;
        }
        return new BSTReturnType(
                leftReturn.begin != null ? leftReturn.begin : root,
                rightReturn.end != null ? rightReturn.end : root
        );
    }
}
