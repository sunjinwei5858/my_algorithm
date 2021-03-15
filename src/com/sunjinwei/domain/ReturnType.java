package com.sunjinwei.domain;


/**
 * 返回树的最大bst的信息结构
 */
public class ReturnType {

    /**
     * 最大bst的根节点
     */
    public TreeNode maxBstNode;

    /**
     * 最大bst的大小
     */
    public int maxBstSize;

    /**
     * 如果根节点就是最大bst max代表根节点左树的最大值 max
     */
    public int max;

    /**
     * 如果根节点就是最大bst min代表根节点右树的最小值 min
     */
    public int min;

    public ReturnType(TreeNode maxBstNode, int maxBstSize, int max, int min) {
        this.maxBstNode = maxBstNode;
        this.maxBstSize = maxBstSize;
        this.max = max;
        this.min = min;
    }
}
