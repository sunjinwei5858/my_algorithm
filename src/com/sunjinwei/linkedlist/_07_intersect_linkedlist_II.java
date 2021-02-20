package com.sunjinwei.linkedlist;

import com.sunjinwei.domain.ListNode;

/**
 * 问题二：有环链表相交
 * 判断两个有环链表是否相交，相交则返回第一个相交节点，不相交则返回null。【both_loop】
 * 考虑这个问题的时候，我们已经得到了两个链表各自的第一个入环节点，假设链表1的第一个入环节点记为loopA，链表2的第一个入环节点记为loopB。
 */
public class _07_intersect_linkedlist_II {

    /**
     * 方法1：左神思路
     * 要找到有环链表相交，必须先找到各自进入环的第一个节点 也就是loopA,loopB 前面的题目已经作出解答
     * 有以下几种情况：
     * 1。 loop1 == loop2 ：说明共用一个环，是先相交 然后在进入环，相交的第一种情况
     * 2。 loop1 != loop2 && 链表相交 ：说明也是共用一个环 进入环的节点不一样，相交的第二种情况
     * 3。 loop1 != loop2 && 链表不相交 ：不相交 返回null
     *
     * @param headA 链表A的头节点
     * @param loop1 链表A进入环的第一个节点
     * @param headB 链表B的头节点
     * @param loop2 链表B进入环的第一个节点
     * @return
     */
    public ListNode bothLoop(ListNode headA, ListNode loop1, ListNode headB, ListNode loop2) {
        // 第一种情况：如果节点相等 说明共用一个环 两个链表是先相交 然后再进入环的，采取无环链表相交的做法
        if (loop1 == loop2) {
            ListNode p1 = headA;
            ListNode p2 = headB;
            // 让两个指针一直走 最后肯定会相遇的
            while (p1 != p2) {
                if (p1 == null) {
                    p1 = p2;
                } else {
                    p1 = p1.next;
                }
                if (p2 == null) {
                    p2 = p1;
                } else {
                    p2 = p2.next;
                }
            }
            return p1;
        } else {
            // 如果节点不相等 此时存在两种情况：
            // 第二种情况 是同一个环：那么一起遍历 直到相遇 此时返回
            // 第三种情况： 不是同一个环 那么就没有交点：如何识别是这种情况呢

            // 可以让loop1在环上遍历
            // 如果遇到了loop2 说明是在同一个环 此时返回loop1 或者loop2都可以
            // 如果一直没有遇到loop2 说明是两个不同的环
            ListNode curr = loop1.next;
            while (curr != loop1) {
                if (curr == loop2) {
                    return loop1;
                }
                curr = curr.next;
            }
            return null;
        }
    }


}
