package com.sunjinwei._12_huiwen;

import com.sunjinwei.domain.ListNode;

/**
 * 判断是否是回文链表
 * 思路：反转一半 然后进行比较
 */
public class _03_huiwen_linkedlsit {

    /**
     * O(1)空间解决
     * <p>
     * 有两种方式：
     * 第一种方式：前半部分反转，然后和后半部分进行比较，注意：需要处理奇偶问题，后半部分的起点从哪开始
     * 第二种方式：后半部分反转 其实也是需要找到后半部分的起点位置
     * 关键点：根据fast是否为null 得出奇偶性【通过画图就可以分析出来】
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        // 寻找链表中点，但是需要判断奇偶
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 反转前半部分
        ListNode first = reverse(head, slow);
        // 寻找后半部分的起点
        // 偶数：起点直接为slow
        // 奇数：起点为slow.next
        ListNode second = slow;
        if (fast != null) {
            second = second.next;
        }
        while (first != null && second != null) {
            if (first.val != second.val) {
                return false;
            }
            first = first.next;
            second = second.next;
        }
        return true;

    }

    private ListNode reverse(ListNode head, ListNode slow) {

        ListNode pre = null;
        ListNode curr = head;
        while (curr != slow) {
            ListNode temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }
        return pre;
    }

}
