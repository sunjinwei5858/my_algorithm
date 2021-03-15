package com.sunjinwei._02_linkedlist;


import com.sunjinwei.domain.ListNode;

/**
 * 给链表节点 删除该节点 O(1)时间完成
 */
public class _03_delete_node_by_node {


    /**
     * 2。剑指offer 给链表节点 删除该节点 O(1)时间完成
     * 方法一：顺序遍历 找到前一个节点 进行删除 时间复杂度O(n)
     *
     * @param head       链表的头节点
     * @param deleteNode 要删除的节点
     */
    public void deleteNodeByNode(ListNode head, ListNode deleteNode) {
        // 鲁棒性1: 为空
        if (head == null || deleteNode == null) {
            return;
        }
        // 鲁棒性2：删除的就是头节点
        if (deleteNode.val == head.val) {
            head = head.next;
        }
        // 顺序查找 进行删除
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode temp = cur.next;
            cur = cur.next;
            pre = temp;
            if (deleteNode.val == cur.val) {
                ListNode next = deleteNode.next;
                pre.next = next;
                break;
            }
        }
    }

    /**
     * 2。剑指offer 给链表节点 删除该节点 O(1)时间完成
     * 方法二：直接将删除节点的next节点复制到删除节点 进行覆盖 保证O(1)时间完成
     * 注意：
     * 1。对于(n-1)个非尾节点删除，我们可以在O(1)时把下一个节点的内存复制覆盖到要删除的节点 并删除这个下一个节点
     * 2。对于尾节点删除，仍然需要顺序查找，时间复杂度O(n)
     * 这道题不是很完美，因为都是基于一个假设，要删除的节点存在链表当中，才能有O(1)的情况，这点需要提前和面试官讨论这个假设
     * 这样面试官会觉得我们考虑问题非常全面
     *
     * @param head       链表的头节点
     * @param deleteNode 要删除的节点
     */
    public void deleteNodeByNode_02(ListNode head, ListNode deleteNode) {
        // 鲁棒性1: 为空
        if (head == null || deleteNode == null) {
            return;
        }
        // 鲁棒性2：只有一个节点 基于删除节点真实存在链表的前提下
        if (head.next == null && deleteNode.val == head.val) {
            head = null;
        }
        // 鲁棒性3：删除的不是尾节点
        if (deleteNode.next != null) {
            // 使用O(1)的时间复杂度删除 复制方法
            ListNode next = deleteNode.next;
            deleteNode.val = next.val;
            deleteNode.next = next.next;
            next = null;
        } else {
            // 鲁棒性4：删除的是尾节点 那么仍然需要顺序遍历解决
            ListNode cur = head;
            while (cur.val != deleteNode.val) {
                cur = cur.next;
            }
            // 此时找到了尾节点 cur就是尾节点的前一个节点
            cur.next = null;
        }
    }


}
