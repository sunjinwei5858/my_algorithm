package com.sunjinwei._10_lru;

import java.util.HashMap;

/**
 * lru缓存设计
 * 核心数据结构：哈希表存储key和node
 * 核心方法啊：
 * 1.put：元素的添加 判断key是否存在+是否需要进行处理移除最老元素+缓存和链表进行添加新元素
 * 2.removeOldest：当容量不足时 移除最老的元素
 * 3.get：获取元素 并且将元素的顺序放置到尾巴节点 热点数据更新 O(1)空间
 * <p>
 * 执行用时：18 ms, 在所有 Java 提交中击败了93.62%的用户
 * 内存消耗：46.4 MB, 在所有 Java 提交中击败了76.26%的用户
 */
public class LRU {

    public HashMap<Integer, Node> keyMap;

    public LinkedNode linkedNode;

    public int capacity;

    public LRU(int capacity) {
        this.keyMap = new HashMap<>();
        this.linkedNode = new LinkedNode();
        this.capacity = capacity;
    }

    /**
     * put操作
     * 需要注意点：
     * 1.移除最老元素的判断应该放在缓存中找不到key的时候
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        Node target = keyMap.get(key);
        if (target == null) {
            // 判断是否需要移除最老元素 只能放在当缓存找不到key的时候
            // 因为key如果存在 只是覆盖的话 不需要判断要不要移除最老元素
            removeOldest();
            target = new Node(key, value);
            // 将元素放入缓存
            keyMap.put(key, target);
            // 将元素放入链表 放置在最后
            linkedNode.addLast(target);
        } else {
            // 修改值
            target.val = value;
            // 将该节点的值优先级提高
            linkedNode.moveNode2Tail(target);
        }
    }

    /**
     * 处理：移除最老的元素
     */
    private void removeOldest() {
        // 获取链表个数
        int size = linkedNode.getSize();
        if (size == capacity) {
            // 1缓存中删除最老的元素
            Node oldestNode = linkedNode.head.next;
            keyMap.remove(oldestNode.key);
            // 2链表移除最老的元素
            linkedNode.deleteOldestNode();
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
    public int get(int key) {
        Node node = keyMap.get(key);
        if (node == null) {
            return -1;
        }
        linkedNode.moveNode2Tail(node);
        return node.val;

    }


    /**
     * ["LRUCache","put","put","get","put","put","get"]
     * [[2],[2,1],[2,2],[2],[1,1],[4,1],[2]]
     * <p>
     * ["LRUCache","put","put","get","put","get","put","get","get","get"]
     * [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
     * <p>
     * ["LRUCache","put","put","get","put","put","get"]
     * [[2],[2,1],[2,2],[2],[1,1],[4,1],[2]]
     * <p>
     * ["LRUCache","get","put","get","put","put","get","get"]
     * [[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
     *
     * @param args
     */
    public static void main(String[] args) {
        LRU cache = new LRU(2);
        // 覆盖
        cache.put(2, 2);
        cache.put(1, 1);
        cache.put(3, 3);

        cache.get(1);


        Node p = cache.linkedNode.tail;
        while (p != null) {
            System.out.println(p.val);
            p = p.pre;
        }


    }

}
