package com.sunjinwei._02_linkedlist02;

import com.sunjinwei.domain.ListNode;

/**
 * 一个环形单链表从头节点head开始不降序，同时由最后的节点指回头节点。
 * 给定这样一个环形单链表的头节点head和一个整数num，请生成节点值为num的新节点，并插入到这个环形链表中，
 * 保证调整后的链表依然有序。
 * <p>
 * 要求：时间复杂度为O（N）、额外空间复杂度为O（1）
 */
public class _08_Insert_NewNode {

    /**
     * 分析：
     * 1。环形链表有序，第一种情况，如果head本身为空，那么设置num为头节点，自己组成环形链表
     * 2。第二种情况，num的值介于环形链表中
     * 3。第三种情况，num的值比环形链表所有的值都大，或者所有的值都小
     * 都大：
     * 都小：
     *
     * @param head
     * @param num
     * @return
     */
    public ListNode insertNewNode(ListNode head, int num) {

        // 1先将num作为值的节点生成
        ListNode newNode = new ListNode(num);
        // 2鲁棒性:第一种情况
        if (head == null) {
            newNode.next = newNode;
            return newNode;
        }
        // 遍历的节点
        ListNode curr = head;
        // 下一个节点
        ListNode next = null;
        // 3.1比头节点的值还更小 那么此时num就可以作为头节点 尾巴节点指向num
        if (head.val > num) {
            while (curr != null) {
                next = curr.next;
                // 此时的curr就是尾巴节点
                if (next == head) {
                    break;
                }
                curr = curr.next;
            }
            curr.next = newNode;
            newNode.next = head;
            return newNode;
        }
        while (curr != null) {
            // 因为是环形链表 所以curr.next一定有值
            next = curr.next;
            // 3.3num的值介于环形链表之间
            if (curr.val < num && next.val > num) {
                // 此时处理curr newNode next 三个节点的指向
                curr.next = newNode;
                newNode.next = next;
                break;
            }
            // 3.2比末尾节点的值还更大，那么此时num就可以作为尾巴节点 next指向头节点
            if (next == head && curr.val < num) {
                // 此时的curr就是尾巴节点 尾巴节点指向newNode newNode指向头节点
                curr.next = newNode;
                newNode.next = head;
                break;
            }
            curr = curr.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(7);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = a;

        _08_Insert_NewNode insertNewNode = new _08_Insert_NewNode();

        ListNode res = insertNewNode.insertNewNode(a, 9);
        System.out.println(res);


    }
}
