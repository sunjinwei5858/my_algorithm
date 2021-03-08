package com.sunjinwei.tree;


/**
 * 根据一棵树后序遍历结果，判断这棵树是否是二叉搜索树，前提：没有重复值
 * 进阶问题：确定这是二叉搜索树后序遍历的结果，通过数组重构二叉树
 */
public class _04_b_is_bst_by_arr {

    public boolean isPost(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        return isBst(arr, 0, arr.length);
    }

    /**
     * @param arr
     * @param i
     * @param length
     * @return
     */
    private boolean isBst(int[] arr, int i, int length) {

        return false;
    }

}
