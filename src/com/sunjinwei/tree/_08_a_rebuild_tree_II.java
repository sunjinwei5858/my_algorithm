package com.sunjinwei.tree;

import com.sunjinwei.domain.TreeNode;

import java.util.HashMap;

/**
 * 根据后序和中序结果重建二叉树
 * 后序：左右根
 * 中序：左根右
 * 方法1：将哈希表和后序数组声明为全局变量，辅助函数传入四个指针
 * 方法2：辅助函数传入三个指针
 * <p>
 * 注意事项：和前序中序不同的是，区间问题 左子树的结束索引为【begin+num-1】
 */
public class _08_a_rebuild_tree_II {

    private HashMap<Integer, Integer> hashMap;

    private int[] postArr;

    /**
     * 方法1：声明全局变量，类比前序中序的思路
     * <p>
     * 执行用时：3 ms, 在所有 Java 提交中击败了65.41%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了29.62%的用户
     *
     * @param inOrder
     * @param postOrder
     * @return
     */
    public TreeNode buildTree(int[] inOrder, int[] postOrder) {
        // 使用hashmap存储中序的结果
        hashMap = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            hashMap.put(inOrder[i], i);
        }
        this.postArr = postOrder;
        return help(0, postOrder.length - 1, 0, inOrder.length - 1);
    }

    /**
     * 中序：左根右
     * 后序：左右根
     *
     * @param inBegin   中序结果的开始位置
     * @param inEnd     中序结果的结束位置
     * @param postBegin 后序开始位置
     * @param postEnd   后序结束位置
     * @return
     */
    private TreeNode help(
            int postBegin,
            int postEnd,
            int inBegin,
            int inEnd
    ) {
        // 终止条件
        if (postBegin > postEnd || inBegin > inEnd) {
            return null;
        }
        // 根据后序创建根节点
        TreeNode root = new TreeNode(postArr[postEnd]);
        // 根据中序找到根节点的索引
        Integer inRootIndex = hashMap.get(root.val);
        // 左子树的开始和结束
        root.left = help(postBegin, postBegin + inRootIndex - inBegin - 1, inBegin, inRootIndex - 1);
        // 右子树开始和结束
        root.right = help(postBegin + inRootIndex - inBegin, postEnd - 1, inRootIndex + 1, inEnd);
        return root;
    }


    /**
     * 方法2：
     * 执行用时：3 ms, 在所有 Java 提交中击败了65.41%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了41.08%的用户
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree_02(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> map = new HashMap();
        // 存储后序遍历结果
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        // 辅助函数
        return help_02(postorder, 0, postorder.length - 1, 0, map);
    }

    private TreeNode help_02(
            int[] postorder,
            int start,
            int end,
            int inBegin,
            HashMap<Integer, Integer> map
    ) {
        // 终止条件
        if (start > end) {
            return null;
        }
        // 处理根节点
        TreeNode root = new TreeNode(postorder[end]);
        // 中序的根节点位置
        int inRootIndex = map.get(root.val);
        // 左子树的节点个数
        // 处理左子树
        root.left = help_02(postorder, start, inRootIndex - inBegin - 1, inBegin, map);
        // 处理右子树
        root.right = help_02(postorder, start + inRootIndex - inBegin, end - 1, inRootIndex + 1, map);
        return root;
    }

    public static void main(String[] args) {
        // 中序遍历
        int[] inorder = new int[]{2, 1, 3};
        int[] postorder = new int[]{2, 3, 1};

        _08_a_rebuild_tree_II tree_ii = new _08_a_rebuild_tree_II();
        TreeNode treeNode = tree_ii.buildTree(inorder, postorder);
        System.out.println(treeNode);
    }

}
