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
     * 方法1: 哈希表 该方法并没有考虑已经排好序 所以使用了哈希表
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


    /**
     * 方法2：将有序考虑进去 只需要一次遍历即可 O(1)空间和O(n)时间
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.8 MB, 在所有 Java 提交中击败了58.42%的用户
     *
     * @param head
     */
    public ListNode removeRepeat2(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        // 记录不和curr值相等的节点
        ListNode preNode = null;
        // 记录上一个节点
        ListNode pre = head;
        // 当前遍历的节点
        ListNode curr = pre.next;

        while (curr != null) {
            // 没有重复的情况
            if (pre.val != curr.val) {
                preNode = pre;
                pre = curr;
                curr = curr.next;
                continue;
            }
            // 有重复的情况
            // 此时需要删除重复的节点
            if (preNode == null) {
                // 说明头节点 pre curr 都要删除
                if (curr.next == null) {
                    return null;
                }
                // 查找curr后面还有和curr值相同的节点
                ListNode sameNextNode = getSameNextNode(curr);
                // 删除 pre curr curr后面值相同的节点
                head = sameNextNode.next;
                pre = head;
                // 为null 说明走到了尽头
                if (pre == null) {
                    break;
                }
                curr = pre.next;
                continue;
            }
            // 查找curr后面还有和curr值相同的节点
            ListNode sameNextNode = getSameNextNode(curr);
            // 删除pre curr curr后面值相同的节点
            preNode.next = sameNextNode.next;
            pre = preNode.next;
            // 为null 说明走到了尽头
            if (pre == null) {
                break;
            }
            curr = pre.next;
        }
        return head;
    }

    /**
     * 有序链表的情况下：获取相同值的最后一个节点
     *
     * @param head
     * @return
     */
    private ListNode getSameNextNode(ListNode head) {

        while (head.next != null && head.val == head.next.val) {
            head = head.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(1);
        ListNode c = new ListNode(1);
        ListNode d = new ListNode(1);
        ListNode e = new ListNode(1);
        ListNode f = new ListNode(1);
        ListNode g = new ListNode(1);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;

        _11_Remove_Sort_Repeat_Node_II removeRepeatNode = new _11_Remove_Sort_Repeat_Node_II();

        //System.out.println("===" + removeRepeatNode.getPreNode(a, d).val);

        ListNode res = removeRepeatNode.removeRepeat2(a);

        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }


}
