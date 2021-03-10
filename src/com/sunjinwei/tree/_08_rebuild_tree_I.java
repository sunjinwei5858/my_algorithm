package com.sunjinwei.tree;

import com.sunjinwei.domain.TreeNode;

import java.util.Arrays;

/**
 * 根据前序和中序结果重建二叉树
 */
public class _08_rebuild_tree_I {

    /**
     * 方法1：简单粗暴 每次递归都重新查找中序根节点的位置
     * 1根据前序找到头节点，
     * 2根据中序结果，头节点的左边是左子树，右边是右子树
     * <p>
     * 根据前、中序遍历的特点，（根左右、左根右），
     * 先根据前序遍历确定根节点，然后在中序遍历知道该根节点的左右树的数量，
     * 反推出前序遍历中左子树的结点有哪些。根据该思路进行递归即可完成二叉树的重建。
     *
     * @param preArr
     * @param inArr
     * @return
     */
    public TreeNode arr2Tree(int[] preArr, int[] inArr) {
        // 鲁棒性
        if (preArr == null || preArr.length == 0 || inArr == null || inArr.length == 0) {
            return null;
        }
        // 1根据前序遍历结果定位根节点
        int rootVal = preArr[0];
        // 2生成根节点
        TreeNode root = new TreeNode(rootVal);
        // 3在中序遍历结果中查找根节点
        for (int i = 0; i < inArr.length; i++) {
            if (inArr[i] == rootVal) {
                // 构建左子树
                // 将左子树的前中序遍历分割开
                root.left = arr2Tree(Arrays.copyOfRange(preArr, 1, i + 1), Arrays.copyOfRange(inArr, 0, i));
                // 构建右子树
                // 将右子树的前中序遍历分割开
                root.right = arr2Tree(Arrays.copyOfRange(preArr, i + 1, preArr.length), Arrays.copyOfRange(inArr, i + 1, inArr.length));
            }
        }
        return root;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;

        TreeNode left1 = new TreeNode(4);
        TreeNode right1 = new TreeNode(5);
        left.left = left1;
        right.right = right1;


        int[] preArr = new int[]{1, 2, 4, 5, 3};
        int[] inArr = new int[]{4, 2, 5, 1, 3};

        _08_rebuild_tree_I rebuildTreeI = new _08_rebuild_tree_I();
        TreeNode treeNode = rebuildTreeI.arr2Tree(preArr, inArr);
        System.out.println(treeNode);


    }


}
