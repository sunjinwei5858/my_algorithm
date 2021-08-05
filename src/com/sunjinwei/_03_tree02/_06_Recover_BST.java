package com.sunjinwei._03_tree02;

import com.sunjinwei.domain.TreeNode;

import java.util.Stack;

/**
 * 找出bst中错误的两个节点 【力扣99+左神】
 * 题目1: 单纯找出 并返回数组
 * 题目2: 在原来结构上进行交换 复原, 进阶：o(1)内存解决
 */
public class _06_Recover_BST {

    /**
     * 题目1：找出bst中错误的两个节点 【显示声明数组】
     * <p>
     * 中序遍历解决，错误节点有两种可能：1相连 2不相连
     * 所以更新的时候 要一直更新第二个error
     *
     * @param root
     * @return
     */
    public TreeNode[] recoverTree(TreeNode root) {

        TreeNode[] arr = new TreeNode[2];
        // 使用栈的方式中序遍历
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (pre != null) {
                    // 开始进行比较
                    if (pre.val > root.val) {
                        if (arr[0] == null) {
                            arr[0] = pre;
                        }
                        arr[1] = root;
                    }
                }
                // 更新pre
                pre = root;
                root = root.right;
            }
        }
        return arr;
    }

    /**
     * 题目2：复原 【显示声明数组，空间复杂度为o(n)】
     * <p>
     * 执行用时：4 ms, 在所有 Java 提交中击败了24.01%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了48.05%的用户
     *
     * @param root
     */
    public void recoverTree2(TreeNode root) {

        TreeNode curr = root;
        // 显示声明错误节点的数组 空间复杂度为o(n)
        TreeNode[] arr = new TreeNode[2];
        // 使用栈的方式中序遍历
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                if (pre != null) {
                    // 开始进行比较
                    if (pre.val > curr.val) {
                        if (arr[0] == null) {
                            arr[0] = pre;
                        }
                        // 不断更新第二个错误节点：arr[1]
                        arr[1] = curr;
                    }
                }
                // 更新pre
                pre = curr;
                curr = curr.right;
            }
        }
        // 获取这两个错误节点 进行交换val
        int temp = arr[0].val;
        arr[0].val = arr[1].val;
        arr[1].val = temp;
    }

    /**
     * 题目2：复原 【不声明数组 空间复杂度为o(h) h为栈的深度】
     * <p>
     * 执行用时：3 ms, 在所有 Java 提交中击败了58.14%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了51.48%的用户
     *
     * @param root
     */
    public void recoverTree3(TreeNode root) {

        TreeNode curr = root;
        // 声明第一个错误节点
        TreeNode first = null;
        // 声明第二个错误节点
        TreeNode second = null;
        // 声明上一个节点
        TreeNode pre = null;
        // 使用栈的方式中序遍历
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                if (pre != null) {
                    // 开始进行比较
                    if (pre.val > curr.val) {
                        second = curr;
                        // 保证first只更新一次即可
                        if (first == null) {
                            first = pre;
                        } else {
                            // opt: 这里进行了优化 提前break
                            break;
                        }
                    }
                }
                // 更新pre
                pre = curr;
                curr = curr.right;
            }
        }
        // 交换first和second
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    /**
     * 进阶：提前break
     *
     * @param root
     */
    public void recoverTree4(TreeNode root) {

        TreeNode curr = root;
        // 声明第一个错误节点
        TreeNode first = null;
        // 声明第二个错误节点
        TreeNode second = null;
        // 声明上一个节点
        TreeNode pre = null;
        // 使用栈的方式中序遍历
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                if (pre != null) {
                    // 开始进行比较
                    if (pre.val > curr.val) {
                        second = curr;
                        // 保证first只更新一次即可
                        if (first == null) {
                            first = pre;
                        } else {
                            // opt: 这里进行了优化 提前break
                            break;
                        }
                    }
                }
                // 更新pre
                pre = curr;
                curr = curr.right;
            }
        }
        // 交换first和second
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }


    public static void main(String[] args) {

        _06_Recover_BST findErrorNodeBst = new _06_Recover_BST();
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(3);
        TreeNode c = new TreeNode(2);
        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(5);
        b.left = a;
        c.left = b;
        c.right = d;
        d.right = e;
        TreeNode[] errorNodeInBst = findErrorNodeBst.recoverTree(c);
        System.out.println(errorNodeInBst[0].val);
        System.out.println(errorNodeInBst[1].val);

        findErrorNodeBst.recoverTree3(c);

        System.out.println(c);


    }

}
