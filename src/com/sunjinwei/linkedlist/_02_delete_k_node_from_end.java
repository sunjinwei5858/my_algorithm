package com.sunjinwei.linkedlist;

import com.sunjinwei.domain.ListNode;

/**
 * 在单链表和双链表中，删除倒数第k个节点
 * 条件：时间复杂度为O(n) 空间复杂度为O(1)
 */
public class _02_delete_k_node_from_end {

    /**
     * 单链表删除倒数第k个节点
     *
     * @param node
     * @param k
     * @return
     */
    public ListNode removeLastKthNode(ListNode node, int k) {
        // 先统计有多少个节点
        int count = 0;
        ListNode curr = node;
        while (curr != null) {
            curr = curr.next;
            count++;
        }
        // 倒数第k个节点，就是顺着第count-k节点 此时只需要找到count-k-1即可
        ListNode head = node;
        for (int i = 1; i <= count - k - 1; i++) {
            head = head.next;
        }
        // 删除这个节点
        ListNode next = head.next;
        head.next = next.next;
        return node;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;

        _02_delete_k_node_from_end delete_k_node = new _02_delete_k_node_from_end();
        ListNode listNode = delete_k_node.removeLastKthNode(listNode1, 2);

        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }


    }
}
