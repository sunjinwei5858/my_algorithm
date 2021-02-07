package com.sunjinwei.linkedlist;


import com.sunjinwei.domain.ListNode;

/**
 * 1。给链表节点的值 删除该节点 【剑指offer18 删除链表的节点 简单】
 * 2。给链表节点 删除该节点 O(1)时间完成
 */
public class _03_delete_node_by_val {

    /**
     * 1。剑指offer18 删除链表的节点 简单
     * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
     * 返回删除后的链表的头节点。
     * <p>
     * 输入: head = [4,5,1,9], val = 5
     * 输出: [4,1,9]
     * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了22.42%的用户
     */
    public ListNode deleteNodeByVal(ListNode head, int val) {
        // 鲁棒性1：如果头节点为空
        if (head == null) {
            return null;
        }
        // 鲁棒性2：刚好是头节点
        if (head.val == val) {
            return head.next;
        }
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode temp = cur;
            cur = cur.next;
            pre = temp;
            if (cur.val == val) {
                pre.next = cur.next;
                break;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode first = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode forth = new ListNode(4);
        first.next = second;
        second.next = third;
        third.next = forth;

        _03_delete_node_by_val delete_node = new _03_delete_node_by_val();
        ListNode listNode = delete_node.deleteNodeByVal(first, 3);
        ListNode next = listNode.next;


    }
}
