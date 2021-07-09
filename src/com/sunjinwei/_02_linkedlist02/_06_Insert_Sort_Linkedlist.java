package com.sunjinwei._02_linkedlist02;

import com.sunjinwei.domain.ListNode;

/**
 * @program: com.sunjinwei._02_linkedlist02
 * @author: sun jinwei
 * @create: 2021-07-08 22:36
 * @description: 链表的插入排序
 **/
public class _06_Insert_Sort_Linkedlist {

    /**
     * 插入排序思想：
     * 假设前面都是排序好的链表 一直往前找
     * 插入排序的基本思想是，维护一个有序序列，初始时有序序列只有一个元素，每次将一个新的元素插入到有序序列中，
     * 将有序序列的长度增加1，直到全部元素都加入到有序序列中。
     * <p>
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.11%的用户
     * 内存消耗：37.9 MB, 在所有 Java 提交中击败了87.64%的用户
     *
     * @param head
     * @return
     */
    public ListNode insertSort(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        // 把第一个节点 也就是头节点 当成是有序的
        ListNode newHead = head;
        // 排好序的尾巴节点
        ListNode tail = head;
        // 当前遍历的节点 [newHead,tail]是排好序的 [tail.next, ...]是没有排好序的
        ListNode curr = tail.next;
        while (curr != null) {
            // 情况1：不需要处理 直接更新尾巴节点
            if (tail.val <= curr.val) {
                tail = tail.next;
            } else {
                ListNode pre = newHead;
                // 情况2：直接当成头节点 更新头节点
                if (pre.val > curr.val) {
                    tail.next = curr.next;
                    curr.next = pre;
                    newHead = curr;
                } else {
                    // 情况3：在头节点和尾巴节点之间 寻找位置
                    while (pre.next.val <= curr.val) {
                        pre = pre.next;
                    }
                    // 处理：将curr插入到pre和pre.next之间
                    // 尾巴节点的next指向curr的next
                    tail.next = curr.next;
                    // curr的next指向pre的next
                    curr.next = pre.next;
                    // pre的next指向curr
                    pre.next = curr;
                }
            }
            // 更新curr
            curr = tail.next;
        }
        return newHead;
    }


    public static void main(String[] args) {
        ListNode a = new ListNode(4);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(1);
        ListNode d = new ListNode(3);

        a.next = b;
        b.next = c;
        c.next = d;

        _06_Insert_Sort_Linkedlist sortLinkedlist = new _06_Insert_Sort_Linkedlist();

        ListNode res = sortLinkedlist.insertSort(a);

        System.out.println(res);

    }
}