package com.sunjinwei.linkedlist;

import com.sunjinwei.domain.ListNode;

/**
 * 终极题目：
 * 在本题中，单链表可能有环，也可能无环。给定两个单链表的头节点 head1 和head2，这两个链表可能相交，也可能不相交。
 * 请实现一个函数，如果两个链表相交，请返回相交的第一个节点；如果不相交，返回null即可。
 * 要求：如果链表1的长度为N，链表2的长度为M，时间复杂度请达到O（N+M），额外空间复杂度请达到O（1）。
 * 难度：困难
 * 拆成的三个子问题都可以单独做一个道算法题 综合起来的话 是比较难的 不知道怎么拆分
 */
public class _07_intersect_linkedlist_III {

    /**
     * 分析：要相交：只能是：无环+无环，有环+有环，这两种形式，不能是无环+有环这种情况
     * 明确了以上的情况，可以先画图 直观感受
     * 也就是拆成了三个子问题：
     * 问题1：如何判断链表有环 返回进入环的第一个节点 【_06_loop_linkedlist_I和_06_loop_linkedlist_II已经作出解答】
     * 问题2：两个无环链表 如何判断相交 返回相交的第一个节点【_07_intersect_linkedlist_I已经作出解答】
     * 问题3：两个有环链表 如何判断相交 返回相交的第一个节点【_07_intersect_linkedlist_II已经作出解答】
     *
     * @param headA 链表A的头节点
     * @param headB 链表B的头节点
     * @return
     */
    public ListNode bothLoop(ListNode headA, ListNode headB) {
        // 问题1：判断是否有环
        _06_loop_linkedlist_II isLoop = new _06_loop_linkedlist_II();
        ListNode loop1 = isLoop.detectCycle_02(headA);
        ListNode loop2 = isLoop.detectCycle_02(headB);
        // 鲁棒性：无环+有环 需要排除
        if (loop1 != null && loop2 == null) {
            return null;
        }
        if (loop1 == null && loop2 != null) {
            return null;
        }
        // 问题2：两个都是无环
        // 问题3：两个都是有环
        if (loop1 == null && loop2 == null) {
            _07_intersect_linkedlist_I noLoop = new _07_intersect_linkedlist_I();
            return noLoop.getIntersectionNode(headA, headB);
        } else {
            _07_intersect_linkedlist_II bothLoop = new _07_intersect_linkedlist_II();
            return bothLoop.bothLoop(headA, loop1, headB, loop2);
        }
    }


}
