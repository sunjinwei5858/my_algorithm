package com.sunjinwei._03_tree;


import com.sunjinwei.domain.TreeNode;

/**
 * 判断二叉树是否是平衡二叉树    【力扣110】
 * avl：1要么是一颗空树 2要么任何一个节点的左右子树的高度差绝对值不超过1【也就是<=1】
 * 思路：
 * 这次不使用左神的动态规划做，而是使用自顶向下和自底向上的方法解决
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
        // 处理根节点
        // 求左子树的高度
        int left = treeDepth(root.left);
        // 求右子树的高度
        int right = treeDepth(root.right);
        // 计算高度差
        if (Math.abs(left - right) > 1) {
            return false;
        }
        // 处理左子树
        boolean avl_left = isAvl_01(root.left);
        // 处理右子树
        boolean avl_right = isAvl_01(root.right);
        return avl_left && avl_right;
    }

    /**
     * 单纯的只是求树的深度
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
     * 求树深度的时候 就去判断是不是平衡树
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
        // 右子树
        int heightRight = height(root.right);
        // 先判断是不是-1
        if (heightLeft == -1 || heightRight == -1) {
            return -1;
        }
        // 然后再判断高度差 顺序这样 效率高些
        if (Math.abs(heightLeft - heightRight) > 1) {
            return -1;
        }

        return Math.max(heightLeft, heightRight) + 1;
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
