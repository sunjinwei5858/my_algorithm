package com.sunjinwei._02_linkedlist02;

import com.sunjinwei.domain.ListNode;

/**
 * 链表的partition
 * 将单向链表按某值划分成左边小 中间相等 右边大的形式
 * 给定一个单向链表，再给定一个整数pivot，实现一个调整链表的函数
 * 题目1：对调整后的顺序没有更多的要求
 * 题目2【进阶】：对调整后左中右内部也要进行排序，并且空间为O(1)
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
        ListNode f = arr[0];
        for (int i = 1; i < arr.length; i++) {
            f.next = arr[i];
            f = arr[i];
        }

        // 4防止循环链表 !!!!
        f.next = null;
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
     * 题目2：partition后还需要保证顺序 时间复杂度为O(n) 空间复杂度为O(1)
     * 考查利用有限几个变量调整链表的代码实现能力
     */
    public ListNode listNodePartition2(ListNode head, int pivot) {










        return new ListNode(1);
    }

    /**
     * 题目2：partition后还需要保证顺序 时间复杂度为O(n) 空间复杂度为O(1)
     * 考查利用有限几个变量调整链表的代码实现能力
     */
    public ListNode listNodePartition3(ListNode head, int pivot) {


        return new ListNode(1);

    }

    public static void main(String[] args) {

        _04_LinkedList_Partition listPartition = new _04_LinkedList_Partition();
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(5);
        ListNode d = new ListNode(1);

        a.next = b;
        b.next = c;
        c.next = d;

        ListNode r = listPartition.listNodePartition1(a, 3);

        while (r != null) {
            System.out.println(r.val);
            r = r.next;
        }

    }

}
