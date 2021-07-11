package com.sunjinwei._02_linkedlist02;

import com.sunjinwei.domain.ListNode;

import java.util.HashSet;

/**
 * 删除有序链表中值重复出现的节点【力扣83】
 * 注意：有序
 */
public class _11_Remove_Sort_Repeat_Node {

    /**
     * 方法1：使用哈希表，时间复杂度O(n) 空间复杂度为O(n)
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了35.73%的用户
     * 内存消耗：37.8 MB, 在所有 Java 提交中击败了67.84%的用户
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
                // 进行调整
                pre.next = curr.next;
                // bug 此时更新curr 是pre的next
                curr = pre.next;
                continue;
            }
            // 此时更新pre和curr
            pre = curr;
            curr = curr.next;

        }
    }

    /**
     * 方法2：双指针 快慢指针 快指针先走一步 因为是排序的 所以如果快指针的值和慢指针相等 那么说明快指针是重复的节点 进行删除
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了20.11%的用户
     *
     * @param head
     */
    public void removeRepeat2(ListNode head) {

        if (head == null || head.next == null) {
            return;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null) {
            if (fast.val == slow.val) {
                // 处理：因为排好序 所以slow指向fast的next 达到删除fast
                slow.next = fast.next;
                // 只需要更新fast即可
                fast = slow.next;
                continue;
            }
            // 更新slow和fast
            slow = slow.next;
            fast = fast.next;
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

        _11_Remove_Sort_Repeat_Node removeRepeatNode = new _11_Remove_Sort_Repeat_Node();
        removeRepeatNode.removeRepeat2(a);

        while (a != null) {
            System.out.println(a.val);
            a = a.next;
        }
    }


}
