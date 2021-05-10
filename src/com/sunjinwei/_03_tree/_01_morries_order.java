package com.sunjinwei._03_tree;

import com.sunjinwei.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树遍历的神级方法 Morries遍历 O(1)内存
 * 核心：充分利用了叶子节点的空节点指针
 * 1如果当前节点有左孩子 那就是去左孩子转一圈 然后再去右孩子转一圈
 * 2充分利用叶子节点大量的null指针
 */
public class _01_morries_order {

    /**
     * Morries遍历
     *
     * @param head
     */
    public void morriesOrder(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode curr = head;
        TreeNode mostRight = null;
        while (curr != null) {
            // 将左孩子赋值给mostRight
            mostRight = curr.left;
            // 情况1：存在左孩子 那么在左孩子中转一圈
            if (mostRight != null) {
                // 先寻找左孩子的最右节点
                while (mostRight.right != null && mostRight.right != curr) {
                    mostRight = mostRight.right;
                }
                // 判断是不是第一次遇到
                if (mostRight.right == null) {
                    // 第一次遇到:修改叶子节点的right指针指向curr节点 第一步：调整
                    mostRight.right = curr;
                    // 然后curr向左移动
                    curr = curr.left;
                    // 回到最外层重新开始处理
                    continue;
                } else {
                    // 第二次遇到:将叶子节点的right指针还原为null 第二步：还原
                    mostRight.right = null;
                }
            }
            // 情况2：向右移动
            // 情况3：curr左子树最右节点是指向curr的 此时curr向右移动
            curr = curr.right;
        }
    }

    private List<Integer> result = new ArrayList<>();

    /**
     * Morries遍历改写中序遍历 确实空间复杂度很低
     * 分析：
     * 中序遍历是左子树 根节点 右子树
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了97.05%的用户
     */
    public List<Integer> morriesMiddleOrder(TreeNode head) {
        if (head == null) {
            return result;
        }
        TreeNode curr = head;
        TreeNode mostRight = null;
        while (curr != null) {
            // 将左孩子赋值给mostRight
            mostRight = curr.left;
            // 情况1：存在左孩子 那么在左孩子中转一圈
            if (mostRight != null) {
                // 先寻找左孩子的最右节点
                while (mostRight.right != null && mostRight.right != curr) {
                    mostRight = mostRight.right;
                }
                // 判断是不是第一次遇到
                if (mostRight.right == null) {
                    // 第一次遇到:修改叶子节点的right指针指向curr节点 第一步：调整
                    mostRight.right = curr;

                    // 然后curr向左移动
                    curr = curr.left;
                    // 回到最外层重新开始处理
                    continue;
                } else {

                    // 第二次遇到:将叶子节点的right指针还原为null 第二步：还原
                    mostRight.right = null;
                }
            }
            // 中序遍历 在这里进行添加元素
            result.add(curr.val);

            // 情况2：向右移动
            // 情况3：curr左子树最右节点是指向curr的 此时curr向右移动
            curr = curr.right;
        }
        return result;
    }


    /**
     * Morries遍历改写前序遍历 确实空间复杂度很低
     * 分析：
     * 前序遍历是根节点 左子树 右子树
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了97.05%的用户
     */
    public List<Integer> morriesPreOrder(TreeNode head) {
        if (head == null) {
            return result;
        }
        TreeNode curr = head;
        TreeNode mostRight = null;
        while (curr != null) {

            // 将左孩子赋值给mostRight
            mostRight = curr.left;
            // 情况1：存在左孩子 那么在左孩子中转一圈
            if (mostRight != null) {
                // 先寻找左孩子的最右节点
                while (mostRight.right != null && mostRight.right != curr) {
                    mostRight = mostRight.right;
                }
                // 判断是不是第一次遇到
                if (mostRight.right == null) {
                    // 第一次遇到:修改叶子节点的right指针指向curr节点 第一步：调整
                    mostRight.right = curr;

                    // 前序遍历 这里进行添加父节点!!!
                    result.add(curr.val);
                    // 然后curr向左移动
                    curr = curr.left;
                    // 回到最外层重新开始处理
                    continue;
                } else {
                    // 第二次遇到:将叶子节点的right指针还原为null 第二步：还原
                    mostRight.right = null;
                }
            } else {
                // 前序遍历 mostRight为空 这里进行添加叶子节点 左叶子节点 右叶子节点
                result.add(curr.val);
            }
            // 情况2：向右移动
            // 情况3：curr左子树最右节点是指向curr的 此时curr向右移动
            curr = curr.right;
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        TreeNode left1 = new TreeNode(2);
        TreeNode right1 = new TreeNode(3);
        head.left = left1;
        head.right = right1;

        TreeNode left11 = new TreeNode(4);
        TreeNode right11 = new TreeNode(5);
        left1.left = left11;
        left1.right = right11;

        TreeNode left111 = new TreeNode(6);
        TreeNode right111 = new TreeNode(7);
        right1.left = left111;
        right1.right = right111;

        _01_morries_order morriesOrder = new _01_morries_order();

        List<Integer> result = morriesOrder.morriesPreOrder(head);
        System.out.println(result.toString());

    }


}
