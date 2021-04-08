package com.sunjinwei._10_cache_design;

import com.sunjinwei.domain.LinkedNode;
import com.sunjinwei.domain.Node;

import java.util.HashMap;

/**
 * lru缓存设计
 * 核心数据结构：哈希表存储key和node
 * 核心方法啊：
 * 1.put：元素的添加 判断key是否存在+是否需要进行处理移除最老元素+缓存和链表进行添加新元素
 * 2.removeOldest：当容量不足时 移除最老的元素
 * 3.get：获取元素 并且将元素的顺序放置到尾巴节点 热点数据更新 O(1)空间
 */
public class _01_lru_cache {

    public HashMap<Integer, Node> keyMap;

    public LinkedNode linkedNode;

    public int capacity;

    public _01_lru_cache(int capacity) {
        this.keyMap = new HashMap<>();
        this.linkedNode = new LinkedNode();
        this.capacity = capacity;
    }

    /**
     * put操作
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        Node newNode = new Node(key, value);
        // 判断是否包含key
        if (keyMap.containsKey(key)) {
            Node node = keyMap.get(key);
            linkedNode.remove(node);
        }
        // 1判断是否需要移除最老的
        removeOldest();
        // 2链表中插入新节点
        linkedNode.add(newNode);
        // 3缓存中插入key
        keyMap.put(key, newNode);
    }

    /**
     * 处理：移除最老的元素
     */
    private void removeOldest() {
        // 获取链表个数
        int size = linkedNode.getSize();
        if (size > capacity) {
            // 1链表移除最老的元素
            Node oldestNode = linkedNode.deleteOldestNode();
            // 2缓存中删除最老的元素
            keyMap.remove(oldestNode.key);
        }
    }

    /**
     * 获取元素
     * 1.判断key是否在缓存中
     * 2.将该节点放置尾巴节点 提高优先级
     *
     * @param key
     * @return
     */
    public Node get(int key) {

        if (!keyMap.containsKey(key)) {
            return null;
        }
        Node node = keyMap.get(key);
        linkedNode.moveNode2Tail(node);
        return node;

    }

}
