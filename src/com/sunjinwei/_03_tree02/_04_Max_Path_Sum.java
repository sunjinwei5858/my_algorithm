package com.sunjinwei._03_tree02;

import com.sunjinwei.domain.TreeNode;

/**
 * @program: com.sunjinwei._03_tree02
 * @author: sun jinwei
 * @create: 2021-07-22 09:11
 * @description:
 * 变种 二叉树的最大路径和 力扣124 难度 困难，是力扣53 数组最大子序和的变种
 * 路径和 是路径中各节点值的总和。
 **/
public class _04_Max_Path_Sum {

    private int res = Integer.MIN_VALUE;

    /**
     * dfs
     *
     * 最重要的思想是「后序遍历」和动态规划的「无后效性」
     *
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {

        if (root == null) {
            return 0;
        }
        process(root);
        return res;
    }

    /**
     * dfs
     *
     * @param root
     * @return
     */
    private int process(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int left = process(root.left);
        int right = process(root.right);

        // 由于结点的值有可能为负数，因此如果子树得到的路径是负数，可以舍弃，表现为和 0 取最大值
        left = Math.max(left, 0);
        right = Math.max(right, 0);

        // 更新最大值
        // 比较当前最大路径和与左右子树最长路径加上根节点值的较大值，更新全局变量
        res = Math.max(res, left + right + root.val);
        // 递归只能返回：左子树+根节点或者是右子树+根节点
        return Math.max(left + root.val, right + root.val);


    }

    public static void main(String[] args) {
        _04_Max_Path_Sum maxPathSum = new _04_Max_Path_Sum();
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(-1);
        TreeNode right = new TreeNode(-1);
        root.left = left;
        root.right = right;

        TreeNode left1 = new TreeNode(1);
        TreeNode right1 = new TreeNode(3);
        left.left = left1;
        left.right = right1;

        int i = maxPathSum.maxPathSum(root);
        System.out.println(i);
    }
}
