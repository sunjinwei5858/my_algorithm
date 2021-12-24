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
 *
 * 比较好的题解：https://leetcode-cn.com/problems/add-two-numbers/solution/hua-jie-suan-fa-2-liang-shu-xiang-jia-by-guanpengc/
 **/
public class _14_AddTwoNumbers {

    /**
     * 思路：这道题和 字符串相加是一样的 都需要进位，因为两个链表的长度可能不一样
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // 1链表要想到哨兵节点
        ListNode shaobing = new ListNode(-1);
        ListNode curr = null;
        curr = shaobing;

        int carry = 0;
        while (l1 != null || l2 != null) {
            int a = l1 != null ? l1.val : 0;
            int b = l2 != null ? l2.val : 0;
            int sum = a + b + carry;
            // 1计算进位
            carry = sum / 10;
            // 2计算位置的值 也就是余数
            sum = sum % 10;

            curr.next = new ListNode(sum);
            curr = curr.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
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
            listNode = listNode.next;
        }
    }
}