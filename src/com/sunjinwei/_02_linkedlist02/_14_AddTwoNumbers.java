package com.sunjinwei._02_linkedlist02;

import com.sunjinwei.domain.ListNode;

/**
 * @program: com.sunjinwei._02_linkedlist02
 * @author: sun jinwei
 * @create: 2021-12-24 16:05
 * @description: 力扣2 两数相加
 * 给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，并且每个节点只能存储一位数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以0开头。
 * <p>
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807
 **/
public class _14_AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int num1 = 0;
        int num2 = 0;
        ListNode p1 = l1;
        ListNode p2 = l2;
        while (p1 != null) {
            num1 = num1 * 10 + p1.val;
            p1 = p1.next;
        }
        while (p2 != null) {
            num2 = num2 * 10 + p2.val;
            p2 = p2.next;
        }
        String s = String.valueOf(num1 + num2);
        char[] chars = s.toCharArray();
        ListNode shaobing = new ListNode(-1);
        ListNode pre = null;
        ListNode curr = null;
        pre = shaobing;

        for (int i = chars.length - 1; i >= 0; i--) {
            int tem = chars[i] - '0';
            curr = new ListNode(tem);
            pre.next = curr;
            pre = curr;
        }
        return shaobing.next;
    }

    public static void main(String[] args) {

        ListNode a1 = new ListNode(2);
        ListNode a2 = new ListNode(4);
        ListNode a3 = new ListNode(9);
        a1.next = a2;
        a2.next = a3;

        ListNode b1 = new ListNode(5);
        ListNode b2 = new ListNode(6);
        ListNode b3 = new ListNode(4);
        ListNode b4 = new ListNode(9);
        b1.next = b2;
        b2.next = b3;
        b3.next = b4;

        _14_AddTwoNumbers addTwoNumbers = new _14_AddTwoNumbers();
        ListNode listNode = addTwoNumbers.addTwoNumbers(a1, b1);
        while (listNode != null) {
            System.out.println(listNode.val);
        }
    }
}