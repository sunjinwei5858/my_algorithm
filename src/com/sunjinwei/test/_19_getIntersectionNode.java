package com.sunjinwei.test;

import com.sunjinwei.domain.ListNode;

/**
 * 相交链表 力扣160 难度：简单
 * 编写一个程序，找到两个单链表相交的起始节点。
 * <p>
 * 注意：
 * 如果两个链表没有交点，返回null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 */
public class _19_getIntersectionNode {


    /**
     * O(1)内存：先求出两个链表长度，哪个链表长度更长 那么先走(m-n)步，然后再进行比较
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        // 求出两个链表的长度
        int len1 = 0;
        int len2 = 0;
        ListNode n1 = headA;
        ListNode n2 = headB;
        while (n1 != null) {
            n1 = n1.next;
            len1++;
        }
        while (n2 != null) {
            n2 = n2.next;
            len2++;
        }
        // 情况1：两个链表长度相等 求第一个相交的节点
        if (len1 == len2) {
            n1 = headA;
            n2 = headB;
            while (n1 != null && n2 != null) {
                if (n1 != n2) {
                    n1 = n1.next;
                    n2 = n2.next;
                } else {
                    break;
                }
            }
            return n1;
        }
        // 情况2：两个长度不相等 让长链表先走(len2-len1)步
        if (len1 > len2) {
            // 短链表
            n1 = headB;
            // 长链表
            n2 = headA;
        } else {
            // 短链表
            n1 = headA;
            // 长链表
            n2 = headB;
        }
        // 长链表先走(len2-len1)步
        int num = Math.abs(len2 - len1);
        for (int i = 0; i < num; i++) {
            n2 = n2.next;
        }
        // 最后两个链表正常的遍历
        while (n1 != null && n2 != null) {
            if (n1 != n2) {
                n1 = n1.next;
                n2 = n2.next;
            } else {
                break;
            }
        }
        return n1;
    }

    /**
     * 代码进行优化
     */
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        // 求出两个链表的长度
        int len1 = 0;
        int len2 = 0;
        ListNode n1 = headA;
        ListNode n2 = headB;
        while (n1 != null) {
            n1 = n1.next;
            len1++;
        }
        while (n2 != null) {
            n2 = n2.next;
            len2++;
        }
        // 情况1：两个链表长度相等 求第一个相交的节点
        if (len1 == len2) {
            n1 = headA;
            n2 = headB;
            while (n1 != n2) {
                n1 = n1.next;
                n2 = n2.next;
            }
            return n1;
        }
        // 情况2：两个长度不相等 让长链表先走(len2-len1)步
        if (len1 > len2) {
            // 短链表
            n1 = headB;
            // 长链表
            n2 = headA;
        } else {
            // 短链表
            n1 = headA;
            // 长链表
            n2 = headB;
        }
        // 长链表先走(len2-len1)步
        int num = Math.abs(len2 - len1);
        for (int i = 0; i < num; i++) {
            n2 = n2.next;
        }
        // 最后两个链表正常的遍历
        while (n1 != n2) {
            n1 = n1.next;
            n2 = n2.next;
        }
        return n1;
    }

}
