package com.sunjinwei._02_linkedlist02;

import com.sunjinwei.domain.ListNode;

/**
 * 单链表的选择排序【类比数组的选择排序】
 * 要求：O(1)内存
 */
public class _06_Select_Sort_LinkedList {

    /**
     * 选择排序的思想：
     * 扫描所有值，找到最小值，将最小值放到第一个位置；
     * 然后从第二个位置开始重新扫一遍，将最小值放到第二个位置
     * <p>
     * 因为是O(1)内存 所以只能使用有限变量完成，对于链表来说 找到最小节点时 需要断开和前一个节点的关系 所以需要记录前一个节点
     *
     * @param head
     * @return
     */
    public ListNode selectSort(ListNode head) {

        // 1排好序的头部节点
        ListNode newHead = null;

        // 2排好序的尾巴节点
        ListNode tail = null;

        // 3当前遍历的节点
        ListNode curr = head;

        // 4最小值节点
        ListNode small = null;

        // 5最小值的前一个节点
        ListNode smallestPreNode = null;

        while (curr != null) {

            // 默认最小值就是curr
            small = curr;

            // 获取最小值的前一个节点
            smallestPreNode = getSmallestPreNode(curr);

            // 如果前一个节点不为空 那么更新最小值 并且处理删除最小值节点
            if (smallestPreNode != null) {
                small = smallestPreNode.next;
                smallestPreNode.next = small.next;
            }

            // 处理节点之间的指向
            if (tail == null) {
                newHead = small;
            } else {
                tail.next = small;
            }

            // 更新tail
            tail = small;

            // 如果curr就是最小值节点 那么处理curr的next
            // 否则下一次遍历还是从curr开始
            if (curr == small) {
                curr = curr.next;
            }

        }
        return newHead;
    }


    /**
     * 链表的最小值节点的前一个节点!!!【如果第一个节点就是最小值 那么这个节点的前一个节点就是null】
     *
     * @param head
     * @return 返回最小值节点的pre节点!!!!!
     */
    private ListNode getSmallestPreNode(ListNode head) {

        // 最小值的前一个节点 只能为null
        ListNode preSmallest = null;

        // 默认head就是最小值
        ListNode smallest = head;

        // 当前遍历的节点
        ListNode curr = head.next;

        // 记录上一个节点
        ListNode pre = head;

        while (curr != null) {
            if (curr.val < smallest.val) {
                smallest = curr;
                preSmallest = pre;
            }
            pre = curr;
            curr = curr.next;
        }
        return preSmallest;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(0);
        ListNode c = new ListNode(5);
        ListNode d = new ListNode(3);

        a.next = b;
        b.next = c;
        c.next = d;

        _06_Select_Sort_LinkedList sortLinkedList = new _06_Select_Sort_LinkedList();

        ListNode node = sortLinkedList.selectSort(a);
        System.out.println(node.val);


    }


}
