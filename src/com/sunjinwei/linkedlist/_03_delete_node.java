package com.sunjinwei.linkedlist;


import com.sunjinwei.domain.ListNode;

/**
 * 1。给链表节点的值 删除该节点 【剑指offer18 删除链表的节点 简单】
 * 2。给链表节点 删除该节点 O(1)时间完成
 */
public class _03_delete_node {

    /**
     * 1。剑指offer18 删除链表的节点 简单
     * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
     * 返回删除后的链表的头节点。
     * <p>
     * 输入: head = [4,5,1,9], val = 5
     * 输出: [4,1,9]
     * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了22.42%的用户
     */
    public ListNode deleteNodeByVal(ListNode head, int val) {
        // 鲁棒性1：如果头节点为空
        if (head == null) {
            return null;
        }
        // 鲁棒性2：刚好是头节点
        if (head.val == val) {
            return head.next;
        }
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode temp = cur;
            cur = cur.next;
            pre = temp;
            if (cur.val == val) {
                pre.next = cur.next;
                break;
            }
        }
        return head;
    }


    /**
     * 2。剑指offer 给链表节点 删除该节点 O(1)时间完成
     * 方法一：顺序遍历 找到前一个节点 进行删除 时间复杂度O(n)
     *
     * @param head       链表的头节点
     * @param deleteNode 要删除的节点
     */
    public void deleteNodeByNode(ListNode head, ListNode deleteNode) {
        // 鲁棒性1: 为空
        if (head == null || deleteNode == null) {
            return;
        }
        // 鲁棒性2：删除的就是头节点
        if (deleteNode.val == head.val) {
            head = head.next;
        }
        // 顺序查找 进行删除
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            ListNode temp = cur.next;
            cur = cur.next;
            pre = temp;
            if (deleteNode.val == cur.val) {
                ListNode next = deleteNode.next;
                pre.next = next;
                break;
            }
        }
    }

    /**
     * 2。剑指offer 给链表节点 删除该节点 O(1)时间完成
     * 方法二：直接将删除节点的next节点复制到删除节点 进行覆盖 保证O(1)时间完成
     * 注意：
     * 1。对于(n-1)个非尾节点删除，我们可以在O(1)时把下一个节点的内存复制覆盖到要删除的节点 并删除这个下一个节点
     * 2。对于尾节点删除，仍然需要顺序查找，时间复杂度O(n)
     * 这道题不是很完美，因为都是基于一个假设，要删除的节点存在链表当中，才能有O(1)的情况，这点需要提前和面试官讨论这个假设
     * 这样面试官会觉得我们考虑问题非常全面
     *
     * @param head       链表的头节点
     * @param deleteNode 要删除的节点
     */
    public void deleteNodeByNode_02(ListNode head, ListNode deleteNode) {
        // 鲁棒性1: 为空
        if (head == null || deleteNode == null) {
            return;
        }
        // 鲁棒性2：只有一个节点 基于删除节点真实存在链表的前提下
        if (head.next == null && deleteNode.val == head.val) {
            head = null;
        }
        // 鲁棒性3：删除的不是尾节点
        if (deleteNode.next != null) {
            // 使用O(1)的时间复杂度删除 复制方法
            ListNode next = deleteNode.next;
            deleteNode.val = next.val;
            deleteNode.next = next.next;
            next = null;
        } else {
            // 鲁棒性4：删除的是尾节点 那么仍然需要顺序遍历解决
            ListNode cur = head;
            while (cur.val != deleteNode.val) {
                cur = cur.next;
            }
            // 此时找到了尾节点 cur就是尾节点的前一个节点
            cur.next = null;
        }
    }

    public static void main(String[] args) {
        ListNode first = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode forth = new ListNode(4);
        first.next = second;
        second.next = third;
        third.next = forth;

        _03_delete_node delete_node = new _03_delete_node();
        ListNode listNode = delete_node.deleteNodeByVal(first, 3);
        ListNode next = listNode.next;


    }
}
