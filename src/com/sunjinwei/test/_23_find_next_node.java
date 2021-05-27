package com.sunjinwei.test;

import com.sunjinwei.domain.ParentTree;
import com.sunjinwei.domain.TreeNode;

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
            return findMostLeft(node.right);
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
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * 二叉搜索树寻找中序后继节点
     * 技巧解法：题解当中比较有技巧的思路，使用了bst的性质
     */
    public TreeNode findNextNodeByBst(TreeNode root, TreeNode p) {
        // 1如果存在右子树 那么找到右树中最左节点就是后继节点
        if (p.right != null) {
            return findMostLeft(p.right);
        }
        // 2如果不存在右子树 根据bst进行二分

        TreeNode res = null;
        while (root != p) {
            // 根据bst的性质搜索找到p
            if (root.val < p.val) {
                root = root.right;
            } else {
                res = root;
                root = root.left;
            }
        }
        return res;
    }

    private TreeNode findMostLeft(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * 二叉搜索树寻找中序后继节点
     * 常规递归法：直接使用中序遍历递归 找到第一个大于p节点值的节点即可 但是没有利用到bst的性质 导致遍历了很多元素
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // 中序遍历 左中右
        // 终止条件
        if (root == null) {
            return null;
        }
        // 左树递归
        TreeNode res = inorderSuccessor(root.left, p);
        // 逻辑处理:
        // 可能性1
        // 如果p在左树 那么后继有两种可能 1后继在左树 2后继是root
        if (res != null) {
            return res;
        }
        // 可能性2
        if (root.val > p.val) {
            return root;
        }
        // 右树递归
        return inorderSuccessor(root.right, p);
    }

    /**
     * 二叉搜索树中寻找后继节点
     * 使用了bst的递归解法：先判断p和root的值 确定是在右树 还是左树中找
     */
    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }
        // 根据bst的性质判断是不是在右树
        // 如果根节点小于或等于要查找的节点p, 直接进入右子树递归
        // 如果根节点大于要查找的节点, 则暂存左子树递归查找的结果,
        // 如果是null,说明在该根节点的左子树中没找到比p大的节点，也就说明该根节点就是要找的p的后继，则直接返回当前根节点
        // 如果不是null,说明找到了答案，返回左子树递归查找的结果

        // 情况1：如果root就是p 那么需要去root的右树上找后继
        if (root == p) {
            return inorderSuccessor2(root.right, p);
        }
        // 情况2：如果p大于root 也是需要在右树上找
        if (root.val < p.val) {
            return inorderSuccessor2(root.right, p);
        }
        // 情况3：p在左树上，那么有两种可能 后继在左树 后继是此时的root
        TreeNode res = inorderSuccessor2(root.left, p);
        if (res != null) {
            return res;
        }
        return root;
    }


}
