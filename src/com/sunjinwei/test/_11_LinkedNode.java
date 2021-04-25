package com.sunjinwei.test;

/**
 * lru使用的双向链表
 */
public class _11_LinkedNode {

    public _11_Node head;

    public _11_Node tail;

    public int size;

    /**
     * 初始化 头节点和尾节点都初始化为哨兵节点
     */
    public _11_LinkedNode() {
        this.head = new _11_Node(0, 0);
        this.tail = new _11_Node(0, 0);
        head.next = tail;
        tail.pre = head;
        this.size = 0;
    }

    /**
     * 添加元素 每次都放在尾巴节点的前一个节点
     */
    public void addLast(_11_Node node) {
        _11_Node pre = tail.pre;
        pre.next = node;
        node.pre = pre;
        node.next = tail;
        // bug!!!
        /**
         * tail尾巴节点的pre指针未处理 bug3
         */
        tail.pre = node;
        size++;
    }

    /**
     * 移除元素
     */
    public _11_Node remove(_11_Node node) {
        _11_Node pre = node.pre;
        _11_Node next = node.next;
        pre.next = next;
        next.pre = pre;
        size--;
        return node;
    }

    /**
     * 将元素移至尾巴 热点数据的处理
     */
    public void move2Tail(_11_Node node) {
        remove(node);
        addLast(node);
    }

    /**
     * 删除最老的节点
     */
    public _11_Node deleteOldestNode() {
        return remove(head.next);
    }
}
