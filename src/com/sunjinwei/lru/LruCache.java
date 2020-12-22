package com.sunjinwei.lru;

import java.util.HashMap;

/**
 * LRU缓存：哈希表+双链表
 * 哈希表：利于查询，根据key查询value
 * 双链表：维护最近最少使用的情况
 * api: put/get
 */
public class LruCache {

    private HashMap<Integer, Integer> hashmap;

    private DoubleList doubleList;

    private int capacity;

    public LruCache(int capacity) {
        this.capacity = capacity;
        this.doubleList = new DoubleList();
        this.hashmap = new HashMap<>();
    }

    /**
     * put
     */
    public void put(int key, int value) {
        DoubleNode doubleNode = new DoubleNode(key, value);
        if (hashmap.containsKey(key)) {
            doubleList.remove(doubleNode);
        } else {
            // 判断容量是否超过
            if (doubleList.getSize() >= capacity) {
                DoubleNode last = doubleList.removeLast();
                hashmap.remove(last.key);
            }
        }
        doubleList.addFirst(doubleNode);
        hashmap.put(key, value);
    }


    /**
     * get
     */
    public int get(int key) {
        if (!hashmap.containsKey(key)) {
            return -1;
        }
        Integer value = hashmap.get(key);
        put(key, value);
        return value;
    }

}
