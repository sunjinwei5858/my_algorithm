package com.sunjinwei._02_linkedlist;

import com.sunjinwei.domain.ListNode;

import java.util.HashSet;

/**
 * 问题二：判断是否有环，可以衍生两道题目：1返回boolean值 2返回进入环的第一个节点
 * 注意：环不一定是尾巴和头部相连 尾巴和中间节点相连也有可能
 * 进阶：你能用 O(1)（即，常量）内存解决此问题吗？
 */
public class _06_loop_linkedlist_II {

    /**
     * 力扣142 返回进入环的第一个节点 难度：中等 时间复杂度和空间复杂度为O(n)
     * 方法1：使用哈希表 hashset
     * 当节点重复时 此时节点就是环的第一个节点
     * <p>
     * 执行用时：4 ms, 在所有 Java 提交中击败了22.60%的用户
     * 内存消耗：39.7 MB, 在所有 Java 提交中击败了5.65%的用户
     */
    public ListNode detectCycle_01(ListNode head) {
        // 新建hashset 并且存储所有节点 导致空间复杂度为O(n)
        HashSet<ListNode> hashSet = new HashSet<>();
        ListNode curr = head;
        // 遍历所有节点 时间复杂度为O(n)
        while (curr != null) {
            if (!hashSet.add(curr)) {
                return curr;
            }
            curr = curr.next;
        }
        return null;
    }

    /**
     * 进阶做法，保证空间复杂度为O(1) 只申请了快慢两个指针的空间
     * 方法2：使用双指针
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.4 MB, 在所有 Java 提交中击败了82.74%的用户
     * <p>
     * fast指针和slow指针一定会再次相遇，并且在第一个入环的节点处相遇。
     * 证明略。【直接通过画图证明或者理解下面这句话】
     * 【快慢指针第一次相遇，慢指针就一直在环里绕了，此时重新让一个指针从头节点开始走，速度和在环里绕的慢指针一样，直到相遇，这就是入环的第一个节点，妙哉妙哉!!!!】
     * 注意：你也可以用哈希表完成问题一的判断，但是不符合题目关于空间复杂度的要求
     *
     * @param head
     */
    public ListNode detectCycle_02(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            // 先使用快慢指针找到是不是有环
            slow = slow.next;
            fast = fast.next.next;
            // 1先找到是不是有环
            if (slow == fast) {
                // 2此时确定已经成环
                // 2.1将快指针移动到head位置
                // 2.2此时快慢指针以相同的速度移动
                // 2.3当快慢指针相遇时 为环的第一个节点
                fast = head;
                while (slow != fast) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }

}
