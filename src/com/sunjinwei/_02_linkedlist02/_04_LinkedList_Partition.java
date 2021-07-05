package com.sunjinwei._02_linkedlist02;

import com.sunjinwei.domain.ListNode;

/**
 * 链表的partition
 * 将单向链表按某值划分成左边小 中间相等 右边大的形式
 * 给定一个单向链表，再给定一个整数pivot，实现一个调整链表的函数
 * 题目1：对调整后的顺序没有更多的要求
 * 题目2：每部分里的节点从左到右的顺序与原链表中节点的先后次序一致，并且空间为O(1) 【进阶】
 */
public class _04_LinkedList_Partition {

    /**
     * 题目1: 单纯的partition 时间复杂度为O(n) 空间复杂度也为O(n)
     */
    public ListNode listNodePartition1(ListNode head, int pivot) {

        // 1将链表转为数组
        ListNode l = head;
        int len = 0;
        while (l != null) {
            l = l.next;
            len++;
        }
        ListNode[] arr = new ListNode[len];
        l = head;
        len = 0;
        while (l != null) {
            arr[len] = l;
            l = l.next;
            len++;
        }

        // 2进行partition
        int left = -1;
        int right = arr.length;
        int curr = 0;
        while (curr < right) {
            if (arr[curr].val < pivot) {
                left++;
                swap(arr, curr, left);
                curr++;
            } else if (arr[curr].val > pivot) {
                right--;
                swap(arr, curr, right);
            } else {
                curr++;
            }
        }

        // 3重新将链表连接起来
        for (int i = 1; i < arr.length; i++) {
            arr[i - 1].next = arr[i];
        }
        // 4防止循环链表 !!!!
        arr[arr.length - 1].next = null;

        return arr[0];
    }


    /**
     * 交换元素
     */
    private void swap(ListNode[] arr, int i, int j) {
        ListNode temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 题目2：每部分里的节点从左到右的顺序与原链表中节点的先后次序一致 时间复杂度为O(n) 空间复杂度为O(1)
     * 考查利用有限几个变量调整链表的代码实现能力
     */
    public ListNode listNodePartition2(ListNode head, int pivot) {
        // 拆成三个链表 然后将三个链表连接起来

        // 左
        ListNode leftHead = null;
        ListNode leftTail = null;
        // 中
        ListNode middleHead = null;
        ListNode middleTail = null;
        // 右
        ListNode rightHead = null;
        ListNode rightTail = null;

        // 当前处理的节点
        ListNode curr = head;
        // 临时存储变量
        ListNode next = null;

        while (curr != null) {
            // 先保存临时变量
            next = curr.next;
            // 因为是声明三个链表 所以设置curr的next指向null 不然会造成循环链表
            curr.next = null;
            if (curr.val < pivot) {
                // 左
                if (leftHead == null) {
                    leftHead = curr;
                    leftTail = curr;
                } else {
                    leftTail.next = curr;
                    leftTail = curr;
                }
            } else if (curr.val == pivot) {
                // 中
                if (middleHead == null) {
                    middleHead = curr;
                    middleTail = curr;
                } else {
                    middleTail.next = curr;
                    middleTail = curr;
                }
            } else {
                // 右
                if (rightHead == null) {
                    rightHead = curr;
                    rightTail = curr;
                } else {
                    rightTail.next = curr;
                    rightTail = curr;
                }
            }
            // 更新curr
            curr = next;
        }
        // 将左和中两个链表连接起来
        if (leftTail != null) {
            leftTail.next = middleHead;
            // middleTail为null 说明没有中的链表
            if (middleTail == null) {
                middleTail = leftTail;
            }
        }
        // 将中和右两个链表连接起来
        if (middleTail != null) {
            middleTail.next = rightHead;
        }
        ListNode res = null;
        if (leftHead != null) {
            res = leftHead;
        } else if (leftHead == null && middleHead != null) {
            res = middleHead;
        } else if (leftHead == null && middleHead == null && rightHead != null) {
            res = rightHead;
        }
        return res;

    }

    public static void main(String[] args) {

        _04_LinkedList_Partition listPartition = new _04_LinkedList_Partition();
        // 7 9 1 8 5 2 5
        ListNode a = new ListNode(7);
        ListNode b = new ListNode(9);
        ListNode c = new ListNode(1);
        ListNode d = new ListNode(8);
        ListNode e = new ListNode(5);
        ListNode f = new ListNode(2);
        ListNode g = new ListNode(5);

        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;

        ListNode r = listPartition.listNodePartition2(a, 5);

        while (r != null) {
            System.out.println(r.val);
            r = r.next;
        }

    }

}
