package com.sunjinwei.test;

import com.sunjinwei.domain.TreeNode;

/**
 * @program: com.sunjinwei.test
 * @author: sun jinwei
 * @create: 2021-04-28 06:33
 * @description: 判断树a是否是树b的子结构
 **/
public class _13_isSubtree {

    /**
     * 递归处理：
     * 根节点开始
     *
     * @param a
     * @param b
     * @return
     */
    public boolean isSubTree(TreeNode a, TreeNode b) {

        if (a == null) {
            return true;
        }
        if (b == null) {
            return false;
        }
        // 三种情况
        // 1 节点的值相同 走help函数
        // 2 查看是否在左子树中
        // 3 查看是否在右子树中
        return help(a, b) || isSubTree(a, b.left) || isSubTree(a, b.right);

    }

    private boolean help(TreeNode a, TreeNode b) {
        if (a == null) {
            return true;
        }
        if (b == null) {
            return false;
        }
        if (a.val != b.val) {
            return false;
        }

        return help(a.left, b.left) && help(a.right, b.right);
    }
}