package com.sunjinwei._tree;

import com.sunjinwei.domain.TreeNode;

/**
 * 二叉搜索树后续遍历后的数组还原成二叉搜索树
 * <p>
 * 思路：
 * 1。从二叉搜索树的特点出发：左子树小于根节点 右子树大于根节点
 * 2。二叉树后序遍历的特点：左子树 右子树 根节点，根节点位于数组的最后一个元素
 */
public class _03__bst_arr_2_tree {

    public TreeNode posArr2Tree(int[] arr) {
        // 鲁棒性
        if (arr.length == 0) {
            return null;
        }
        return help_03(arr, 0, arr.length - 1);
    }

    /**
     * 方法1：通过遍历的方式找到分界点 并且分界点的初始值为-1【这是最原始的方式】
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private TreeNode help(int[] arr, int left, int right) {
        // 边界条件1：左索引超过右索引 返回空
        if (left > right) {
            return null;
        }
        // 找到根节点
        TreeNode root = new TreeNode(arr[right]);
        // 边界条件2：只有一个根节点
        if (left == right) {
            return root;
        }
        // 考虑：1正常的二叉树 2左斜树 3右斜树
        // 找到分界点 [left, right-1]
        // 如何找：[left, right-1]进行遍历 和根节点的值比较
        // 因为二叉搜索树的特点：左子树的值小于根节点 右子树的值大于根节点
        // 遍历 找到[i]的值第一次大于根节点的值
        int middle = -1;
        for (int i = left; i < right; i++) {
            if (arr[i] < arr[right]) {
                middle = i;
            }
        }
        // 开始进行判断：正常的二叉树 左斜树 右斜树
        if (middle == -1) {
            // 说明是右斜树
            root.right = help(arr, left, right - 1);
        } else if (middle == right - 1) {
            // 说明是左斜树
            root.left = help(arr, left, right - 1);
        } else {
            root.left = help(arr, left, middle - 1);
            root.right = help(arr, middle, right - 1);
        }
        return root;
    }

    /**
     * 方式2：对分界点的初始值进行优化 不需要对这棵树的极端情况进行考虑 少了很多逻辑判断
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private TreeNode help_02(int[] arr, int left, int right) {
        // 边界条件1：左索引超过右索引 返回空
        // 这个base case 特别重要，减少了斜树的各种判断 跟分界点初始值设置为left-1 起到了承上启下的作用
        if (left > right) {
            return null;
        }
        // 找到根节点
        TreeNode root = new TreeNode(arr[right]);
        // 边界条件2：只有一个根节点
        if (left == right) {
            return root;
        }
        // 只要把分界点初始值设置为： left-1
        int middle = left - 1;
        for (int i = left; i < right; i++) {
            if (arr[i] < arr[right]) {
                middle = i;
            }
        }
        // 不需要考虑：1正常的二叉树 2左斜树 3右斜树
        // 假设是左斜树 此时左子树全部小于根节点 此时middle不变 还是为left-1
        root.left = help_02(arr, left, middle - 1);
        root.right = help_02(arr, middle, right - 1);
        return root;
    }

    /**
     * 方式三：二分找分界点
     *
     * @param arr
     */
    private TreeNode help_03(int[] arr, int left, int right) {
        if (left > right) {
            return null;
        }
        TreeNode root = new TreeNode(arr[right]);
        if (left == right) {
            return root;
        }
        int middle = left - 1;
        int l = left;
        int r = right - 1;
        // 二分
        while (l <= r) {
            // 使用位运算寻找[l,r-1]的中点索引
            int m = l + ((r - l) >> 1);
            if (arr[m] < arr[r]) {
                middle = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        root.left = help_03(arr, left, middle);
        root.right = help_03(arr, middle + 1, right - 1);
        return root;
    }


    public static void main(String[] args) {
        _03__bst_arr_2_tree arr_2_tree = new _03__bst_arr_2_tree();
        // 左右子树都存在
        int[] arr = new int[]{3, 6, 5, 8, 11, 10, 7};
        // 只包含左子树
        int[] arrLeft = new int[]{3, 6, 5, 7};
        // 只包含右子树
        int[] arrRight = new int[]{8, 11, 10, 7};

        TreeNode treeNode = arr_2_tree.posArr2Tree(arrLeft);
        TreeNode right = treeNode.right;

        int a = 3;
        int b = 4;

        // 有溢出风险 因为是a+b
        System.out.println("===" + (a + b) / 2);

        // 保险的做法
        System.out.println(a + (b - a) / 2);

        //
        System.out.println((b + a) >> 1);

        // 求10的中点
        System.out.println(10 >> 1);
    }
}
