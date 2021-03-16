package com.sunjinwei._03_tree;

import com.sunjinwei.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：【力扣114】
 * <p>
 * 展开后的单链表应该同样使用TreeNode ，其中right子指针指向链表中下一个结点，而左子指针始终为null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 * <p>
 * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
 */
public class _11_tree_to_list_IV {

    private List<Integer> list;

    /**
     * 方法1：前序遍历【申请额外空间 使用list容器存储前序遍历结果】时间和空间复杂度都为O(n)
     * <p>
     * 由于将节点展开之后会破坏二叉树的结构而丢失子节点的信息，因此前序遍历和展开为单链表分成了两步。
     * 能不能在不丢失子节点的信息的情况下，将前序遍历和展开为单链表同时进行？
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了40.42%的用户
     * 内存消耗：37.9 MB, 在所有 Java 提交中击败了54.10%的用户
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        this.list = new ArrayList<>();
        help(root);
        // 链表的第一个直接声明为root节点 并处理左指针为null
        TreeNode pre = root;
        pre.left = null;
        TreeNode curr = null;
        for (int i = 1; i < list.size(); i++) {
            Integer integer = list.get(i);
            curr = new TreeNode(integer);
            pre.right = curr;
            pre = curr;
        }
    }

    /**
     * 前序遍历方法
     *
     * @param root
     */
    private void help(TreeNode root) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        help(root.left);
        help(root.right);
    }

    /**
     * 方法2：前序遍历+展开同步 迭代法前序遍历申请额外空间 时间和空间复杂度都为O(n)
     * <p>
     * 之所以会在破坏二叉树的结构之后丢失子节点的信息，是因为在对左子树进行遍历时，没有存储右子节点的信息，在遍历完左子树之后才获得右子节点的信息。
     * 只要对前序遍历进行修改，在遍历左子树之前就获得左右子节点的信息，并存入栈内，子节点的信息就不会丢失，就可以将前序遍历和展开为单链表同时进行。
     * <p>
     * 该方法不适用于递归处理前序遍历  而是使用迭代的方法进行前序遍历 然后声明一个pre变量存储上一个节点
     * <p>
     * 展开为单链表的做法是，维护上一个访问的节点 prev，每次访问一个节点时，
     * 令当前访问的节点为 curr，将 prev 的左子节点设为 null 以及将 prev 的右子节点设为 curr，然后将 curr 赋值给 prev，
     * 进入下一个节点的访问，直到遍历结束。
     * 需要注意的是，初始时 prev 为 null，只有在 prev 不为 null 时才能对 prev 的左右子节点进行更新。
     * <p>
     * 执行用时：2 ms, 在所有 Java 提交中击败了9.63%的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了26.67%的用户
     *
     * @param root
     */
    public void flatten_02(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode pre = null;
        TreeNode curr = null;
        while (!stack.isEmpty()) {
            curr = stack.pop();
            // 前序遍历的逻辑处理
            if (pre == null) {
                pre = curr;
            } else {
                pre.left = null;
                pre.right = curr;
            }
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
            pre = curr;
        }

    }


    public static void main(String[] args) {
        _11_tree_to_list_IV tree_to_list_iv = new _11_tree_to_list_IV();
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        tree_to_list_iv.flatten_02(root);
        System.out.println(root);

    }
}
