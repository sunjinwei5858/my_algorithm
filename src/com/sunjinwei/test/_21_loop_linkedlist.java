package com.sunjinwei.test;

import com.sunjinwei.domain.ListNode;

/**
 * 判断链表是否有环,返回进入环的第一个节点
 */
public class _21_loop_linkedlist {

    public ListNode loopLinkedlist(ListNode head) {
        if (head == null) {
            return null;
        }
        // 判断是否存在环
        if (!checkHasLoop(head)) {
            return null;
        }
        // 寻找环的进入环的第一个节点
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // 快慢指针相遇 此时让快指针回到head
            if (slow == fast) {
                fast = head;
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;

            }
        }
        return null;
    }

    private boolean checkHasLoop(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
