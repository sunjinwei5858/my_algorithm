package com.sunjinwei.domain;

/**
 * 复杂链表：除了next指针 还含有随机rand指针
 */
public class ComplexListNode {

    public int val;

    public ComplexListNode next;

    public ComplexListNode rand;

    public ComplexListNode(int x) {
        val = x;
    }
}
