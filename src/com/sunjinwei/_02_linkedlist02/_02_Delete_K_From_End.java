package com.sunjinwei._02_linkedlist02;

import com.sunjinwei.domain.ListNode;

/**
 * 删除链表倒数第k个节点
 */
public class _02_Delete_K_From_End {

    /**
     * 1从头开始遍历
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode deleteKFromEnd(ListNode head, int k) {

        if (head == null || k < 0) {
            return null;
        }
        // 1从头到尾遍历链表 k--
        // 2k小于0时 此时从头开始遍历 k++
        // 3k等于0时 当前的节点就是要删除节点的前一个节点
        ListNode curr = head;
        while (curr != null) {
            if (k < 0) {
                break;
            }
            curr = curr.next;
            k--;
        }
        // 鲁棒性1 如果此时k还大于0 说明k太大了 直接返回头节点
        if (k > 0) {
            return head;
        }
        // 鲁棒性2 如果此时k=0 说明删除的是头节点
        if (k == 0) {
            return head.next;
        }
        curr = head;
        while (curr != null) {
            if (k == 0) {
                break;
            }
            curr = curr.next;
            k++;
        }
        // 此时的curr就是要删除节点的前一个节点
        ListNode del = curr.next;
        curr.next = del.next;
        return head;
    }

    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);

        one.next = two;
        two.next = three;
        three.next = four;

        _02_Delete_K_From_End deleteKFromEnd = new _02_Delete_K_From_End();

        ListNode listNode = deleteKFromEnd.deleteKFromEnd(one, 4);

        System.out.println(listNode);


    }
}
