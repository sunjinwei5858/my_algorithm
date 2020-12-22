package com.sunjinwei.lru;


/**
 * lru缓存的双链表Node数据结构
 * 注意：
 * 1。key和value都要存储，不能仅仅存储value?
 *
 * 2。为什么要用双链表？
 *  因为需要淘汰策略是删除最后一个元素，需要知道前指针，双链表查询前指针的时间复杂度为O(1)
 *
 */
public class DoubleNode {

    public int key;

    public int value;

    public DoubleNode next;

    public DoubleNode prev;

    public DoubleNode(int key, int value) {
        this.key = key;
        this.value = value;
    }





}
