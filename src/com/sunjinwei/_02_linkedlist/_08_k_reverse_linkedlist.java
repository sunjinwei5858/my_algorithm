package com.sunjinwei._02_linkedlist;

import com.sunjinwei.domain.ListNode;

/**
 * k个一组翻转链表 力扣25
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 进阶：
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 */
public class _08_k_reverse_linkedlist {

    /**
     * 递归实现：代码和逻辑都很清晰 容易理解
     * 注意：两个不需要进行反转的条件，一个head为null 一个链表个数小于k
     * 时间复杂度为：
     * 空间复杂度：因为有递归调用 所以需要使用栈空间
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // 递归处理:
        // 终止条件1
        if (head == null) {
            return null;
        }
        // 声明两个变量 一个用于记录还没反转的头节点 一个用于反转结束的节点
        ListNode p1 = head;
        ListNode p2 = head;
        for (int i = 0; i < k; i++) {
            // 终止条件2: 比如[1,2,3,4,5] k=3 那么[1,2,3]反转完 剩下[4,5]个数小于k是不需要进行反转的
            // base case 不足k 不需要反转
            // if判断不能反
            if (p2 == null) {
                return head;
            }
            p2 = p2.next;
        }
        // 进行反转 左闭右开
        ListNode first = reverse(p1, p2);
        // 进行递归
        p1.next = reverseKGroup(p2, k);
        return first;
    }

    private ListNode reverse(ListNode nodeA, ListNode nodeB) {
        ListNode curr = nodeA;
        ListNode pre = null;
        while (curr != null && curr != nodeB) {
            ListNode temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }
        return pre;
    }


    /**
     * 方法2：O(1)空间
     * 将链表拆成三部分：反转好的 反转中的[start,tail] 还未进行反转的
     * 使用哨兵节点位于头节点之前
     */
    public ListNode reverseKGroup2(ListNode head, int k) {
        // 哨兵节点
        ListNode shaobing = new ListNode(0);
        // 哨兵节点和头节点关系
        shaobing.next = head;
        // 反转好的结束节点
        ListNode pre = shaobing;
        ListNode curr = head;
        while (curr != null) {
            // 提前存储好反转之后的尾巴节点
            ListNode tail = curr;
            for (int i = 0; i < k; i++) {
                // base case k次next后的节点不能为空
                // if判断必须放置在前面
                if (curr == null) {
                    return shaobing.next;
                }
                curr = curr.next;
            }
            // 反转返回反转后的头节点
            ListNode start = reverse(tail, curr);
            // 1连接上一个节点和头节点
            pre.next = start;
            // 2连接尾巴节点和未进行反转的
            tail.next = curr;
            pre = tail;
        }
        return shaobing.next;
    }

    public static void main(String[] args) {
        _08_k_reverse_linkedlist reverseLinkedlist = new _08_k_reverse_linkedlist();
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);
        ListNode fifth = new ListNode(5);
        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;

        ListNode listNode = reverseLinkedlist.reverseKGroup2(head, 2);
        System.out.println(listNode);

    }
}
