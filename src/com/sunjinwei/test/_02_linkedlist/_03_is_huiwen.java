package com.sunjinwei.test._02_linkedlist;

import com.sunjinwei.domain.ListNode;

import java.util.Stack;

/**
 * 判断是否是回文链表
 * 普通解法：时间复杂度和空间复杂度都为O(n)
 * 进阶：时间复杂度O(n) 空间复杂度为O(1)
 */
public class _03_is_huiwen {

    /**
     * 普通解法1：使用栈将链表进行反转【将链表全部元素压入栈中】
     * <p>
     * 执行用时：16 ms, 在所有 Java 提交中击败了14.17%的用户
     * 内存消耗：50.8 MB, 在所有 Java 提交中击败了36.58%的用户
     *
     * @param head
     * @return
     */
    public boolean isHuiwen1(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        // 1将链表元素push到栈中
        Stack<ListNode> stack = new Stack<>();
        ListNode curr = head;
        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
        }
        // 2进行对比
        curr = head;
        while (!stack.isEmpty() && curr != null) {
            if (stack.peek().val != curr.val) {
                return false;
            }
            stack.pop();
            curr = curr.next;
        }
        if (!stack.isEmpty() || curr != null) {
            return false;
        }
        return true;
    }

    /**
     * 普通解法2：将链表的后半部分放入链表中
     * <p>
     * 执行用时：9 ms, 在所有 Java 提交中击败了46.37%的用户
     * 内存消耗：52.1 MB, 在所有 Java 提交中击败了18.67%的用户
     */
    public boolean isHuiwen2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 寻找链表中点【快慢指针】
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 前半部分
        ListNode n1 = head;
        // 后半部分
        ListNode n2 = null;
        // 判断奇偶性 这里保证了后半部分的长度小于等于前半部分的长度
        if (fast == null) {
            // 偶数
            n2 = slow;
        } else {
            // 奇数
            n2 = slow.next;
        }

        // 将后半部分的节点放入栈中
        Stack<ListNode> stack = new Stack<>();
        while (n2 != null) {
            stack.push(n2);
            n2 = n2.next;
        }

        // 进行比较
        while (!stack.isEmpty()) {
            if (stack.pop().val != n1.val) {
                return false;
            }
            n1 = n1.next;
        }
        return true;
    }


    /**
     * 进阶解法：寻找到中点 将链表的后半部分进行反转 然后和前半部分进行比较
     * <p>
     * 执行用时：4 ms, 在所有 Java 提交中击败了94.11%的用户
     * 内存消耗：47.9 MB, 在所有 Java 提交中击败了97.38%的用户
     *
     * @param head
     * @return
     */
    public boolean isHuiwen3(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 寻找链表中点【快慢指针】
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 前半部分
        ListNode n1 = head;
        // 后半部分
        ListNode n2 = null;
        // 判断奇偶性 这里保证了后半部分的长度小于等于前半部分的长度
        if (fast == null) {
            // 偶数
            n2 = slow;
        } else {
            // 奇数
            n2 = slow.next;
        }

        // 反转后半部分n2
        ListNode curr = n2;
        ListNode pre = null;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }

        // 比较前半部分和反转的
        n2 = pre;
        // 因为前半部分长度大于后半部分长度
        while (n2 != null) {
            if (n1.val != n2.val) {
                return false;
            }
            n1 = n1.next;
            n2 = n2.next;
        }

        // 还原后半部分
        curr = pre;
        pre = null;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }
        return true;
    }


    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(2);
        ListNode d = new ListNode(1);

        a.next = b;
        b.next = c;
        c.next = d;

        _03_is_huiwen huiwen = new _03_is_huiwen();
        boolean b1 = huiwen.isHuiwen3(a);

        while (a != null) {
            System.out.println("====" + a.val);
            a = a.next;
        }


        System.out.println(b1);

    }
}
