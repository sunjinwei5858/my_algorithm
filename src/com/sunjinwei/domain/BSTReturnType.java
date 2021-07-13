package com.sunjinwei.domain;

/**
 * 将bst转双向链表
 */
public class BSTReturnType {

    /**
     * 双向链表的头节点
     */
    public TreeNode begin;

    /**
     * 双向链表的尾巴节点
     */
    public TreeNode end;

    public BSTReturnType(TreeNode begin, TreeNode end) {
        this.begin = begin;
        this.end = end;
    }
}
