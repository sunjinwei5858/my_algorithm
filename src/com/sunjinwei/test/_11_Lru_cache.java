package com.sunjinwei.test;

import java.util.HashMap;

/**
 * lru缓存
 *
 * 第二次写，有三处bug
 * 1添加元素：将key更新到缓存之后忘记添加元素到链表
 * 2超过size后 去除最老节点 忘记移除缓存
 * 3addLast方法 tail的pre忘记处理
 *
 */
public class _11_Lru_cache {

    public HashMap<Integer, _11_Node> hashMap;

    public _11_LinkedNode linkedNode;

    public int capacity;

    public _11_Lru_cache(int capacity) {
        this.hashMap = new HashMap<>();
        this.capacity = capacity;
        this.linkedNode = new _11_LinkedNode();
    }

    /**
     * 添加元素至缓存
     *
     * @param key
     * @param val
     */
    public void add(int key, int val) {
        // 查询缓存有没有
        _11_Node node = hashMap.get(key);
        if (node == null) {
            // 1判断是否需要删除元素
            handleCapacity();
            // 2创建新节点
            node = new _11_Node(key, val);
            // 3更新至缓存
            hashMap.put(key, node);
            // 4添加元素 bug!!!!!
            /**
             * bug2 忘记添加元素
             */
            linkedNode.addLast(node);
        } else {
            // 1更新value值
            node.value = val;
            // 2将该节点移动到尾巴
            linkedNode.move2Tail(node);
        }
    }

    private void handleCapacity() {
        if (capacity == linkedNode.size) {
            // 1移除头部第一个元素
            _11_Node oldestNode = linkedNode.deleteOldestNode();
            /**
             * bug1 缓存没去除
             */
            // 2缓存也需要移除 bug!!!!!
            hashMap.remove(oldestNode.key);
        }
    }

    /**
     * 根据key查询节点node
     */
    public _11_Node get(int key) {
        _11_Node node = hashMap.get(key);
        if (node == null) {
            return null;
        }
        // 更新该节点到尾巴节点
        linkedNode.move2Tail(node);
        return node;
    }



}
