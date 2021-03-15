package com.sunjinwei._02_linkedlist;

import com.sunjinwei.domain.ListNode;

/**
 * 在单链表和双链表中，删除倒数第k个节点 力扣19
 * 条件：时间复杂度为O(n) 空间复杂度为O(1)
 * 注意这道题：
 * 1主要考察鲁棒性
 * 2灵活运用链表的技巧来解题
 */
public class _02_delete_k_node_from_end {

    /**
     * 单链表 方法一：
     * 1。鲁棒性
     * 2。先统计链表有多少个节点 然后找到倒数第n个节点的前一个节点 完成删除倒数第n个节点的操作
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.4 MB, 在所有 Java 提交中击败了48.95%的用户
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 鲁棒性1: 链表为空或者n小于0
        if (head == null || n < 0) {
            return head;
        }
        // 统计链表有多少个节点
        int total = 0;
        ListNode curr = head;
        while (curr != null) {
            curr = curr.next;
            total++;
        }
        // 鲁棒性2：n和节点数相同，也就是删除头节点
        if (n == total) {
            return head.next;
        }
        // 鲁棒性3：n大于节点数 直接返回空
        if (n > total) {
            return null;
        }
        // 倒数第n个节点，就是顺着第total-n节点, 只需要找到total-n的前一个节点，即total-n-1这个节点
        ListNode node = head;
        for (int i = 0; i < total - n - 1; i++) {
            node = node.next;
        }
        // 删除这个节点
        ListNode next = node.next;
        node.next = next.next;
        return head;
    }

    /**
     * 单链表 方法二
     * 双指针：快指针先走n步 然后慢指针开始，当快指针的next节点为null 此时的慢指针就是要删除节点的前一个指针
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.2 MB, 在所有 Java 提交中击败了89.68%的用户
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd_02(ListNode head, int n) {
        // 鲁棒性1: 链表为空或者n小于0
        if (head == null || n < 0) {
            return head;
        }
        // 鲁棒性2：只有一个节点
        if (head.next == null && n >= 1) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            if (n > 0) {
                fast = fast.next;
            } else {
                fast = fast.next;
                slow = slow.next;
            }
            n--;
        }
        // 鲁棒性3：删除的是头节点 因为是删除节点
        // 所以如果刚好删除头节点 那么快指针应该是就差一步走到尽头 所以是n=1
        if (n == 1) {
            ListNode temp = head.next;
            head.next = null;
            return temp;
        }
        ListNode delete = slow.next;
        ListNode next = delete.next;
        slow.next = next;
        return head;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        //ListNode listNode3 = new ListNode(3);
        //ListNode listNode4 = new ListNode(4);
        listNode1.next = listNode2;
        //listNode2.next = listNode3;
        //listNode3.next = listNode4;

        _02_delete_k_node_from_end delete_k_node = new _02_delete_k_node_from_end();
        ListNode listNode = delete_k_node.removeNthFromEnd_02(listNode1, 2);


    }
}
