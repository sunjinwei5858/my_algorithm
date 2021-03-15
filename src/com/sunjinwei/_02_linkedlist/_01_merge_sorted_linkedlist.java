package com.sunjinwei._02_linkedlist;

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

    /**
     * 方法二：使用迭代
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.46%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了8.84%的用户
     *
     * @param l1
     * @param l2
     */
    public ListNode mergeTwoLists_02(ListNode l1, ListNode l2) {
        // 哨兵节点
        ListNode shaobing = new ListNode(0);
        // 当前节点
        ListNode cur = shaobing;
        // 如果l1和l2都不为空 才进入迭代
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            // 此时将cur的next赋值到cur
            cur = cur.next;
        }
        // 判断哪个链表先为null
        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }
        return shaobing.next;
    }


    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);
        ListNode fifth = new ListNode(5);
        one.next = second;
        third.next = fourth;
        fourth.next = fifth;

        _01_merge_sorted_linkedlist mergeSortedLinkedlist = new _01_merge_sorted_linkedlist();

        ListNode listNode = mergeSortedLinkedlist.mergeTwoLists_02(one, third);

        while (listNode != null) {
            System.out.println("========" + listNode.val);
            listNode = listNode.next;
        }


    }
}
