package com.sunjinwei.test;

import com.sunjinwei.domain.ParentTree;

import java.util.Stack;

/**
 * 第一个题目：二叉树包含parent节点，中序找后继节点，给出：当前节点
 * 变种：二叉树搜索树 寻找后继节点，给出：根节点+当前节点
 */
public class _23_find_next_node {

    /**
     * 第一个题目
     * 方法1：暴力解法，找到根节点 然后中序遍历，最后找到后继节点
     *
     * @param node
     * @return
     */
    public ParentTree findNextNode(ParentTree node) {
        // 鲁棒性
        if (node == null) {
            return node;
        }
        // 寻找根节点
        ParentTree root = node;
        while (root.parent != null) {
            root = root.parent;
        }
        // 找到根节点 进行中序遍历 左中右
        // 非递归
        Stack<ParentTree> stack = new Stack<>();
        boolean flag = false;
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                // 因为栈是先进后出 这里先将根节点压栈 然后再将左子树一直
                stack.push(root);
                root = root.left;
            } else {
                // 如果此时root为空 那么进行弹栈 也就是将最左节点开始出栈
                root = stack.pop();
                // 进行处理: 判断是不是遇到了该节点 如果是修改为true 那么下一个节点如果是true的话 此时的节点就是后继节点
                if (flag) {
                    return root;
                }
                if (root == node) {
                    flag = true;
                }
                // 找到该节点的右节点
                root = root.right;
            }
        }
        return null;
    }


    /**
     * 第一个题目：
     * 最优写法：不需要遍历所有节点
     * 1。如果当前节点有右孩子 那么后继节点就是右孩子中最左节点
     * 2。如果当前节点没有右孩子 那么后继节点就是一直往上找 如果该节点是它父节点的左孩子 那么这个父节点就是后继节点【画图 左叶子节点和右叶子节点】
     *
     * @param node
     * @return
     */
    public ParentTree findNextNode2(ParentTree node) {
        // 鲁棒性
        if (node == null) {
            return null;
        }
        // 判断是否有右孩子
        if (node.right != null) {
            // 存在右孩子 那么找右孩子的最左节点
            return findMostLeft(node);
        }
        // 不存在右孩子 一直往上找
        ParentTree parent = node.parent;
        // 判断条件1：node如果是parent的左孩子 那么退出循环
        // 判断条件2：parent为null 也就是此时到了root节点
        while (parent != null && parent.left != node) {
            node = parent;
            parent = node.parent;
        }
        return parent;
    }

    private ParentTree findMostLeft(ParentTree node) {
        while (node != null) {
            node = node.left;
        }
        return null;
    }


}
