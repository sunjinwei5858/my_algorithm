package com.sunjinwei._02_linkedlist;

import com.sunjinwei.domain.ListNode;

/**
 * 在单链表和双链表中，删除倒数第k个节点 力扣19和剑指offer
 * 条件：时间复杂度为O(n) 空间复杂度为O(1)
 * 注意这道题：
 * 1主要考察鲁棒性 【当删除的是头节点】
 * 2灵活运用链表的技巧来解题 【双指针+哨兵节点，注意理解哨兵节点的作用】
 */
public class _02_delete_k_node_from_end {

    /**
     * 方法一：暴力方式
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
     * 方法二 双指针 O(1)内存 注意：最后需要判断是否删除的是头节点 防止程序崩溃
     * 双指针：快指针先走n步 然后快慢指针一起走，当快指针指向null的时候，此时slow就是要删除的节点!!!【规律】
     * <p>
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
        // 快指针先走n步
        while (n > 0) {
            fast = fast.next;
            n--;
        }
        // 记录慢指针的上一个节点
        ListNode pre = null;
        while (fast != null) {
            pre = slow;
            fast = fast.next;
            slow = slow.next;
        }
        // bug!!!! 需要判断如果删除的是头节点的情况!!!!
        if (slow == head) {
            return head.next;
        }
        // 正常删除节点
        pre.next = slow.next;
        return head;
    }


    /**
     * 方法2 双指针+哨兵节点 对双指针代码的重构,哨兵节点避免了删除头节点的情况，非常好!!!!
     *
     * @param head
     * @param n
     */
    public ListNode removeNthFromEnd_03(ListNode head, int n) {
        // 哨兵节点 避免了如果删除的是头节点的情况
        ListNode shaobing = new ListNode(-1);
        shaobing.next = head;
        // 声明快慢指针
        ListNode fast = shaobing;
        ListNode slow = shaobing;
        // 因为最终结果返回的是shaobing.next 此时n不需要做改动 因为当快指针走了n步后，最后快指针都要走到null的位置
        while (n > 0) {
            fast = fast.next;
            n--;
        }
        // 快慢指针一起走
        // 记录slow的上一个节点
        ListNode pre = null;
        while (fast != null) {
            pre = slow;
            fast = fast.next;
            slow = slow.next;
        }
        // 因为使用了哨兵节点 所以这里不需要判断删除头节点的情况
        pre.next = slow.next;
        return shaobing.next;
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
