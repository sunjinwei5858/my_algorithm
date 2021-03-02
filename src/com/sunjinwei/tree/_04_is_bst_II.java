package com.sunjinwei.tree;


/**
 * 后序遍历的数组，判断是否是二叉搜索树的后序遍历的结果，前提：没有重复值
 */
public class _04_is_bst_II {

    public boolean isPost(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        return isBst(arr, 0, arr.length);
    }

    private boolean isBst(int[] arr, int i, int length) {




        return false;
    }

}
