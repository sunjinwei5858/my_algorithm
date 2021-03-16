package com.sunjinwei._03_tree;


import com.sunjinwei.domain.AvlReturnType;
import com.sunjinwei.domain.TreeNode;

/**
 * 判断二叉树是否是平衡二叉树 avl 【左神 力扣110】
 * avl：1要么是一颗空树 2要么任何一个节点的左右子树的高度差绝对值不超过1【也就是<=1】
 * 思路：同样也是使用树形dp
 * 为什么可以使用？
 * 满足dp的前提，依次考察每个节点为头节点的子树，如果都是avl，那么整体就是avl
 */
public class _15_tree_dp_III {

    /**
     * 分析第一步：
     * 第一种：左子树不平衡
     * 第二种：右子树不平衡
     * 第三种：左子树高度和右子树高度绝对值大于1 那么以X为节点的二叉树也不平衡
     */

    /**
     * 分析第二步：
     * 列出所需要的信息
     * 第一种和第二种：是否平衡
     * 第三种：左子树高度 右子树高度
     * ==》【是否平衡，高度】
     */

    /**
     * 分析第三步：
     * {
     *     boolean balancedIs;
     *     int height
     * }
     */

    /**
     * 分析第四步：
     * 设计递归函数，递归函数是处理以x为节点的情况下的答案，包括base case，默认得到左树和右树的所有信息，以及把可能性整合
     * 并且返回第三步的结构
     */

    private AvlReturnType process(TreeNode root) {
        // base case
        if (root == null) {
            return new AvlReturnType(true, 0);
        }
        // 默认处理好了左树和右树
        AvlReturnType leftReturnType = process(root.left);
        AvlReturnType rightReturnType = process(root.right);

        // 第三种情况 高度
        //int height = (leftReturnType.height > rightReturnType.height ? leftReturnType.height : rightReturnType.height) + 1;
        // 重构一下：也就是左树和右树高度 哪个最高 取哪个
        int height = Math.max(leftReturnType.height, rightReturnType.height) + 1;
        // 第三种情况 是否是平衡树
        boolean balancedIs = false;
        // 1判断左树和右树高度差绝对值是否小于等于1【<=1】
        //boolean height_is = Math.abs(leftReturnType.height - rightReturnType.height) <= 1 ? true : false;
        // 重构一下：将小于等于1 改写成 小于2
        boolean height_is = Math.abs(leftReturnType.height - rightReturnType.height) < 2 ? true : false;
        // 2判断
        if (leftReturnType.balancedIs && rightReturnType.balancedIs && height_is) {
            balancedIs = true;
        }
        return new AvlReturnType(balancedIs, height);
    }

    /**
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.96%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了15.09%的用户
     *
     * @param root
     * @return
     */
    public boolean isAvl(TreeNode root) {
        AvlReturnType avlReturnType = process(root);
        return avlReturnType.balancedIs;
    }

    public static void main(String[] args) {
        _15_tree_dp_III tree_dp_iii = new _15_tree_dp_III();
        TreeNode root = new TreeNode(2);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;

        boolean avl = tree_dp_iii.isAvl(root);
        System.out.println(avl);

    }


}
