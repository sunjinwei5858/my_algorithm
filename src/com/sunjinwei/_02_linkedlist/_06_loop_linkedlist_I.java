package com.sunjinwei._02_linkedlist;

import com.sunjinwei.domain.ListNode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 问题一：判断是否有环，可以衍生两道题目：1返回boolean值 2返回进入环的第一个节点
 * 注意：环不一定是尾巴和头部相连 尾巴和中间节点相连也有可能
 * 进阶：你能用 O(1)（即，常量）内存解决此问题吗？
 */
public class _06_loop_linkedlist_I {

    /**
     * 力扣141 返回boolean值 难度：简单 自己的错误写法
     * <p>
     * 为什么？
     * 想当然的以为尾节点的next指向头节点 链表成环不一定是尾巴和头部相连 尾巴和中间节点相连也可能的
     */
    public boolean isLoop_01(ListNode node) {
        // 链表成环：没有尾节点 尾节点指向头节点
        ListNode curr = node;
        while (curr != null) {
            if (curr.next == node) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }


    /**
     * 正确解法1：使用hashmap或者hashset进行存储 想成判重
     * 时间和空间复杂度为O(n)
     * 执行用时：5 ms, 在所有 Java 提交中击败了8.01%的用户
     * 内存消耗：39.4 MB, 在所有 Java 提交中击败了8.88%的用户
     */
    public boolean isLoop_02(ListNode head) {
        Set<ListNode> hashset = new HashSet<>();
        ListNode curr = head;
        while (curr != null) {
            // 如果set添加不成功 说明有环 返回true
            if (!hashset.add(curr)) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }


    /**
     * 正确解法2：双指针法解决
     * 时间复杂度为O(n) 空间复杂度为O(1):只使用了两个指针的额外空间
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39.6 MB, 在所有 Java 提交中击败了6.19%的用户
     *
     * @param head
     */
    public boolean isLoop_03(ListNode head) {
        // 快慢指针都从head出发 官方题解的写法是慢指针从head开始 快指针从fast开始
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            // 注意：这里是比较节点 不是比较值 比较值 会有问题
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 正确解法3：双指针 官方题解的写法
     */
    public boolean isLoop_04(ListNode head) {
        // 1先进行鲁棒性的判断
        if (head == null || head.next == null) {
            return false;
        }
        // 2声明快慢指针
        ListNode slow = head;
        ListNode fast = head.next;
        // 3快慢指针开始移动
        while (slow != fast) {
            // 不成环的条件：1fast可以为null 2fast.next可以为null
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        // 走到这 说明成环
        return true;
    }


    public static void main(String[] args) {
        _06_loop_linkedlist_I linkedlistI = new _06_loop_linkedlist_I();
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        five.next = one;


        Boolean loop = linkedlistI.isLoop_02(one);


        List<Integer> integers = Arrays.asList(
                -21, 10, 17, 8, 4, 26, 5, 35, 33, -7, -16, 27, -12, 6, 29, -12, 5, 9, 20, 14, 14, 2, 13
        );

        Object[] objects = integers.toArray();

        System.out.println("============" + objects.length);

    }


}
