package com.sunjinwei.linkedlist;

import com.sunjinwei.domain.ListNode;

/**
 * 打印两个有序链表的公共部分，难度：简单
 * 注意：链表是有序的
 */
public class _01_print_2_linkedlist_common {

    /**
     * 打印有序链表的公共部分
     *
     * @param node1
     * @param node2
     */
    public void printLinkedlistCommon(ListNode node1, ListNode node2) {

        ListNode cur1 = node1;
        ListNode cur2 = node2;
        while (cur1 != null && cur2 != null) {
            if (cur1.val == cur2.val) {
                System.out.println(cur1.val);
                cur1 = cur1.next;
                cur2 = cur2.next;
            } else if (cur1.val > cur2.val) {
                cur2 = cur2.next;
            } else {
                cur1 = cur1.next;
            }
        }
    }

    public static void main(String[] args) {

    }
}
