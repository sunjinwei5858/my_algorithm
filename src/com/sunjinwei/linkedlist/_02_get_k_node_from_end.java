package com.sunjinwei.linkedlist;

import com.sunjinwei.domain.ListNode;


/**
 * 返回倒数第k个节点 剑指offer22
 */
public class _02_get_k_node_from_end {

    /**
     * 方法一：
     * 1。鲁棒性
     * 2。统计所有节点数，倒数第k个节点，也就是顺数n-k
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.5 MB, 在所有 Java 提交中击败了9.99%的用户
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        // 鲁棒性1：链表为空 或者k<0
        if (head == null || k < 0) {
            return null;
        }
        ListNode node = head;
        int n = 0;
        while (node != null) {
            node = node.next;
            n++;
        }
        // 鲁棒性2: k比节点数还大
        if (k > n) {
            return head;
        }
        for (int i = 0; i < n - k; i++) {
            head = head.next;
        }
        return head;
    }


    /**
     * 方法二：题解的做法
     * 1。鲁棒性
     * 2。使用双指针,第一个指针走k步，第二个指针再开始走，当第一个指针走到尽头的时候，此时第二个指针就是倒数第k个节点的位置
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.2 MB, 在所有 Java 提交中击败了76.15%的用户
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd_02(ListNode head, int k) {
        // 鲁棒性1：链表为空 或者k<0
        if (head == null || k < 0) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        // 快指针先走k步 然后慢指针再走；等快指针到达尽头 此时的慢指针刚好在倒数k的位置
        // 注意：不需要去判断 fast.next != null
        while (fast != null) {
            if (k-- > 0) {
                fast = fast.next;
            } else {
                fast = fast.next;
                slow = slow.next;
            }
        }
        return slow;
    }


}
