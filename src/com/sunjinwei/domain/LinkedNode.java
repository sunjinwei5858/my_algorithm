package com.sunjinwei.domain;

/**
 * 定义双链表,
 * 三个属性：头节点 尾巴节点 链表节点个数
 * 两个核心方法：删除第一个节点
 */
public class LinkedNode {

    public Node head;

    public Node tail;

    public int size;

    public LinkedNode() {
        // 初始化哨兵节点
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.pre = head;
        this.size = 0;
    }

    /**
     * 添加节点 将最新的元素添加至末尾
     *
     * @param node
     */
    public void add(Node node) {
        Node pre = tail.pre;
        pre.next = node;
        node.next = tail;
        size++;
    }

    /**
     * 删除节点
     *
     * @param node
     */
    public void remove(Node node) {
        Node pre = node.pre;
        Node next = node.next;
        pre.next = next;
        next.pre = pre;
        size--;
    }

    /**
     * 删除最老的节点 也就是第一个节点 返回这个删除节点
     *
     * @return
     */
    public Node deleteOldestNode() {
        // 如果还没有任何节点 此时两个哨兵节点是互相指向的
        if (head.next == tail) {
            return null;
        }
        Node first = head.next;
        Node second = first.next;
        head.next = second;
        second.pre = head;
        size--;
        return first;
    }

    /**
     * 获取链表的长度
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 将最新访问的节点放置尾巴
     *
     * @param node
     */
    public void moveNode2Tail(Node node) {
        Node next = node.next;
        Node pre = node.pre;
        pre.next = next;
        next.pre = pre;
        tail.pre = node;
        node.next = tail;
    }

}
