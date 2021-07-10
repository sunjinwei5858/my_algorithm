package com.sunjinwei._02_linkedlist02;

import com.sunjinwei.domain.ListNode;

/**
 * 按照左右半区的方式重新组合单链表
 * <p>
 * 给定一个单链表的头部节点head，链表长度为N，
 * 如果N为偶数，那么前N/2个节点算作左半区，后N/2个节点算作右半区；
 * 如果N为奇数，那么前N/2个节点算作左半区，后N/2+1个节点算作右半区。
 * 左半区从左到右依次记为 L1-＞L2-＞…，右半区从左到右依次记为R1-＞R2-＞…，请将单链表调整成L1-＞R1-＞L2-＞R2-＞…的形式。
 */
public class _10_Relocate_LinkedList {

    public void relocate(ListNode head) {

        // 鲁棒性1：节点为null或者只有一个节点 无需调整
        if (head == null || head.next == null) {
            return;
        }
        // 计算链表的长度
        int len = 0;
        ListNode curr = head;
        while (curr != null) {
            curr = curr.next;
            len++;
        }
        // 1-2-3 也是不需要进行调整的
        if (len < 4) {
            return;
        }
        // 找到左半区的尾巴节点
        curr = head;
        len = len / 2;
        while (len > 0) {
            len--;
            // 注意 如果此时len==0 那么curr不需要进行更新了 比如长度为5 那么只需要第二个节点就是左半区的尾巴节点
            if (len == 0) {
                break;
            }
            curr = curr.next;
        }

        // 此时的curr就是左半区的尾巴节点
        ListNode right = curr.next;
        curr.next = null;

        // 合并处理
        mergeLeftAndRight(head, right);
    }

    /**
     * 进行合并，画图 画图!!!! 【不难】
     *
     * @param left
     * @param right
     */
    private void mergeLeftAndRight(ListNode left, ListNode right) {
        ListNode next = null;
        while (left.next != null) {
            // 先提前记录好right的next
            next = right.next;
            // 进行处理
            right.next = left.next;
            left.next = right;
            // 更新left
            left = right.next;
            // 更新right
            right = next;
        }
        left.next = right;

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

        _10_Relocate_LinkedList linkedList = new _10_Relocate_LinkedList();
        linkedList.relocate(a);

        while (a != null) {
            System.out.println(a.val);
            a = a.next;
        }

    }


}
