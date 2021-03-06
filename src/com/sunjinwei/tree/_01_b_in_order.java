package com.sunjinwei.tree;

import com.sunjinwei.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 中序遍历：左子树 根节点 右子树
 * 方法：递归和非递归
 */
public class _01_b_in_order {

    private List<Integer> result = new ArrayList<>();

    /**
     * 递归
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.6 MB, 在所有 Java 提交中击败了68.71%的用户
     */
    public List<Integer> orderMiddle_01(TreeNode root) {
        if (root == null) {
            return result;
        }
        // 因为已经提前做了判空 所以这里不需要判断root.left是否为null
        orderMiddle_01(root.left);
        result.add(root.val);
        orderMiddle_01(root.right);
        return result;
    }

    /**
     * 非递归：第一种写法 【对应着前序遍历的一种写法 只是打印的位置变了 第二次遇到就打印】
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了41.44%的用户
     * 内存消耗：36.5 MB, 在所有 Java 提交中击败了85.67%的用户
     */
    public List<Integer> orderMiddle_02(TreeNode root) {
        // 使用栈
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            // 1 一进来 就把二叉树的左边界压到栈里面去【整条左边界 依次入栈】
            if (root != null) {
                // 第一次碰到
                stack.push(root);
                root = root.left;
            } else {
                // 2 1的逻辑无法继续了 左边界处理完之后 然后处理右节点 【弹出节点 就打印】
                root = stack.pop();
                // 联想：中序遍历原则，第二次碰到就打印
                result.add(root.val);
                root = root.right;
            }
        }
        return result;
    }

    /**
     * 非递归：第二种写法 思想是一样的 先将左孩子进栈,弹出栈顶元素 再处理栈顶元素的右子树
     */
    public List<Integer> orderMiddle_03(TreeNode root) {
        // 使用栈
        Stack<TreeNode> stack = new Stack<>();
        // 思路：
        // 1先将左孩子进栈 2然后弹出 再有孩子进栈
        TreeNode curr = root;
        while (true) {
            // 1左孩子全部进栈
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            // 终止条件
            if (stack.isEmpty()) {
                break;
            }
            // 2栈顶元素 也就是左孩子出栈
            curr = stack.pop();
            result.add(curr.val);
            // 3栈顶元素的右孩子进栈
            curr = curr.right;
        }
        return result;
    }


}
