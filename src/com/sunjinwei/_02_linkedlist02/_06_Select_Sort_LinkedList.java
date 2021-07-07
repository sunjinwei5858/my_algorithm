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

        ListNode curr = head;
        ListNode pre = null;
        while (curr != null) {
            // 获取最小值的前一个节点
            ListNode smallestPreNode = getSmallestPreNode(curr);
            // 如果最小值的前一个节点就是当前节点 那么不需要进行处理
            if (smallestPreNode == curr) {
                pre = curr;
            } else {
                // 调整节点指向
                ListNode small = smallestPreNode.next;
                pre.next = small;
                ListNode last = small.next;
                smallestPreNode.next = last;
                small.next = curr;
            }
            curr = curr.next;
        }
        return head;
    }


    /**
     * 链表的最小值节点的前一个节点!!!
     *
     * @param head
     * @return 返回最小值节点的pre节点!!!!!
     */
    private ListNode getSmallestPreNode(ListNode head) {

        // 最小值的前一个节点
        ListNode preSmallest = head;

        // 默认head就是最小值
        ListNode smallest = head;

        // 记录上一个节点
        ListNode pre = head;

        // 当前遍历的节点
        ListNode curr = head.next;

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
