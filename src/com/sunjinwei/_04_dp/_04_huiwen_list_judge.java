package com.sunjinwei._04_dp;

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
     * 错误方法2：简单粗暴的直接将链表反转然后进行比较 因为单纯的反转改变了原来的节点 不是这样比较，反转后的链表是新链表
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
     * 正确方法1：将链表转为数组 for循环里面i和j
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
     * 正确方法2：将链表转为数组之后 使用左右指针
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

    /**
     * 进阶：
     * 正确写法3：用时间复杂度O(n),空间复杂度O(1)解决此题？反转一半
     * 1。使用双指针找到中间节点 快慢指针 只是改变链表的指针 所以空间复杂度为O(1)
     * 2。反转中间节点到末尾的链表部分
     * 3。前半部分和反转后的链表后半段部分比较
     * 4。复原链表的后半部分
     *
     * @param head
     */
    public boolean isPalindrome_05(ListNode head) {
        if (head == null) {
            return true;
        }
        // 1使用快慢指针找到链表的中间节点
        ListNode middleNode = middle(head);
        // 2中间节点后半部分进行反转
        ListNode left = reverse(middleNode);
        // 3进行比较
        while (head != null && left != null) {
            if (head.val != left.val) {
                return false;
            }
            head = head.next;
            left = left.next;
        }
        // 4如果没有单独去声明head和left 那么实现不了反转
        return true;
    }

    /**
     * 反转链表
     *
     * @param middleNode
     * @return
     */
    public ListNode reverse(ListNode middleNode) {
        // 2反转剩余的节点
        ListNode pre = null;
        ListNode curr = middleNode;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    /**
     * 找到链表的中间点，快慢指针
     * 用快慢指针，快指针有两步，慢指针走一步，快指针遇到终止位置时，慢指针就在链表中间位置!!!!!!
     *
     * @param node
     */
    public ListNode middle(ListNode node) {
        ListNode fast = node;
        ListNode slow = node;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public boolean isPalindrome_06(ListNode head) {
        if (head == null) {
            return true;
        }
        // 1使用快慢指针找到链表的中间节点
        ListNode middleNode = middle(head);
        // 2中间节点后半部分进行反转 [中间节点的next]
        ListNode left = reverse(middleNode.next);
        // 3进行比较
        boolean flag = true;
        ListNode p1 = head;
        ListNode p2 = left;
        while (p1 != null && p2 != null) {
            if (p1.val != p2.val) {
                flag = false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        // 4如果没有单独去声明head和left 那么实现不了反转
        ListNode p3 = reverse(left);
        middleNode.next = p3;

        while (head != null){
            System.out.println("========"+head.val);
            head = head.next;
        }


        return flag;
    }


    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode02 = new ListNode(2);
        ListNode listNode03 = new ListNode(3);
        ListNode listNode04 = new ListNode(4);
        //ListNode listNode05 = new ListNode(18);
        listNode.next = listNode02;
        listNode02.next = listNode03;
        listNode03.next = listNode04;
        //listNode04.next = listNode05;
        _04_huiwen_list_judge huiwen_list_judge = new _04_huiwen_list_judge();


        huiwen_list_judge.isPalindrome_06(listNode);


        /* ListNode reverse = huiwen_list_judge.reverse(listNode);
         *//*while (reverse != null) {
            System.out.println(reverse.val);
            reverse = reverse.next;
        }*//*
        System.out.println("==========");
        ListNode node = huiwen_list_judge.reverse(reverse);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
*/

    }


}
