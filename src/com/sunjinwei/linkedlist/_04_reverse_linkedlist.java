package com.sunjinwei.linkedlist;

import com.sunjinwei.domain.ListNode;

import java.util.Stack;

/**
 * 反转单链表
 */
public class _04_reverse_linkedlist {

    /**
     * 方法1：使用while
     */
    public ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }
        return pre;
    }

    /**
     * 方法2：使用stack栈 注意事项就是 防止成环
     */
    public ListNode reverse_02(ListNode head) {
        if (head == null) {
            return null;
        }
        Stack<ListNode> stack = new Stack<>();
        // 进栈
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        // 出栈
        ListNode reverse = stack.pop();
        ListNode temp = reverse;
        while (!stack.isEmpty()) {
            ListNode pop = stack.pop();
            temp.next = pop;
            //  temp = pop;
            temp = temp.next;
        }
        // 防止成环!!!!
        temp.next = null;
        return reverse;
    }


}
