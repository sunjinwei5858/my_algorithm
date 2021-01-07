package com.sunjinwei.dp;

import com.sunjinwei.domain.ListNode;

import java.util.ArrayList;

/**
 * 判断链表是不是回文链表 力扣234 难度 简单
 * <p>
 * 进阶：
 * 你能否用时间复杂度O(n),空间复杂度O(1)解决此题？
 */
public class _04_huiwen_list_judge {

    /**
     * 错误方法1：将链表转成字符串 如果两个节点 都为负数 -1-1 反转之后回变为1-1-
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        StringBuffer buffer = new StringBuffer();
        while (head != null) {
            buffer.append(head.val);
            head = head.next;
        }
        String s = buffer.toString();
        // 反转
        StringBuffer reverse = buffer.reverse();
        return s.equals(reverse.toString());

    }

    /**
     * 错误方法2：将链表反转然后进行比较 因为单纯的反转改变了原来的节点 不是这样比较，反转后的链表是新链表
     *
     * @param head
     * @return
     */
    public boolean isPalindrome_02(ListNode head) {

        if (head == null) {
            return true;
        }
        // 1 反转链表
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            // 临时节点
            ListNode temp = curr.next;
            // 1当前节点的next指向前节点
            curr.next = prev;
            // 2前节点设置为当前节点
            prev = curr;
            // 3当前节点设置为临时节点
            curr = temp;
        }
        // 2 进行比较是否相等 此时的head已经不是之前的head了
        ListNode reverse = prev;
        if (reverse == null) {
            return true;
        }
        while (reverse != null && head != null) {
            if (reverse.val != head.val) {
                return false;
            }
            reverse = reverse.next;
            head = head.next;
        }
        return true;
    }

    /**
     * 正确方法3：将链表转为数组 类似左右指针
     *
     * @param head
     */
    public boolean isPalindrome_03(ListNode head) {

        if (head == null) {
            return true;
        }
        ArrayList<Integer> integers = new ArrayList<>();
        while (head != null) {
            integers.add(head.val);
            head = head.next;
        }
        for (int i = 0, j = integers.size() - 1; i < j; i++, j--) {
            if (!integers.get(i).equals(integers.get(j))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 正确方法4：将链表转为数组之后 使用左右指针
     *
     * @param head
     */
    public boolean isPalindrome_04(ListNode head) {

        if (head == null) {
            return true;
        }
        ArrayList<Integer> integers = new ArrayList<>();
        while (head != null) {
            integers.add(head.val);
            head = head.next;
        }
        int left = 0;
        int right = integers.size() - 1;
        while (left < right) {
            if (!integers.get(left).equals(integers.get(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    public static void main(String[] args) {
        ListNode listNode = new ListNode(-1);
        ListNode listNode02 = new ListNode(-1);
        //ListNode listNode03 = new ListNode(2);
        //ListNode listNode04 = new ListNode(1);
        listNode.next = listNode02;
        //listNode02.next = listNode03;
        //listNode03.next = listNode04;
        _04_huiwen_list_judge huiwen_list_judge = new _04_huiwen_list_judge();
        System.out.println(huiwen_list_judge.isPalindrome_03(listNode));
    }


}
