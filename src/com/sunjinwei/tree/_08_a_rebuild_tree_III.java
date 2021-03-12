package com.sunjinwei.tree;

import com.sunjinwei.domain.TreeNode;

import java.util.HashMap;

/**
 * 根据前序和后序结果重建二叉树 【力扣889】
 */
public class _08_a_rebuild_tree_III {

    /**
     * 方法1：
     * 思路：
     * 1。前序第一个元素和后序最后一个元素都是根节点
     * 2。前序第二个就是左子树的第一个节点也就是根，是后序左子树的最后一个节点
     * <p>
     * 执行用时：2 ms, 在所有 Java 提交中击败了43.84%的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了7.44%的用户
     *
     * @param pre
     * @param post
     * @return
     */
    private int[] pre;
    private HashMap<Integer, Integer> hashMap;

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        // 鲁棒性
        if (pre == null || post == null || pre.length == 0 || post.length == 0) {
            return null;
        }
        this.hashMap = new HashMap<>();
        // 将后序结果存到哈希表中
        for (int i = 0; i < post.length; i++) {
            hashMap.put(post[i], i);
        }
        this.pre = pre;
        return help(0, pre.length - 1, 0, post.length - 1);
    }

    private TreeNode help(int preBegin, int preEnd, int postBegin, int postEnd) {
        // 终止条件1
        if (preBegin > preEnd || postBegin > postEnd) {
            return null;
        }
        // 前序遍历第一个就是根节点
        TreeNode root = new TreeNode(pre[preBegin]);
        // 终止条件2
        if (preBegin == preEnd) {
            return root;
        }
        // 对应着后序遍历 左子树的根节点的索引
        Integer leftIndex = hashMap.get(pre[preBegin + 1]);
        // 通过画图的方式得出 左子树的开始索引和结束索引 从而得出 右子树的开始索引和结束索引
        root.left = help(preBegin + 1, preBegin + leftIndex - postBegin + 1, postBegin, leftIndex);
        root.right = help(preBegin + leftIndex - postBegin + 2, preEnd, leftIndex + 1, postEnd - 1);
        return root;
    }

    public static void main(String[] args) {
        int[] pre = new int[]{1, 2, 4, 5, 3};
        int[] post = new int[]{4, 5, 2, 3, 1};
        _08_a_rebuild_tree_III a_rebuild_tree_iii = new _08_a_rebuild_tree_III();
        TreeNode treeNode = a_rebuild_tree_iii.constructFromPrePost(pre, post);
        System.out.println(treeNode.val);
    }

}
