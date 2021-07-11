package com.sunjinwei._02_linkedlist02;

import com.sunjinwei.domain.ListNode;

/**
 * 在单链表中删除指定值的节点
 */
public class _12_Remove_Value {

    /**
     * 方法1：O(1)空间
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeValue(ListNode head, int val) {


        ListNode curr = head;
        ListNode pre = null;

        while (curr != null) {
            if (curr.val == val) {
                if (pre == null) {
                    // 头节点相等 移除头节点
                    head = head.next;
                } else {
                    pre.next = curr.next;
                }
            }
            pre = curr;
            curr = curr.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(3);
        ListNode e = new ListNode(3);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        _12_Remove_Value removeValue = new _12_Remove_Value();
        ListNode head = removeValue.removeValue(a, 1);
        System.out.println(head);
    }
}
