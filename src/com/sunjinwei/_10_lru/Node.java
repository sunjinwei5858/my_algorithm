package com.sunjinwei._10_lru;

/**
 * 双链表节点
 */
public class Node {

    public Node pre;

    public Node next;

    public int val;

    public int key;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
