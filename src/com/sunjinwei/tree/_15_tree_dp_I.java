package com.sunjinwei.tree;

import com.sunjinwei.domain.MaxBstReturnType;
import com.sunjinwei.domain.TreeNode;

/**
 * 找到二叉树中的最大搜索二叉子树
 * 【左神：利用分析可能性在二叉树上做类似动态规划的问题，简称"树形dp套路"】
 * <p>
 * 树形dp套路使用前提：如果题目求解目标是S规则，则求解流程可以定成以每一个节点为头节点的子树在S规则下的每一个答案，并且最终答案一定在其中。
 * 以本题为例，题目求解目标是：整棵二叉树中的最大搜索二叉子树，这就是我们的规则。
 * 那么求解流程可不可以定成：在整棵二叉树中，求出每一个节点为头节点的子树的最大搜索二叉子树（对任何一棵子树都求出答案），
 * 并且最终答案（整棵二叉树的最大搜索二叉子树）一定在其中？当然可以。因此，本题可以使用套路。
 */
public class _15_tree_dp_I {

    /**
     * 分析第一步：
     * 以某个节点x为头节点的子树中，分析答案有哪些可能性，并且这种分析是以X的左子树、X的右子树和X整棵树的角度来考虑可能性的。用本题举例。
     * 第一种：X为头节点的子树中，最大的搜索二叉子树就是 X 的左子树中的最大搜索二叉子树。也就是说，答案可能来自左子树。
     * 第二种：X为头节点的子树中，最大的搜索二叉子树就是 X 的右子树中的最大搜索二叉子树。也就是说，答案可能来自右子树。
     * 第三种：如果X左子树上的最大搜索二叉子树是X左子树的全体，X右子树上的最大搜索二叉子树是X右子树的全体，
     * 并且X的值大于X左子树所有节点的最大值，但小于X右子树所有节点的最小值，那么X为头节点的子树中，
     * 最大的搜索二叉子树就是以X为头节点的全体。也就是说，答案可能是用X连起所有。
     */

    /**
     * 分析第二步：
     * 根据第一步的可能性分析，列出所有可能的信息。
     * 第一种和第二种所需的信息：左树：leftMaxBstHead leftBstSize 右树：rightBstHead rightBstSize
     * 结合第三种：因为需要比较值的大小 所以左树还需要leftMax  右树还需要rightMin
     */

    /**
     * 分析第三步：
     * 合并第二步的信息，写出信息结构，左树只需要最大值，右树只需要最小值
     * {
     *     TreeNode maxBstHead;
     *     int maxSize;
     *     int min;
     *     int max
     * }
     */

    /**
     * 分析第四步：
     * 设计递归函数，递归函数是处理以x为节点的情况下的答案，包括base case，默认得到左树和右树的所有信息，以及把可能性整合
     * 并且返回第三步的结构
     */

    /**
     * 方法：树形dp+后序遍历
     *
     * @param root
     * @return
     */
    private MaxBstReturnType process(TreeNode root) {
        // base case：空树
        if (root == null) {
            return new MaxBstReturnType(root, 0, Integer.MAX_VALUE, Integer.MIN_VALUE);
        }
        // 默认得到左树的信息
        MaxBstReturnType leftMaxBstReturnType = process(root.left);
        // 默认得到右树的信息
        MaxBstReturnType rightMaxBstReturnType = process(root.right);
        // 信息整合
        // 1如果只考虑第一种和第二种情况 那么最大的size
        int maxSize = Math.max(leftMaxBstReturnType.maxBstSize, rightMaxBstReturnType.maxBstSize);
        // 2如果只考虑第一种和第二种情况 那么最大bst的节点
        TreeNode maxBstNode = leftMaxBstReturnType.maxBstSize >= rightMaxBstReturnType.maxBstSize ? leftMaxBstReturnType.maxBstNode : rightMaxBstReturnType.maxBstNode;
        // 3同时对以x为头节点的子树也有同样要求 以x为头节点的子树的最小值：左树最小 右树最小 以及x最小
        int min = Math.min(root.val, Math.min(leftMaxBstReturnType.min, rightMaxBstReturnType.min));
        int max = Math.min(root.val, Math.min(leftMaxBstReturnType.max, rightMaxBstReturnType.max));
        // 利用收集的信息 判断是否存在第三种可能性
        // 1 左树最大bst的头节点刚好是root的左孩子 && 右树最大的bst的头节点刚好是root的右孩子
        // 2 root的值大于左树最大bst的max && root的值小于右树最大bst的最小值
        // 因此，需要从左子树上取得左子树的最大值leftMax，从右子树上取得右子树的最小值rightMin
        if (leftMaxBstReturnType.maxBstNode == root.left && rightMaxBstReturnType.maxBstNode == root.right
                && leftMaxBstReturnType.max < root.val && root.val < rightMaxBstReturnType.min
        ) {
            maxSize = leftMaxBstReturnType.maxBstSize + rightMaxBstReturnType.maxBstSize + 1;
            maxBstNode = root;
        }
        // 全部信息收集完毕
        return new MaxBstReturnType(maxBstNode, maxSize, max, min);
    }

    public TreeNode getMaxBst(TreeNode root) {
        if (root == null) {
            return null;
        }
        MaxBstReturnType maxBstReturnType = process(root);
        return maxBstReturnType.maxBstNode;
    }

}
