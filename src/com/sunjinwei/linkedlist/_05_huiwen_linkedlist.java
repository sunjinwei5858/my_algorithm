package com.sunjinwei.linkedlist;

import com.sunjinwei.domain.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 回文链表 234 难度 简单
 * 进阶：能否用O(n)时间复杂度和O(1)空间复杂度？
 */
public class _05_huiwen_linkedlist {

    /**
     * 方法1：先找到链表的中点，然后判断前半部分和后半部分是不是相同 【进阶的做法 时间复杂度为O(n)，空间复杂度为O(1)】
     * 该方法破坏了链表结构
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
        // 快慢指针怎么判断链表个数的奇偶
        // 判断链表节点个数是奇数还是偶数
        // 如果fast此时为null 说明是偶数；
        // 如果fast不为null 那么就fast.next为null 说明是奇数 此时的slow为中点位置
        if (fast != null) {
            slow = slow.next;
        }
        ListNode second = reverse(slow);
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

    /**
     * 方法3：将链表遍历压入栈中 时间复杂度为O(n),空间复杂度为O(n) 因为新建了栈的结构并且将所有节点都压入了栈中
     * <p>
     * 执行用时：2 ms, 在所有 Java 提交中击败了59.58%的用户
     * 内存消耗：41.7 MB, 在所有 Java 提交中击败了50.34%的用户
     *
     * @param head
     */
    public boolean isPalindrome_03(ListNode head) {
        // 遍历链表
        ListNode curr = head;
        Stack<ListNode> stack = new Stack<>();
        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
        }
        // 比较链表和栈中弹出的元素
        curr = head;
        while (curr != null) {
            if (curr.val != stack.pop().val) {
                return false;
            }
            curr = curr.next;
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
