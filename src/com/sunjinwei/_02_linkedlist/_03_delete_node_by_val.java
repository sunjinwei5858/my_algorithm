package com.sunjinwei._02_linkedlist;


import com.sunjinwei.domain.ListNode;

import java.util.Stack;

/**
 * 给链表节点的值 删除该节点 【剑指offer18 删除链表的节点 简单】
 * <p>
 * 这里就涉及如下链表操作的两种方式：
 * 1直接使用原来的链表来进行删除操作。
 * 2设置一个虚拟头结点在进行删除操作。
 */
public class _03_delete_node_by_val {

    /**
     * 方法1：使用双指针 【兼容节点的值相同和不相同的情况】注意和方法3的区别
     *
     * @see https://mp.weixin.qq.com/s/slM1CH5Ew9XzK93YOQYSjA
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了22.42%的用户
     */
    public ListNode deleteNodeByVal_01(ListNode head, int val) {
        // 鲁棒性1：如果头节点为空
        if (head == null) {
            return null;
        }
        // 鲁棒性2：刚好是头节点 因为节点的值互不相同 所以这里可以提前判断
        if (head.val == val) {
            head = head.next;
        }
        // 声明这两个节点 是为了记住上一个节点
        // 也可以不声明出来 使用curr.next代替 但是得用到虚拟节点
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            // if判断的代码必须放在前面 不能放在cur=temp的后面
            ListNode temp = cur.next;
            if (cur.val == val) {
                pre.next = temp;
            }
            pre = cur;
            cur = temp;
        }
        return head;
    }


    /**
     * 方法2：使用栈，时间复杂度和空间复杂度都为O(n)【兼容节点的值相同和不相同的情况】
     * 思路：
     * 1将不等于val的进栈
     * 2然后将栈底的节点作为新的头节点返回
     *
     * @param head
     * @param val
     */
    public ListNode deleteNodeByVal_02(ListNode head, int val) {
        Stack<ListNode> stack = new Stack<>();
        // 1将不需要删除的节点进栈
        while (head != null) {
            if (head.val != val) {
                stack.push(head);
            }
            head = head.next;
        }
        // 2将栈底的节点作为新的头节点返回
        // 这段代码的处理也是妙哉啊!!!!!
        // 完美的让节点从头开始 而不是反转了
        head = returnSame(stack);
        return head;
    }


    /**
     * 栈实现链表的反转输出
     *
     * @param stack
     */
    private ListNode reverse(Stack<ListNode> stack) {
        ListNode shaobing = new ListNode(-1);
        ListNode curr = shaobing;
        while (!stack.isEmpty()) {
            ListNode pop = stack.pop();
            curr.next = pop;
            curr = pop;
        }
        curr.next = null;
        return shaobing.next;
    }

    /**
     * 栈实现原链表的输出
     *
     * @param stack
     */
    private ListNode returnSame(Stack<ListNode> stack) {
        ListNode head = null;
        while (!stack.isEmpty()) {
            ListNode pop = stack.pop();
            pop.next = head;
            head = pop;
        }
        return head;
    }

    /**
     * 方法3：使用哨兵节点的做法 【兼容节点的值相同和不相同的情况】
     * 分析：使用哨兵节点可以去掉判空的校验和头节点要不要删除的判断
     *
     * @param head
     * @param val
     * @see https://mp.weixin.qq.com/s/slM1CH5Ew9XzK93YOQYSjA
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：37.9 MB, 在所有 Java 提交中击败了52.11%的用户
     */
    public ListNode deleteNodeByVal_03(ListNode head, int val) {
        // 1创建哨兵节点 哨兵节点指向head
        // 不需要判断头节点是不是null
        ListNode shaobing = new ListNode(-1);
        shaobing.next = head;
        ListNode curr = shaobing;
        // 2进行处理：注意和方法1的区别 因为这里用到了虚拟节点 所以不需要声明当前节点和上一个节点这么麻烦
        while (curr.next != null) {
            // 注意这里是if和else关系
            if (curr.next.val == val) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
        return shaobing.next;
    }


    public static void main(String[] args) {
        ListNode first = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode forth = new ListNode(4);
        first.next = second;
        second.next = third;
        third.next = forth;

        _03_delete_node_by_val delete_node = new _03_delete_node_by_val();
        ListNode listNode = delete_node.deleteNodeByVal_03(first, 4);
        ListNode next = listNode.next;


    }
}
