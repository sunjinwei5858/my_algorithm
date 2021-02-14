package com.sunjinwei.linkedlist;

import com.sunjinwei.domain.ListNode;

/**
 * 反转部分单链表II, 力扣92，难度：中等
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * 说明:1 ≤ m ≤ n ≤ 链表长度。
 * 如果链表长度为N，时间复杂度要求为O（N），额外空间复杂度要求为O（1）。
 * <p>
 * 链表题目做多了都一个套路无非是解链，反转。这题是把一个单链表解开成为三个链表。把中间的链表反转然后依次拼接。
 */
public class _04_reverse_linkedlist_III {

    /**
     * 方法1：使用左神的思路，直接拆，要么是两段 要么是三段
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：35.9 MB, 在所有 Java 提交中击败了80.32%的用户
     *
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // 1 找分界点 m-1 m-n n+1
        int length = 0;
        ListNode pre = null;
        ListNode post = null;
        ListNode temp1 = head;
        while (temp1 != null) {
            length++;
            if (length == m - 1) {
                pre = temp1;
            }
            if (length == n + 1) {
                post = temp1;
            }
            temp1 = temp1.next;
        }
        // 2 鲁棒性
        if (m > n || m < 1 || n > length) {
            return null;
        }
        // 3 反转
        // 3.1找到反转的开始节点 注意：需要判断m==1
        if (pre == null) {
            temp1 = head;
        } else {
            temp1 = pre.next;
        }
        ListNode curr = temp1;
        ListNode preNode = null;
        while (curr != post) {
            ListNode temp = curr.next;
            curr.next = preNode;
            preNode = curr;
            curr = temp;
        }
        // 4 开始拼接
        // 分情况讨论：
        // 如果m=1 那么只有两段 只需要拼接一次；
        // 如果m!=1 那么有三段 需要拼接两次

        // 拼接1：temp1指向post
        temp1.next = post;
        if (pre == null) {
            // m=1:只需要拼接temp1和post
            return preNode;
        }
        // m!=1:第一段和第二段的拼接,此时的preNode就是反转之后的头节点 将pre的next指向preNode
        pre.next = preNode;
        return head;
    }

    /**
     * 方法2：两个for循环
     * 注意：需要判断m==1
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.1 MB, 在所有 Java 提交中击败了29.31%的用户
     *
     * @param m
     * @param n
     */
    public ListNode reverseBetween_02(ListNode head, int m, int n) {
        if (m == 1) {
            ListNode pre = null;
            ListNode cur = head;
            for (int i = m; i <= n; i++) {
                ListNode temp = cur.next;
                cur.next = pre;
                pre = cur;
                cur = temp;
            }
            head.next = cur;
            return pre;
        }
        // 找到第m-1个节点
        ListNode pre = head;
        for (int i = 1; i < m - 1; i++) {
            pre = pre.next;
        }
        // 反转
        ListNode preNode = null;
        ListNode curr = pre.next;
        for (int i = m; i <= n; i++) {
            ListNode temp = curr.next;
            curr.next = preNode;
            preNode = curr;
            curr = temp;
        }
        // 此时的cur就是第n+1个节点
        // 拼接
        // 拼接1：第m个节点的next指向第n+1个节点
        pre.next.next = curr;
        // 拼接2：第m-1个节点的next指向反转后的第一个节点 也就是preNode
        pre.next = preNode;
        return head;
    }

    /**
     * 两个for循环的优化写法：使用哨兵节点，代替m==1的处理
     *
     * @param m
     * @param n
     */
    public ListNode reverseBetween_03(ListNode head, int m, int n) {
        // 声明哨兵节点 不需要处理m=1的情况了
        ListNode shaobing = new ListNode(-1);
        shaobing.next = head;
        ListNode pre = shaobing;
        for (int i = 1; i < m - 1; i++) {
            pre = pre.next;
        }
        ListNode preNode = null;
        ListNode curr = pre.next;
        for (int i = m; i <= n; i++) {
            ListNode temp = curr.next;
            curr.next = preNode;
            preNode = curr;
            curr = temp;
        }
        // 拼接
        pre.next.next = curr;
        pre.next = preNode;
        return shaobing.next;
    }


    public static void main(String[] args) {
        _04_reverse_linkedlist_III linkedlist_iii = new _04_reverse_linkedlist_III();
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);
        ListNode fifth = new ListNode(5);
        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;

        ListNode reverseBetween = linkedlist_iii.reverseBetween_03(head, 2, 4);
        while (reverseBetween != null) {
            System.out.println(reverseBetween.val);
            reverseBetween = reverseBetween.next;
        }

    }


}
