package com.sunjinwei.linkedlist;

import com.sunjinwei.domain.ListNode;

/**
 * 打印两个有序链表的公共部分，难度：简单
 * 注意：链表是有序的
 */
public class _01_print_common_sorted_linkedlist {

    /**
     * 打印有序链表的公共部分,逻辑还是比较简单的：
     * 1。值相等，指针都向后移动
     * 2。node1大于node2，node2指针向后移动【谁更小，谁向后移动】
     * 3。node1小于node2，node1指针向后移动 【谁更小，谁向后移动】
     * 4。node1和node2都不能为null
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

    /**
     * 简化代码，优化代码
     */
    public void printLinkedlistCommon_02(ListNode node1, ListNode node2) {

        while (node1 != null && node2 != null) {
            if (node1.val == node2.val) {
                System.out.println(node2.val);
                node1 = node1.next;
                node2 = node2.next;
            } else if (node1.val > node2.val) {
                node2 = node2.next;
            } else {
                node1 = node1.next;
            }
        }
    }


    public static void main(String[] args) {

    }
}
