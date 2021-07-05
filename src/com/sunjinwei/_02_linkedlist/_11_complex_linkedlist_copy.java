package com.sunjinwei._02_linkedlist;

import com.sunjinwei.domain.ComplexListNode;

/**
 * 复杂链表的复制
 * 进阶做法：时间复杂度为O(n),空间复杂度为O(1)
 */
public class _11_complex_linkedlist_copy {

    /**
     * 1先对原链表进行复制 将next改为复制后的1`
     * 2处理复制后的链表的rand指针
     * 3将复制后的链表进行剥离
     *
     * @param head
     * @return
     */
    public ComplexListNode copy(ComplexListNode head) {
        if (head == null) {
            return null;
        }
        // 1复制
        ComplexListNode curr = head;
        ComplexListNode next = null;
        while (curr != null) {
            next = curr.next;
            // 复制
            curr.next = new ComplexListNode(curr.val);
            // 处理新节点的next指向
            curr.next.next = next;
            curr = next;
        }

        // 2处理rand
        curr = head;
        while (curr != null) {
            next = curr.next.next;
            if (curr.rand != null) {
                curr.next.rand = curr.rand.next;
            }
            curr = next;
        }

        // 3剥离
        ComplexListNode res = head.next;
        ComplexListNode copy = null;
        curr = head;
        while (curr != null) {
            next = curr.next.next;
            copy = curr.next;

            curr.next = next;
            if (next != null) {
                copy.next = next.next;
            }
            curr = next;
        }
        return res;
    }
}
