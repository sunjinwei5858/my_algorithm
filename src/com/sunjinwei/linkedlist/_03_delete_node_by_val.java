package com.sunjinwei.linkedlist;


import com.sunjinwei.domain.ListNode;

import java.util.Stack;

/**
 * 1。给链表节点的值 删除该节点 【剑指offer18 删除链表的节点 简单】
 * 2。给链表节点 删除该节点 O(1)时间完成
 */
public class _03_delete_node_by_val {

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
     * 方法2：使用栈，时间复杂度和空间复杂度都为O(n)
     * 1将不等于val的进栈 2然后将栈底的节点作为新的头节点返回
     *
     * @param head
     * @param val
     */
    public ListNode deleteNodeByVal_02(ListNode head, int val) {
        Stack<ListNode> stack = new Stack<>();
        // 1将不需要删除的节点进栈
        while (head != null) {
            if (head.val != val) {
                stack.push(head);
            }
            head = head.next;
        }
        // 2将栈底的节点作为新的头节点返回
        // 这段代码的处理也是妙哉啊!!!!!
        // 完美的让节点从头开始 而不是反转了
        head = returnSame(stack);
        return head;
    }


    /**
     * 栈实现链表的反转
     *
     * @param stack
     */
    private ListNode reverse(Stack<ListNode> stack) {
        ListNode shaobing = new ListNode(-1);
        ListNode curr = shaobing;
        while (!stack.isEmpty()) {
            ListNode pop = stack.pop();
            curr.next = pop;
            curr = pop;
        }
        curr.next = null;
        return shaobing.next;
    }

    /**
     * 栈实现原链表的输出
     *
     * @param stack
     */
    private ListNode returnSame(Stack<ListNode> stack) {
        ListNode head = null;
        while (!stack.isEmpty()) {
            ListNode pop = stack.pop();
            pop.next = head;
            head = pop;
        }
        return head;
    }


    public static void main(String[] args) {
        ListNode first = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode forth = new ListNode(4);
        first.next = second;
        second.next = third;
        third.next = forth;

        _03_delete_node_by_val delete_node = new _03_delete_node_by_val();
        ListNode listNode = delete_node.deleteNodeByVal_02(first, 3);
        ListNode next = listNode.next;


    }
}
