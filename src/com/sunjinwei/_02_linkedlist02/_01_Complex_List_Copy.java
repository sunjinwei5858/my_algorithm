package com.sunjinwei._02_linkedlist02;

import com.sunjinwei.domain.ComplexListNode;

/**
 * 复杂链表的复制【复制含有随机指针节点的链表】力扣138
 * 要求：时间复杂度为O(n) 空间复杂度为O(1)
 */
public class _01_Complex_List_Copy {

    /**
     * 1。先复制一份
     * 2。处理rand指针
     * 3。解链和还原
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.8 MB, 在所有 Java 提交中击败了89.38%的用户
     *
     * @param head
     * @return
     */
    public ComplexListNode copyComplexListNode(ComplexListNode head) {
        if (head == null) {
            return null;
        }
        // 1复制链表 curr复制一份curr` 然后curr的next指向curr`
        ComplexListNode curr = head;
        ComplexListNode next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = new ComplexListNode(curr.val);
            curr.next.next = next;
            curr = next;
        }
        // 2处理curr`的rand指针
        curr = head;
        ComplexListNode rand = null;
        ComplexListNode newNode = null;
        while (curr != null) {
            next = curr.next.next;
            // 处理rand指针
            newNode = curr.next;

            // 容易出bug：忘记判空
            if (curr.rand != null) {
                rand = curr.rand;
                newNode.rand = rand.next;
            }
            curr = next;
        }
        // 3解链和还原
        ComplexListNode res = head.next;
        curr = head;
        while (curr != null) {
            // 声明旧链表的next节点
            next = curr.next.next;
            // 声明新链表
            newNode = curr.next;
            // 旧链表还原next
            curr.next = next;
            // 新链表重新设置next

            // 容易出bug：忘记判空
            if (next != null) {
                newNode.next = next.next;
            }
            // 继续处理下一个
            curr = next;
        }
        return res;
    }

    public static void main(String[] args) {
        ComplexListNode one = new ComplexListNode(1);
        ComplexListNode two = new ComplexListNode(2);
        ComplexListNode three = new ComplexListNode(3);

        one.next = two;
        two.next = three;

        one.rand = three;
        two.rand = one;
        three.rand = two;

        _01_Complex_List_Copy complexListCopy = new _01_Complex_List_Copy();

        ComplexListNode complexListNode = complexListCopy.copyComplexListNode(one);
        System.out.println(complexListNode);
    }

}
