package com.sunjinwei._02_linkedlist;

import com.sunjinwei.domain.ListNode;

import java.util.Stack;

/**
 * 问题一：无环链表相交 力扣：160 剑指offer：52 两个链表的第一个公共结点 难度:简单
 * <p>
 * 如何判断两个无环链表是否相交，相交则返回第一个相交节点，不相交则返回null。【no_loop】
 * <p>
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 *
 * 目前有三种方法：
 * 方法1：先计算出链表的长度 谁更长 谁先走
 * 方法2：双指针 谁先到末尾 然后从另一个链表开始走
 * 方法3：使用栈 利用栈先进后出 将两个链表分别入栈 然后弹栈 思路就是从尾巴节点开始遍历
 * <p>
 * 注意：但是这里要注意，如果题目只是说找两个链表的第一个公共节点，
 * 这里是要提前说明 这两个链表是不是单链表 是不是无环
 * 因为有环的解法和无环的解法是不一样的
 */
public class _07_intersect_linkedlist_I {

    /**
     * 方法1：
     * 1。先判断会不会相交，如果相交 那么最后一个节点都是相等的
     * 2。利用第一步的判断 可以知道两个链表的长度，然后进行相减
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：41.6 MB, 在所有 Java 提交中击败了13.99%的用户
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode01(ListNode headA, ListNode headB) {
        // 鲁棒性1
        if (headA == null || headB == null) {
            return null;
        }
        // 鲁棒性2
        if (headA == headB) {
            return headA;
        }
        // 1判断是否是相交
        // 分析：只需要判断各自最后一个节点是不是相等 如果是 说明是相交的 否则 不是
        // 1.1遍历第一个链表
        int len1 = 0;
        ListNode end1 = headA;
        while (end1.next != null) {
            len1++;
            end1 = end1.next;
        }
        int len2 = 0;
        ListNode end2 = headB;
        while (end2.next != null) {
            len2++;
            end2 = end2.next;
        }
        // 说明不相交 返回null即可
        if (end1 != end2) {
            return null;
        }
        // 2再找相交的节点
        // 分析：比较长度 如果len1更大 那么两个链表从头开始走的话 链表1需要先走len1-len2步
        ListNode node1 = headA;
        ListNode node2 = headB;
        int k = len1 - len2;
        if (k > 0) {
            // len1更大 node1先走k步
            for (int i = 0; i < k; i++) {
                node1 = node1.next;
            }
            // 注意：这里要取= 不然就要next前写if判断 next后也要进行判断
            for (int i = 0; i <= len2; i++) {
                if (node1 == node2) {
                    return node1;
                }
                node1 = node1.next;
                node2 = node2.next;
            }
        } else if (k < 0) {
            // len2更大 node2先走k步
            for (int i = 0; i < k; i++) {
                node2 = node2.next;
            }
            // 注意：这里要取= 不然就要next前写if判断 next后也要进行判断
            for (int i = 0; i <= len1; i++) {
                if (node1 == node2) {
                    return node1;
                }
                node1 = node1.next;
                node2 = node2.next;
            }
        } else {
            // 注意：这里要取= 不然就要next前写if判断 next后也要进行判断
            for (int i = 0; i <= len1; i++) {
                if (node1 == node2) {
                    return node1;
                }
                node1 = node1.next;
                node2 = node2.next;
            }
        }
        return null;
    }

    /**
     * 方法2 对上面的代码进行优化
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：41.1 MB, 在所有 Java 提交中击败了80.11%的用户
     * <p>
     * 时间复杂度：都对headA和headB进行了遍历，所以为O(n)
     * 空间复杂度：申请了三个变量：长度 尾节点A 尾节点B 所以空间复杂度为O(1)
     *
     * @param headA
     * @param headB
     */
    public ListNode getIntersectionNode02(ListNode headA, ListNode headB) {
        // 鲁棒性1: 提前判空
        if (headA == null || headB == null) {
            return null;
        }
        // 1 判断是否相交
        // 思路：判断两个链表最后一个节点是不是相等 并且声明长度 只声明一个长度变量 不需要各自一个
        int len = 0;
        ListNode lastA = headA;
        ListNode lastB = headB;
        while (lastA.next != null) {
            len++;
            lastA = lastA.next;
        }
        while (lastB.next != null) {
            len--;
            lastB = lastB.next;
        }
        // 2
        if (lastA != lastB) {
            return null;
        }
        // 3 找出相交的第一个节点
        lastA = headA;
        lastB = headB;
        if (len > 0) {
            // 说明链表A更长
            while (len > 0) {
                len--;
                lastA = lastA.next;
            }
        } else if (len < 0) {
            // 说明链表B更长
            while (len < 0) {
                len++;
                lastB = lastB.next;
            }
        }
        // 开始一起遍历 判断节点是否相等 左神的写法 更加简洁
        while (lastA != lastB) {
            lastA = lastA.next;
            lastB = lastB.next;
        }
        return lastA;
    }


    /**
     * 方法3：双指针
     * 但是如果不先判断有没有相交的话 默认相交的话 会一直陷入死循环中 所以更加完善的做法还是需要先提前判断是不是会相交
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：41.5 MB, 在所有 Java 提交中击败了17.24%的用户
     * <p>
     * 通过画图：
     * 假设链表a的长度为a，链表b的长度为b，链表a走了c步到达了第一个相交的节点，链表b走了d步到达了第一个相交的节点
     * 此时有公式 b-d = a-c  转换则 a+d = b+c
     * 说明：链表a走完了，再走链表b；同理链表b走完了，再走链表a，那么肯定是会相遇的
     *
     * @param headA
     * @param headB
     */
    public ListNode getIntersectionNode03(ListNode headA, ListNode headB) {
        // 鲁棒性1: 提前判空
        if (headA == null || headB == null) {
            return null;
        }
        // 鲁棒性2：必须先判断是不是相交 如果不相交 该程序是要崩溃的
        ListNode node1 = headA;
        ListNode node2 = headB;
        while (node1.next != null) {
            node1 = node1.next;
        }
        while (node2.next != null) {
            node2 = node2.next;
        }
        if (node1 != node2) {
            return null;
        }
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != p2) {
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }
        return p1;
    }

    /**
     * 方法4：使用栈
     * 使用辅助栈的解法：空间复杂度为O(m+n), 利用栈的先进后出，栈顶就是链表的尾巴节点，当然这里的前提是：链表为无环链表才可以使用这个方法
     * <p>
     * 执行用时：3 ms, 在所有 Java 提交中击败了15.63%的用户
     * 内存消耗：41.5 MB, 在所有 Java 提交中击败了18.02%的用户
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode04(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        Stack<ListNode> stackA = new Stack<>();
        Stack<ListNode> stackB = new Stack<>();
        ListNode a = headA;
        ListNode b = headB;
        while (a != null) {
            stackA.push(a);
            a = a.next;
        }
        while (b != null) {
            stackB.push(b);
            b = b.next;
        }
        ListNode result = null;
        while (!stackA.isEmpty() && !stackB.isEmpty() && (stackA.peek() == stackB.peek())) {
            stackA.pop();
            result = stackB.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        _07_intersect_linkedlist_I linkedlist_iii = new _07_intersect_linkedlist_I();
        ListNode three = new ListNode(3);
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode four = new ListNode(1);
        ListNode five = new ListNode(2);
        // 第一个链表
        three.next = one;
        one.next = two;
        // 第二个链表
        four.next = two;

        ListNode intersectionNode = linkedlist_iii.getIntersectionNode01(one, four);
        System.out.println(intersectionNode.val);

    }


}
