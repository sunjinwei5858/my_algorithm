package com.sunjinwei.test;

/**
 * lru使用的链表节点
 */
public class _11_Node {

    public _11_Node next;

    public _11_Node pre;

    public int key;

    public int value;

    public _11_Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
