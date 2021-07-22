package com.sunjinwei._03_tree02;

import com.sunjinwei.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 主要写前序 中序 后序的非递归版本
 * 前序：栈实现
 * 中序：栈实现
 * 后序：一个栈实现；两个栈实现
 */
public class _01_Traverse {

    /**
     * 前序遍历：根左右
     *
     * @param root
     * @return
     */
    public List<Integer> preOrder(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        // 先将头节点push
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            // 第一次遇见 加入
            res.add(pop.val);
            // 因为栈是先进后出 所以此时让右孩子先进栈 放在栈底
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
        return res;
    }


    /**
     * 中序遍历：左根右
     *
     * @param root
     * @return
     */
    public List<Integer> inOrder(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        // 1 中序遍历：需要将所有左节点进栈 直到左孩子为空 此时从栈顶弹出 最左节点
        // 2 然后再去找最左节点的头节点
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                // 进栈
                stack.push(curr);
                // 一路找左孩子
                curr = curr.left;
            } else {
                // 走到这里 说明curr为null 左孩子都已经进栈 此时弹出
                TreeNode pop = stack.pop();
                // 第二次遇见 此时添加到结果集
                res.add(pop.val);
                // 弹出节点的右孩子赋给curr
                curr = pop.right;
            }
        }
        return res;
    }

    /**
     * 后序遍历：两个栈实现
     * 思路：类比前序遍历 根左右 后序是左右根，将前序 根左右换成根右左,
     * 第一个栈弹出[根右左] 第二个栈进行接收[根右左] 那么第二个栈弹出就能得到后序[左右根]
     *
     * @param root
     * @return
     */
    public List<Integer> postOrder(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> preStack = new Stack<>();
        Stack<TreeNode> postStack = new Stack<>();
        preStack.push(root);
        while (!preStack.isEmpty()) {
            TreeNode pop = preStack.pop();
            postStack.push(pop);
            // 跟前序遍历反过来 根右左
            if (pop.left != null) {
                preStack.push(pop.left);
            }
            if (pop.right != null) {
                preStack.push(pop.right);
            }
        }

        while (!postStack.isEmpty()) {
            res.add(postStack.pop().val);
        }

        return res;
    }

    /**
     * 后序遍历：一个栈实现
     * 思路：
     * 声明变量pre 表示前一个处理的节点
     * 声明变量curr 表示当前遍历的节点
     * <p>
     * 核心准则：
     * 判断pre是不是curr的左孩子或者右孩子 如果是左孩子 说明左孩子已经处理过了 如果是右孩子 说明右孩子已经处理过了
     *
     * @param root
     * @return
     */
    public List<Integer> postOrder2(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode curr = null;
        // 这里不能为pre默认不能null 因为如果root是一颗斜树 即退化成链表 那么就会有问题 会少结果
        TreeNode pre = root;
        while (!stack.isEmpty()) {
            // 将栈顶元素设置为当前元素
            curr = stack.peek();
            if (curr.left != null && curr.left != pre && curr.right != pre) {
                // 先判断curr的左孩子有没有处理 如果没有处理 那么进栈
                stack.push(curr.left);
            } else if (curr.right != null && curr.right != pre) {
                // 然后判断curr的右孩子有没有处理 如果没有处理 那么进栈
                stack.push(curr.right);
            } else {
                // 说明curr的左孩子和右孩子都已经处理过了 弹出
                TreeNode pop = stack.pop();
                res.add(pop.val);
                // 更新pre
                pre = pop;
            }

        }
        return res;
    }

    public static void main(String[] args) {
        _01_Traverse treeTraverse = new _01_Traverse();

        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);

        a.left = b;
        a.right = c;

        List<Integer> treeNodes = treeTraverse.postOrder2(a);

        for (Integer integer : treeNodes) {
            System.out.println(integer);
        }


    }


}
