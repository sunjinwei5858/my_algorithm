package com.sunjinwei.lru;

/**
 * 提供双链表的增删改查功能
 * 主要就是添加元素，删除元素
 * 1.添加元素-->在链表的头部添加元素 addFirst
 * 2.删除元素
 * 删除元素 remove
 * 删除尾部元素 removeLast
 */
public class DoubleList {

    private DoubleNode head;

    private DoubleNode tail;

    private int size;

    public DoubleList() {
        this.size = 0;
        this.head = new DoubleNode(0, 0);
        this.tail = new DoubleNode(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    /**
     * 添加元素--其实就是在头部添加
     *
     * @param doubleNode
     */
    public void addFirst(DoubleNode doubleNode) {
        DoubleNode next = head.next;
        doubleNode.next = next;
        doubleNode.prev = head;
        next.prev = doubleNode;
        size++;
    }

    /**
     * 删除元素
     *
     * @param doubleNode
     */
    public void remove(DoubleNode doubleNode) {
        DoubleNode prev = doubleNode.prev;
        DoubleNode next = doubleNode.next;
        prev.next = next;
        next.prev = prev;
        size--;
    }

    /**
     * 删除最后一个元素并返回这个节点
     * 1.判断是否成环
     * 2.返回删除的元素
     *
     * @return
     */
    public DoubleNode removeLast() {
        if (head == tail.prev) {
            return null;
        }
        DoubleNode last = tail.prev;
        remove(last);
        return last;
    }

    public int getSize() {
        return size;
    }


}
