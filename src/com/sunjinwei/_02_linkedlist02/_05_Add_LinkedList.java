package com.sunjinwei._02_linkedlist02;

import com.sunjinwei.domain.ListNode;

import java.util.Stack;

/**
 * 两个链表相加为数字【类比两个字符串数字相加】 面试题 02.05. 链表求和
 * 思路：低位相加 然后高位进1
 * 方法1：O(n)内存
 * 方法2：O(1)内存
 */
public class _05_Add_LinkedList {


    /**
     * 个位排在链表末尾 例如：9-＞3-＞7 即937
     * 方法1：使用栈，这样就可以将链表逆序
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addLinkedList(ListNode l1, ListNode l2) {

        // 1使用栈进行逆序
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        ListNode n = l1;
        while (n != null) {
            stack1.push(n.val);
            n = n.next;
        }
        n = l2;
        while (n != null) {
            stack2.push(n.val);
            n = n.next;
        }

        // 2进行相加处理
        int n1 = 0;
        int n2 = 0;
        int ca = 0;
        int total = 0;
        ListNode pre = null;
        ListNode curr = null;

        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (!stack1.isEmpty()) {
                n1 = stack1.pop();
            }
            if (!stack2.isEmpty()) {
                n2 = stack2.pop();
            }
            total = n1 + n2 + ca;
            pre = curr;
            // 取余数
            curr = new ListNode(total % 10);
            // 从后往前指向
            curr.next = pre;
            // 取高位
            ca = total / 10;
        }
        // 如果此时高位ca还存在
        if (ca != 0) {
            pre = curr;
            curr = new ListNode(ca);
            curr.next = pre;
        }
        return curr;

    }


    /**
     * 个位排在链表末尾 例如：9-＞3-＞7 即937
     * 方法2：O(1)内存 先将链表反转 使用有限变量解决
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addLinkedList2(ListNode l1, ListNode l2) {
        // 1先将两个进行反转
        ListNode h1 = reverseNode(l1);
        ListNode h2 = reverseNode(l2);

        return addLinkedList3(h1,h2);
    }


    private ListNode reverseNode(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }
        return pre;
    }


    /**
     * 个位排在链表首部 输入：7->1->6 即617
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addLinkedList3(ListNode l1, ListNode l2) {

        ListNode h1 = l1;
        ListNode h2 = l2;
        // 2进行相加处理
        int n1 = 0;
        int n2 = 0;
        int ca = 0;
        int total = 0;
        ListNode pre = null;
        ListNode curr = null;
        while (h1 != null || h2 != null) {
            if (h1 != null) {
                n1 = h1.val;
                h1 = h1.next;
            }
            if (h2 != null) {
                n2 = h2.val;
                h2 = h2.next;
            }
            total = n1 + n2 + ca;
            pre = curr;
            // 取余数
            curr = new ListNode(total % 10);
            // 从后往前指向
            curr.next = pre;
            // 取高位
            ca = total / 10;
        }
        // 如果此时高位ca还存在
        if (ca != 0) {
            pre = curr;
            curr = new ListNode(ca);
            curr.next = pre;
        }
        return curr;

    }


    public static void main(String[] args) {

        ListNode a = new ListNode(9);
        ListNode b = new ListNode(6);
        ListNode c = new ListNode(7);

        ListNode d = new ListNode(9);
        ListNode e = new ListNode(3);
        ListNode f = new ListNode(7);

        a.next = b;
        b.next = c;

        d.next = e;
        e.next = f;

        _05_Add_LinkedList addLinkedList = new _05_Add_LinkedList();

        ListNode res = addLinkedList.addLinkedList2(a, d);

        System.out.println(res);


    }

}
