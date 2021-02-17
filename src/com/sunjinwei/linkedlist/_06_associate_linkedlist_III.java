package com.sunjinwei.linkedlist;

import com.sunjinwei.domain.ListNode;

/**
 * 两个单链表相交的一系列问题
 * <p>
 * 在本题中，单链表可能有环，也可能无环。给定两个单链表的头节点 head1 和head2，这两个链表可能相交，也可能不相交。
 * 请实现一个函数，如果两个链表相交，请返回相交的第一个节点；如果不相交，返回null即可。
 * 要求：如果链表1的长度为N，链表2的长度为M，时间复杂度请达到O（N+M），额外空间复杂度请达到O（1）。
 * 难度：困难
 * 拆成下面三个子问题，每个问题都可以作为一道独立的算法题：
 * 1。问题一：如何判断一个链表是否有环，如果有，则返回第一个进入环的节点，没有则返回null。
 * 2。问题二：如何判断两个无环链表是否相交，相交则返回第一个相交节点，不相交则返回null。
 * 3。问题三：如何判断两个有环链表是否相交，相交则返回第一个相交节点，不相交则返回null。
 * <p>
 * <p>
 * 如何判断两个无环链表是否相交，相交则返回第一个相交节点，不相交则返回null。
 * 力扣：160 剑指offer：52 难度都是简单
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 */
public class _06_associate_linkedlist_III {

    /**
     * 给定两个（单向）链表，判定它们是否相交并返回交点。
     * 请注意相交的定义基于节点的引用，而不是基于节点的值。
     * 换句话说，如果一个链表的第k个节点与另一个链表的第j个节点是同一节点（引用完全相同），则这两个链表相交。
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：41.6 MB, 在所有 Java 提交中击败了13.99%的用户
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 鲁棒性1
        if (headA == null || headB == null) {
            return null;
        }
        // 鲁棒性2
        if (headA == headB) {
            return headA;
        }
        // 1判断是否是相交
        // 分析：只需要判断各自最后一个节点是不是相等 如果是 说明是相交的 否则 不是
        // 1.1遍历第一个链表
        int len1 = 0;
        ListNode end1 = headA;
        while (end1.next != null) {
            len1++;
            end1 = end1.next;
        }
        int len2 = 0;
        ListNode end2 = headB;
        while (end2.next != null) {
            len2++;
            end2 = end2.next;
        }
        // 说明不相交 返回null即可
        if (end1 != end2) {
            return null;
        }
        // 再找相交的节点
        // 分析：比较长度 如果len1更大 那么两个链表从头开始走的话 链表1需要先走len1-len2步
        ListNode node1 = headA;
        ListNode node2 = headB;
        if (len1 > len2) {
            int k = len1 - len2;
            for (int i = 0; i < k; i++) {
                node1 = node1.next;
            }
            // 注意：这里要取= 不然就要next前写if判断 next后也要进行判断
            for (int i = 0; i <= len2; i++) {
                if (node1 == node2) {
                    return node1;
                }
                node1 = node1.next;
                node2 = node2.next;
            }
        }
        if (len1 < len2) {
            int k = len2 - len1;
            for (int i = 0; i < k; i++) {
                node2 = node2.next;
            }
            // 注意：这里要取= 不然就要next前写if判断 next后也要进行判断
            for (int i = 0; i <= len1; i++) {
                if (node1 == node2) {
                    return node1;
                }
                node1 = node1.next;
                node2 = node2.next;
            }
        }
        if (len1 == len2) {
            // 注意：这里要取= 不然就要next前写if判断 next后也要进行判断
            for (int i = 0; i <= len1; i++) {
                if (node1 == node2) {
                    return node1;
                }
                node1 = node1.next;
                node2 = node2.next;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        _06_associate_linkedlist_III linkedlist_iii = new _06_associate_linkedlist_III();
        ListNode three = new ListNode(3);
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode four = new ListNode(1);
        ListNode five = new ListNode(2);
        // 第一个链表
        three.next = one;
        one.next = two;
        // 第二个链表
        four.next = two;

        ListNode intersectionNode = linkedlist_iii.getIntersectionNode(one, four);
        System.out.println(intersectionNode.val);

    }


}