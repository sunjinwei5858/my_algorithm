package com.sunjinwei.linkedlist;

import com.sunjinwei.domain.ListNode;

/**
 * 反转链表前N个节点
 * 和反转整个链表的区别：
 * 1。base case 变为n==1，反转一个元素，就是它本身
 * 2。要记录后驱节点
 */
public class _04_reverse_linkedlist_II {

    /**
     * 方法1：使用递归 时间复杂度和空间复杂度都是O(n)
     * 1。函数定义：返回反转后的头节点
     * 2。base case
     * 3。记录后驱节点
     *
     * @param head
     * @param n
     * @return
     */
    private ListNode successor;

    public ListNode reverseN(ListNode head, int n) {
        // 1 base case n=1 反转一个元素 就是自己本身
        if (n == 1) {
            successor = head.next;
            return head;
        }
        // 2 递归 输入n-1
        ListNode last = reverseN(head.next, n - 1);
        // 3 反转的处理
        head.next.next = head;
        // 4 第n个节点的next指针指向后驱节点!!!
        head.next = successor;
        return last;
    }


    /**
     * 方法2：使用迭代，左神写法和for循环写法
     *
     * @param head
     */
    public ListNode reverseN_02(ListNode head, int n) {
        // 鲁棒性1
        if (head == null || n < 1) {
            return head;
        }
        // 1找到第n+1个节点
        int length = 0;
        ListNode post = null;
        ListNode temp = head;
        while (temp != null) {
            length++;
            if (length == n + 1) {
                post = temp;
            }
            temp = temp.next;
        }
        // 鲁棒性2
        if (n > length) {
            return null;
        }

        // 2开始反转
        ListNode pre = null;
        ListNode curr = head;
        while (curr != post) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        // 3拼接
        head.next = post;
        return pre;
    }

    /**
     * 方法3：for循环写法
     *
     * @param head
     */
    public ListNode reverseN_03(ListNode head, int n) {
        // 鲁棒性
        if (head == null || n < 1) {
            return head;
        }
        // 找到第n+1个节点
        ListNode post = head;
        for (int i = 1; i < n + 1; i++) {
            post = post.next;
        }
        // 反转
        ListNode pre = null;
        ListNode cur = head;
        for (int i = 1; i <= n; i++) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        // 拼接
        head.next = post;
        return pre;
    }


    public static void main(String[] args) {
        _04_reverse_linkedlist_II linkedlist_ii = new _04_reverse_linkedlist_II();
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);
        head.next = second;
        second.next = third;
        third.next = fourth;

        ListNode reverseN = linkedlist_ii.reverseN_03(head, 3);
        while (reverseN != null) {
            System.out.println(reverseN.val);
            reverseN = reverseN.next;
        }
    }


}
