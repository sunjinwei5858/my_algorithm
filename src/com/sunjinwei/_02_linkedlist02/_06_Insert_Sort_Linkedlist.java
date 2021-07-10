package com.sunjinwei._02_linkedlist02;

import com.sunjinwei.domain.ListNode;

/**
 * @program: com.sunjinwei._02_linkedlist02
 * @author: sun jinwei
 * @create: 2021-07-08 22:36
 * @description: 链表的插入排序
 **/
public class _06_Insert_Sort_Linkedlist {

    /**
     * 插入排序思想：
     * 假设前面都是排序好的链表 一直往前找
     * 插入排序的基本思想是，维护一个有序序列，初始时有序序列只有一个元素，每次将一个新的元素插入到有序序列中，
     * 将有序序列的长度增加1，直到全部元素都加入到有序序列中。
     * <p>
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.11%的用户
     * 内存消耗：37.9 MB, 在所有 Java 提交中击败了87.64%的用户
     *
     * @param head
     * @return
     */
    public ListNode insertSort(ListNode head) {

        // 鲁棒性：链表为空或者只有一个节点 不需要排序
        if (head == null || head.next == null) {
            return head;
        }
        // 把第一个节点 也就是头节点 当成是有序的
        ListNode newHead = head;
        // 排好序的尾巴节点
        ListNode tail = head;
        // 当前遍历的节点 [newHead,tail]是排好序的 [tail.next, ...]是没有排好序的
        ListNode curr = tail.next;
        while (curr != null) {
            // 情况1：不需要处理 直接更新尾巴节点
            if (tail.val <= curr.val) {
                tail = curr;
            } else {
                ListNode pre = newHead;
                // 情况2：直接当成头节点 更新头节点 【这一步可以优化 可以使用哨兵节点】
                if (pre.val > curr.val) {
                    tail.next = curr.next;
                    curr.next = pre;
                    newHead = curr;
                } else {
                    // 情况3：在头节点和尾巴节点之间 寻找位置
                    while (pre.next.val <= curr.val) {
                        pre = pre.next;
                    }
                    // 处理：将curr插入到pre和pre.next之间
                    // 尾巴节点的next指向curr的next
                    tail.next = curr.next;
                    // curr的next指向pre的next
                    curr.next = pre.next;
                    // pre的next指向curr
                    pre.next = curr;
                }
            }
            // 更新curr
            curr = tail.next;
        }
        return newHead;
    }

    /**
     * 第二种:使用哨兵节点，优化第一种解法的第二种情况 将第二种情况和第三种情况进行整合了【官方题解的做法】
     * <p>
     * 执行用时：2 ms, 在所有 Java 提交中击败了99.11%的用户
     * 内存消耗：38.3 MB, 在所有 Java 提交中击败了13.69%的用户
     *
     * @param head
     */
    public ListNode insertSort2(ListNode head) {

        // 鲁棒性：链表为空或者只有一个节点 不需要排序
        if (head == null || head.next == null) {
            return head;
        }

        // 创建哨兵节点
        ListNode shaobing = new ListNode(-1);
        shaobing.next = head;
        // 设置排好序的尾巴节点
        ListNode tail = shaobing.next;
        // 从head.next开始遍历
        ListNode curr = head.next;

        while (curr != null) {
            if (tail.val <= curr.val) {
                // 因为是原地调整
                // tail = curr // 也是可以的
                tail = tail.next;
            } else {
                ListNode begin = shaobing;
                while (begin.next.val <= curr.val) {
                    begin = begin.next;
                }
                // 调整节点指向
                tail.next = curr.next;
                curr.next = begin.next;
                begin.next = curr;
            }
            // 更新curr
            curr = tail.next;
        }
        return shaobing.next;
    }


    /**
     * 第三种：对第一种方法使用哨兵节点 不记录排好序的尾巴节点，每次都从头开始遍历一遍，所以时间复杂度高【代码随想录的解法】
     * <p>
     * 执行用时：25 ms, 在所有 Java 提交中击败了24.91%的用户
     * 内存消耗：37.6 MB, 在所有 Java 提交中击败了98.68%的用户
     *
     * @param head
     */
    public ListNode insertSort3(ListNode head) {
        // 鲁棒性：链表为空或者只有一个节点 不需要排序
        if (head == null || head.next == null) {
            return head;
        }
        // 使用哨兵节点 但是哨兵节点初始化不指向头节点 这样是为了方便插入比头节点还小的值
        ListNode shaobing = new ListNode(0);
        ListNode curr = head;
        ListNode pre = shaobing;
        while (curr != null) {
            // 如果curr比pre.next的值更大 那么继续寻找
            while (pre.next != null && pre.next.val <= curr.val) {
                pre = pre.next;
            }
            // 在pre和pre.next之间插入curr
            ListNode temp = curr.next;
            curr.next = pre.next;
            pre.next = curr;
            pre = shaobing;
            curr = temp;
        }
        return shaobing.next;
    }


    public static void main(String[] args) {
        ListNode a = new ListNode(4);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(1);
        ListNode d = new ListNode(3);

        a.next = b;
        b.next = c;
        c.next = d;

        _06_Insert_Sort_Linkedlist sortLinkedlist = new _06_Insert_Sort_Linkedlist();

        ListNode res = sortLinkedlist.insertSort3(a);

        System.out.println(res);

    }
}
