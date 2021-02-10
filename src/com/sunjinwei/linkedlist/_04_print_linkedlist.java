package com.sunjinwei.linkedlist;

import com.sunjinwei.domain.ListNode;

import java.util.Stack;

/**
 * 从后往前输出链表
 */
public class _04_print_linkedlist {

    /**
     * 方法1：使用stack
     */
    public void invert(ListNode head) {
        if (head == null) {
            return;
        }
        // 使用栈实现
        // 进栈
        Stack<ListNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        // 出栈
        while (!stack.isEmpty()) {
            ListNode pop = stack.pop();
            System.out.println(pop.val);
        }
    }

    /**
     * 方法2：使用递归
     * 栈其实就是递归的一种体现
     */
    public void invert_02(ListNode head) {
        if (head == null) {
            return;
        }
        invert_02(head.next);
        System.out.println(head.val);
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode02 = new ListNode(2);
        ListNode listNode03 = new ListNode(3);
        ListNode listNode04 = new ListNode(4);
        ListNode listNode05 = new ListNode(18);
        listNode.next = listNode02;
        listNode02.next = listNode03;
        listNode03.next = listNode04;
        listNode04.next = listNode05;
        _04_print_linkedlist a04invertedlinkedlist = new _04_print_linkedlist();
        a04invertedlinkedlist.invert_02(listNode);

    }


}
