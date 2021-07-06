package com.sunjinwei._02_linkedlist;

import com.sunjinwei.domain.ListNode;

import java.util.Stack;

/**
 * 反转单链表 力扣206 难度：简单
 * 总共有三种方法
 */
public class _04_reverse_linkedlist_I {

    /**
     * 方法1：好理解的双指针!!!!
     */
    public ListNode reverse(ListNode head) {
        // 不需要单独抽取这个if判断了
        if (head == null) {
            return null;
        }
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode temp = curr.next;
            // 1局部反转
            curr.next = pre;
            // 2局部反转完成之后，prepre 和 curcur 同时往前移动一个位置
            pre = curr;
            curr = temp;
        }
        return pre;
    }

    /**
     * 方法2：使用stack栈 注意事项就是 防止成环
     */
    public ListNode reverse_02(ListNode head) {
        // 鲁棒性1：需要这个判断 因为 _01_stack.pop() 如果stack为空 调用pop会报空指针异常 所以需要提前return
        if (head == null) {
            return null;
        }
        Stack<ListNode> stack = new Stack<>();
        // 进栈
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        // 出栈
        // 第一个出栈的就是反转之后的头节点
        ListNode result = stack.pop();

        // 将栈中节点连接起来
        ListNode temp = result;
        while (!stack.isEmpty()) {
            temp.next = stack.pop();
            temp = temp.next;
        }
        // 鲁棒性2： 防止成环!!!! 必不可少 如果是一个链表数组 也是一样需要防止成环bug
        temp.next = null;
        return result;
    }

    /**
     * 方法3：使用递归!!! 非常好的解法
     * 定义递归函数：对于递归算法 最重要的就是明确定义递归函数的行为
     * 输入一个节点head，将以head为起点的链表反转，并返回反转完成后的链表头节点
     * 思路：
     * 1 一直递归到链表的最后一个节点，该节点就是反转之后的头节点，
     * 2 此后 每次在函数返回的过程中，让当前节点的下一个节点的next指针指向当前节点
     * 3 同时让当前节点的next指针指向null，从而实现从链表尾部开始的局部反转
     * 4 当递归函数全部出栈后 递归完成
     */
    public ListNode reverse_03(ListNode head) {
        // 1base case 递归终止条件!!!
        // 1.1如果当前节点为null 返回该节点
        // 1.2判断是不是达到了最后一个节点 返回最后这个节点
        if (head == null || head.next == null) {
            return head;
        }
        // 2递归：返回反转
        ListNode last = reverse_03(head.next);
        // 3巧妙之处1：将指针反向
        ListNode cur = head.next;
        cur.next = head;
        // 4巧妙之处2：将当前节点指向null 其实就是处理尾节点的next指针 后驱节点
        head.next = null;
        return last;
    }

    /**
     * 方法4：递归写法二，基本都是利用双指针改编
     *
     * @param head
     */
    public ListNode reverse04(ListNode head) {


        return help(null, head);
    }

    private ListNode help(ListNode pre, ListNode curr) {
        if (curr == null) {
            return pre;
        }
        ListNode temp = curr.next;
        curr.next = pre;
        return help(curr, temp);
    }


    public static void main(String[] args) {
        _04_reverse_linkedlist_I linkedlistI = new _04_reverse_linkedlist_I();
        ListNode head = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        ListNode fourth = new ListNode(4);
        head.next = second;
        second.next = third;
        third.next = fourth;

        ListNode result = linkedlistI.reverse_02(head);

        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }


}
