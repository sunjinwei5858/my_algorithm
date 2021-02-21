package com.sunjinwei.tree;

import com.sunjinwei.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 后序遍历：左子树 右子树 根节点
 * 方法：递归和非递归
 */
public class _01_order_III {

    private List<Integer> result = new ArrayList<>();

    /**
     * 递归
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37 MB, 在所有 Java 提交中击败了5.18%的用户
     */
    public List<Integer> orderAfter_01(TreeNode root) {
        if (root == null) {
            return result;
        }
        orderAfter_01(root.left);
        orderAfter_01(root.right);
        result.add(root.val);
        return result;
    }

    /**
     * 非递归：第一种写法【前序遍历就有三种写法 对应着后序遍历使用两个栈也有三种写法】
     * 可以类比：前序遍历的思想【根左右】换成 根右左的遍历  然后将这个根右左 放到另外一个栈中 也就实现了后序遍历 左右根 的遍历
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了52.82%的用户
     * 内存消耗：36.7 MB, 在所有 Java 提交中击败了44.40%的用户
     *
     * @see https://www.bilibili.com/video/BV1uo4y1d7V3?p=10 【P10章节 二叉树的基本算法】
     */
    public List<Integer> orderAfter_02(TreeNode root) {
        // 鲁棒性
        if (root == null) {
            return result;
        }
        // 使用两个栈
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            // 1stack1弹出了 不打印 使用stack2进行收集
            TreeNode pop = stack1.pop();
            stack2.push(pop);
            // 2将左节点先进栈 目标是在stack1中形成根右左
            if (pop.left != null) {
                stack1.push(pop.left);
            }
            // 3然后是右节点 目标是在stack1中形成根右左
            if (pop.right != null) {
                stack1.push(pop.right);
            }
        }
        while (!stack2.isEmpty()) {
            result.add(stack2.pop().val);
        }
        return result;
    }

    /**
     * 非递归：第二种写法 【前序遍历就有三种写法 对应着后序遍历使用两个栈也有三种写法】
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了52.82%的用户
     * 内存消耗：36.7 MB, 在所有 Java 提交中击败了48.05%的用户
     */
    public List<Integer> orderAfter_03(TreeNode root) {
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        TreeNode curr = root;
        // 将前序遍历变成根右左的方式
        while (curr != null || !stack1.isEmpty()) {
            if (curr != null) {
                // 不打印 使用栈2进行收集
                stack2.push(curr);
                stack1.push(curr);
                curr = curr.right;
            } else {
                curr = stack1.pop();
                curr = curr.left;
            }
        }
        while (!stack2.isEmpty()) {
            result.add(stack2.pop().val);
        }
        return result;
    }


}
