package com.sunjinwei.linkedlist;

import com.sunjinwei.domain.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 回文链表 234 难度 简单
 * 进阶：能否用O(n)时间复杂度和O(1)空间复杂度？
 */
public class _05_huiwen_linkedlist {

    /**
     * 方法1：先找到链表的中点，然后判断前半部分和后半部分是不是相同 【进阶的做法 时间复杂度为O(n)，空间复杂度为O(1)】
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.96%的用户
     * 内存消耗：41 MB, 在所有 Java 提交中击败了81.10%的用户
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        // 1使用快慢指针寻找链表的中点
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            // 快指针走两步 慢指针走两步
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode first = head;
        ListNode second = null;

        second = reverse(slow);
        while (first != null && second != null) {
            if (first.val != second.val) {
                return false;
            }
            first = first.next;
            second = second.next;
        }

        return true;
    }

    /**
     * 链表反转的方法
     *
     * @param node
     */
    private ListNode reverse(ListNode node) {
        ListNode pre = null;
        ListNode curr = node;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }
        return pre;
    }

    /**
     * 方法2：将链表的值转换为数组，进行判断 【时间复杂度和空间复杂度都为O(n)】
     * <p>
     * 执行用时：4 ms, 在所有 Java 提交中击败了28.15%的用户
     * 内存消耗：42.2 MB, 在所有 Java 提交中击败了34.16%的用户
     *
     * @param head
     */
    public boolean isPalindrome_02(ListNode head) {
        // 新建了集合
        List<Integer> list = new ArrayList<Integer>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        // 新建了数组 其实不需要转成数组 因为ArrayList的元素顺序就是添加的顺序
        //Integer[] array = list.toArray(new Integer[list.size()]);
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            if (!list.get(left).equals(list.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        _05_huiwen_linkedlist huiwenLinkedlist = new _05_huiwen_linkedlist();
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(2);
        ListNode fourth = new ListNode(1);
        ListNode fifth = new ListNode(2);
        head.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = fifth;

        boolean palindrome = huiwenLinkedlist.isPalindrome(head);
        System.out.println(palindrome);

        while (head != null) {
            System.out.println("head == " + head.val);
            head = head.next;
        }

    }

}
