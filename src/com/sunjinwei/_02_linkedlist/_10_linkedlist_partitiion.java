package com.sunjinwei._02_linkedlist;

import com.sunjinwei.domain.ListNode;

/**
 * @program: com.sunjinwei._02_linkedlist
 * @author: sun jinwei
 * @create: 2021-05-06 23:06
 * @description: 将单向链表按某值划分成左边小、中间相等、右边大的形式 难度：中等
 * 问题一：对调整后的节点顺序没有更多的要求。
 * 问题二：进阶对每一区域进行排序
 **/
public class _10_linkedlist_partitiion {

    /**
     * 问题1：类比荷兰国旗,对象是数组，但是这里是链表，但是也可以转换为数组，泛型是链表的数组,链表的partition
     * 时间和空间复杂度为O(n)
     *
     * @param root
     * @param pivot
     * @return
     */
    public ListNode sortLinkedlist(ListNode root, int pivot) {
        // 1计算出链表的长度
        int len = 0;
        ListNode curr = root;
        while (curr != null) {
            len++;
            curr = curr.next;
        }
        // 2创建数组
        ListNode[] arr = new ListNode[len];
        // 3初始化数组
        curr = root;
        for (int i = 0; i < len; i++) {
            arr[i] = curr;
            curr = curr.next;
        }
        // 4进行partition处理
        int less = -1;
        int more = len;
        int index = 0;
        while (index < more) {
            if (arr[index].val < pivot) {
                less++;
                swap(arr, index, less);
                index++;
            } else if (arr[index].val == pivot) {
                index++;
            } else {
                more--;
                swap(arr, more, index);
            }
        }
        // 5此时的arr就是左中右的形式 将链表连接起来
        for (int i = 1; i < arr.length; i++) {
            arr[i - 1].next = arr[i];
        }
        // 6bug 不要忘记
        arr[len - 1].next = null;
        return arr[0];
    }

    private void swap(ListNode[] arr, int i, int j) {
        ListNode temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}