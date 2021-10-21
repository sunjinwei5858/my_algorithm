package com.sunjinwei._02_linkedlist;

import com.sunjinwei.domain.ListNode;


/**
 * 返回倒数第k个节点 剑指offer22 和力扣19 删除倒数第k个节点 照相呼应!!
 * 方法1：正常的遍历做法 时间复杂度为O(n)
 * 方法2：双指针 O(1)内存
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
     * 方法二：双指针O(1)内存
     * 1。鲁棒性
     * 2。使用双指针, 快指针先走k步，然后快慢指针一起走，当快指针为null时，此时slow就是倒数第k个节点【画图!!!!!】
     * <p>
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
        // 快指针走k步
        while (k > 0) {
            fast = fast.next;
            k--;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 第三种方式：到达最后一个节点停止
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd_03(ListNode head, int k) {
        // 鲁棒性1：链表为空 或者k<0
        if (head == null || k < 0) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        // 相当于先走 k-1 步
        while (k > 1) {
            fast = fast.next;
            k--;
        }
        // 那么这里就需要 到达尾巴节点退出循环
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        _02_get_k_node_from_end getKNodeFromEnd = new _02_get_k_node_from_end();

        ListNode node = getKNodeFromEnd.getKthFromEnd_02(a, 2);
        System.out.println(node);
    }

}
