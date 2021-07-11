package com.sunjinwei._02_linkedlist02;

import com.sunjinwei.domain.ListNode;

import java.util.HashSet;

/**
 * 删除无序链表中值重复出现的节点 【左神】
 * 注意：是无序
 */
public class _11_Remove_NoSort_Repeat_Node {

    /**
     * 方法1：使用哈希表，时间复杂度O(n) 空间复杂度为O(n)
     *
     * @param head
     */
    public void removeRepeat1(ListNode head) {

        if (head == null || head.next == null) {
            return;
        }
        HashSet<Integer> hashSet = new HashSet<>();
        ListNode pre = head;
        ListNode curr = head;
        while (curr != null) {
            if (!hashSet.add(curr.val)) {
                // 进行删除 pre直接指向curr的next 达到删除curr的效果
                pre.next = curr.next;
                // bug 此时只更新curr 是pre的next
                curr = pre.next;
                continue;
            }
            // 更新pre和curr
            pre = curr;
            curr = curr.next;
        }
    }

    /**
     * 方法2：类似选择排序 时间复杂度为O(n2) 空间复杂度为O(1)
     *
     * @param head
     */
    public void removeRepeat2(ListNode head) {

        if (head == null || head.next == null) {
            return;
        }
        ListNode curr = head;
        ListNode next = null;
        ListNode pre = null;
        while (curr != null) {
            next = curr.next;
            pre = curr;
            // 类似选择排序 寻找和curr相同值的节点
            while (next != null) {
                if (next.val == curr.val) {
                    // 进行删除
                    pre.next = next.next;
                    // 只需要更新next即可
                    next = pre.next;
                    continue;
                }
                // 更新pre和next
                pre = next;
                next = next.next;
            }
            curr = curr.next;
        }
    }

    /**
     * 方法2：类似选择排序 时间复杂度为O(n2) 空间复杂度为O(1)【左神写法不同而已】
     *
     * @param head
     */
    public void removeRepeat3(ListNode head) {

        if (head == null || head.next == null) {
            return;
        }
        ListNode curr = head;
        ListNode next = null;
        ListNode pre = null;
        while (curr != null) {
            next = curr.next;
            pre = curr;
            while (next != null) {
                if (next.val != curr.val) {
                    // 如果不相等 那么更新pre
                    pre = next;
                } else {
                    // 如果相等 进行去除相同的节点 更新pre.next即可
                    pre.next = next.next;
                }
                next = next.next;
            }
            curr = curr.next;
        }
    }


    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(1);
        ListNode c = new ListNode(1);
        ListNode d = new ListNode(1);
        ListNode e = new ListNode(1);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        _11_Remove_NoSort_Repeat_Node removeRepeatNode = new _11_Remove_NoSort_Repeat_Node();
        removeRepeatNode.removeRepeat1(a);

        while (a != null) {
            System.out.println(a.val);
            a = a.next;
        }
    }


}
