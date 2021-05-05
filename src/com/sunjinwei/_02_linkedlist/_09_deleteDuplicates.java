package com.sunjinwei._02_linkedlist;

import com.sunjinwei.domain.ListNode;

/**
 * 83. 删除排序链表中的重复元素 难度：简单
 * <p>
 * 思路：和删除排序数组中的重复元素一样
 */
public class _09_deleteDuplicates {

    /**
     * 使用双指针
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null) {
            // 如果fast指针的值和slow不相等 那么slow进行++ 然后将fast的值赋给slow
            if (slow.val != fast.val) {
                ListNode node = slow.next;
                node.val = fast.val;
                slow = node;
            }
            fast = fast.next;

        }
        // 最后得到的链表是以slow指针为准
        slow.next = null;
        return head;
    }
}
