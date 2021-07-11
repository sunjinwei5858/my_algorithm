package com.sunjinwei._02_linkedlist02;

import com.sunjinwei.domain.ListNode;

import java.util.HashSet;

/**
 * 删除有序链表中值重复出现的节点【力扣82】
 * 变种：请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现的数字。
 * 【1，2，3，3，4，4，5】变成【1，2，5】
 */
public class _11_Remove_Sort_Repeat_Node_II {

    /**
     * 方法1: 哈希表
     * 执行用时：1 ms, 在所有 Java 提交中击败了73.17%的用户
     * 内存消耗：37.9 MB, 在所有 Java 提交中击败了28.48%的用户
     *
     * @param head
     * @return
     */
    public ListNode removeRepeat(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(head.val);
        ListNode curr = head.next;

        while (curr != null) {
            if (!hashSet.add(curr.val)) {
                ListNode preNode = getPreNode(head, curr);
                if (preNode == null) {
                    // 说明是头节点 需要更新头节点
                    if (curr.next != null) {
                        head = curr.next;
                        // 更新curr 这里不能将curr更新为head的next 因为 我这里是使用hashset.add()方法来处理的
                        curr = head;
                    } else {
                        head = null;
                        curr = null;
                    }
                } else {
                    // 删除和curr相同值的节点 更新preNode指向
                    preNode.next = curr.next;
                    // 更新curr
                    curr = preNode.next;
                }
            } else {
                // 更新curr
                curr = curr.next;
            }
        }
        return head;
    }

    /**
     * 寻找不等于target节点值的前一个节点
     *
     * @param head
     * @param target
     * @return
     */
    private ListNode getPreNode(ListNode head, ListNode target) {
        ListNode curr = head;
        ListNode pre = null;

        while (curr != null) {
            if (curr.val == target.val) {
                return pre;
            }
            pre = curr;
            curr = curr.next;
        }
        return null;
    }


    public static void main(String[] args) {
        ListNode a = new ListNode(4);
        ListNode b = new ListNode(4);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(3);
        ListNode e = new ListNode(6);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;

        _11_Remove_Sort_Repeat_Node_II removeRepeatNode = new _11_Remove_Sort_Repeat_Node_II();

        //System.out.println("===" + removeRepeatNode.getPreNode(a, d).val);

        ListNode res = removeRepeatNode.removeRepeat(a);

        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }


}
