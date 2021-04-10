package com.sunjinwei._10_lru;

/**
 * 定义双链表,
 * 三个属性：头节点 尾巴节点 链表节点个数
 */
public class LinkedNode {

    public Node head;

    public Node tail;

    public int size;

    /**
     * 初始化哨兵节点!!! 很有用 这样可以
     */
    public LinkedNode() {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.pre = head;
        size = 0;
    }

    /**
     * 添加到最后一个节点 将最新的元素添加至末尾 【容易出错】
     *
     * @param node
     */
    public void addLast(Node node) {
        Node pre = tail.pre;
        pre.next = node;
        node.pre = pre;
        node.next = tail;
        tail.pre = node;
        size++;
    }

    /**
     * 删除第一个节点
     *
     * @param node
     */
    public void remove(Node node) {
        Node pre = node.pre;
        Node next = node.next;
        // 修改node上一个节点和下一个节点指向
        pre.next = next;
        next.pre = pre;
        size--;
    }

    /**
     * 删除最老的节点 也就是第一个节点 返回这个删除节点
     *
     * @return
     */
    public void deleteOldestNode() {
        remove(head.next);
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
        // 直接移除
        remove(node);
        // 直接添加
        addLast(node);
    }

}
