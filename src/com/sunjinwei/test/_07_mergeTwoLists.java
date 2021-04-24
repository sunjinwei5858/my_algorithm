package com.sunjinwei.test;

import com.sunjinwei.domain.ListNode;

/**
 * 21. 合并两个有序链表
 */
public class _07_mergeTwoLists {

    /**
     * 递归处理
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 1鲁棒性
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        // 2声明结果
        ListNode res = null;
        // 3进行处理
        if (l1.val < l2.val) {
            res = l1;
            l1 = l1.next;
        } else {
            res = l2;
            l2 = l2.next;
        }
        // 4递归处理
        res.next = mergeTwoLists(l1, l2);
        return res;
    }

    /**
     * 迭代处理，思路和合并两个有序数组的思想一样 归并排序就用到了合并两个有序数组
     * 哨兵节点的场景1
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        // 1鲁棒性
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        // 2使用哨兵节点
        ListNode shaobing = new ListNode(0);
        // 3初始化curr节点为哨兵节点
        ListNode curr = shaobing;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        if (l1 != null) {
            curr.next = l1;
        }
        if (l2 != null) {
            curr.next = l2;
        }
        return shaobing.next;
    }


}
