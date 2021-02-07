package com.sunjinwei.linkedlist;

import com.sunjinwei.domain.ListNode;

/**
 * 合并两个有序链表： 难度 简单 剑指offer25和力扣21
 */
public class _01_merge_sorted_linkedlist {

    /**
     * 方法一：使用递归
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.46%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了14.22%的用户
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 鲁棒性1：空判断
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1 == null && l2 == null) {
            return null;
        }

        ListNode mergeNode = null;
        if (l1.val < l2.val) {
            mergeNode = l1;
            mergeNode.next = mergeTwoLists(l1.next, l2);
        } else {
            mergeNode = l2;
            mergeNode.next = mergeTwoLists(l1, l2.next);

        }
        return mergeNode;
    }

    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);
        one.next = second;
        third.next = fourth;

        _01_merge_sorted_linkedlist mergeSortedLinkedlist = new _01_merge_sorted_linkedlist();

        ListNode listNode = mergeSortedLinkedlist.mergeTwoLists(one, third);

        while (listNode != null) {
            System.out.println("========" + listNode.val);
            listNode = listNode.next;
        }


    }
}
