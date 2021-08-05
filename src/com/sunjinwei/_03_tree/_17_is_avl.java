package com.sunjinwei._03_tree;


import com.sunjinwei.domain.AvlReturnType;
import com.sunjinwei.domain.TreeNode;

/**
 * 判断二叉树是否是平衡二叉树    【力扣110】
 * avl：1要么是一颗空树 2要么任何一个节点的左右子树的高度差绝对值不超过1【也就是<=1】
 * 方法1：自顶向下
 * 方法2：自底向上
 * 方法3：树形dp
 */
public class _17_is_avl {

    /**
     * 方法1：使用自顶向下的方式 递归 时间复杂度O(n*n)
     * 这种方法时间复杂度高 不高效的方法 因为每次都要重新计算高度 其实高度已经计算过了
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.96%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了45.28%的用户
     *
     * @param root
     */
    public boolean isAvl_01(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 左子树的高度
        int left = treeDepth(root.left);
        // 右子树的高度
        int right = treeDepth(root.right);
        // 计算高度差
        if (Math.abs(left - right) > 1) {
            return false;
        }
        // 左子树：存在大量的重复计算
        boolean avl_left = isAvl_01(root.left);
        // 右子树：存在大量的重复计算
        boolean avl_right = isAvl_01(root.right);
        return avl_left && avl_right;
    }

    /**
     * 求树的高度：后序遍历
     *
     * @param root
     * @return
     */
    private int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = treeDepth(root.left);
        int right = treeDepth(root.right);
        return Math.max(left, right) + 1;
    }

    /**
     * 方法2：自底向上的方法 时间复杂度O(n) 保证不会重复计算高度
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.96%的用户
     * 内存消耗：38.5 MB, 在所有 Java 提交中击败了76.87%的用户
     *
     * @param root
     * @return
     */
    public boolean isAvl_02(TreeNode root) {
        if (root == null) {
            return true;
        }
        return height(root) != -1;
    }

    /**
     * 求树的高度：后序遍历 就加入平衡树的判断
     *
     * @param root
     * @return
     */
    private int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 左子树
        int heightLeft = height(root.left);
        if (heightLeft == -1) {
            return -1;
        }
        // 右子树
        int heightRight = height(root.right);
        if (heightRight == -1) {
            return -1;
        }
        // 然后再判断高度差 顺序这样 效率高些
        if (Math.abs(heightLeft - heightRight) > 1) {
            return -1;
        }
        // 根节点
        return Math.max(heightLeft, heightRight) + 1;
    }

    /**
     * 方法3：树形dp
     * 判断是否是平衡二叉树，需要高度，是否是
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了90.87%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了10.38%的用户
     *
     * @param root
     */
    public boolean isAvl_03(TreeNode root) {
        return process(root).balancedIs;
    }

    /**
     * 后序遍历处理树形dp
     *
     * @param root
     * @return
     */
    private AvlReturnType process(TreeNode root) {
        // base case: 为空 返回true
        if (root == null) {
            return new AvlReturnType(true, 0);
        }
        AvlReturnType leftReturn = process(root.left);
        AvlReturnType rightReturn = process(root.right);
        int height = Math.max(leftReturn.height, rightReturn.height) + 1;
        boolean flag = Math.abs(leftReturn.height - rightReturn.height) <= 1;
        if (flag && leftReturn.balancedIs && rightReturn.balancedIs) {
            return new AvlReturnType(true, height);
        }
        return new AvlReturnType(false, height);
    }


    public static void main(String[] args) {
        _17_is_avl tree_dp_iii = new _17_is_avl();
        TreeNode root = new TreeNode(2);
        TreeNode left = new TreeNode(1);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;

        boolean avl = tree_dp_iii.isAvl_02(root);
        System.out.println(avl);

    }


}
