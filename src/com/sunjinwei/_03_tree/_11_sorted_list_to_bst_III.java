package com.sunjinwei._03_tree;

import com.sunjinwei.domain.ListNode;
import com.sunjinwei.domain.TreeNode;

import java.util.ArrayList;

/**
 * 有序链表转bst 【力扣109】 难度：中等
 * <p>
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 */
public class _11_sorted_list_to_bst_III {

    /**
     * 方法1：分治+断开链表为两部分  【左闭右开】
     * 思路：1快慢指针找中点  2使用pre节点记录前一个节点便于断开 分成左链表 右链表【因为是单链表，双链表就不需要声明pre】
     * 注意：如果题目是双向链表 那么访问前一个节点也很简单 那么就需要单独声明pre变量来记录前一个节点
     *
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了98.76%的用户
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST_01(ListNode head) {
        // 特殊情况1
        if (head == null) {
            return null;
        }
        // 特殊情况2
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;
        // 快慢指针寻找中点
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        // 断开中点!!!! 将链表分为两部分
        // 断开的作用：保证了左链表区间[left, null], 右链表[middle.next, null]
        pre.next = null;
        // 创建根节点
        TreeNode root = new TreeNode(slow.val);
        // 递归
        // 左子树 从head开始
        root.left = sortedListToBST_01(head);
        // 右子树 从中点的下一个开始
        root.right = sortedListToBST_01(slow.next);
        return root;
    }

    /**
     * 方法2：分治+但是不拆成两个子链表 而是使用链表区间(leftNode, rightNode] 左闭右开,不需要记录pre节点    【左闭右开】
     * <p>
     * 思考：如何设置左闭右开，也就是left包含在链表，但是right不包含在链表？
     * 辅助函数help_02(head,null) right传入null 就完成左闭右开的设置
     * <p>
     * 如果设定「左闭右开」的关系，我们就可以直接用(left,mid) 以及(mid.next,right) 来表示左右子树对应的列表了。
     * 并且，初始的列表也可以用(head,null) 方便地进行表示，其中null表示空节点。
     *
     * @param head
     */
    public TreeNode sortedListToBST_02(ListNode head) {

        return help_02(head, null);
    }

    private TreeNode help_02(ListNode left, ListNode right) {
        // 终止条件: left=right
        if (left == right) {
            return null;
        }
        // 根据区间寻找链表中点
        ListNode middle = findMiddleNode(left, right);
        TreeNode root = new TreeNode(middle.val);
        // 因为提前设置了左闭右开始 所以对于左树 right传入middle是没问题的
        root.left = help_02(left, middle);
        root.right = help_02(middle.next, right);
        return root;
    }

    /**
     * 在链表区间中找中点【还可以这么玩，这是自己第一次见到】
     *
     * @param left
     * @param right
     */
    private ListNode findMiddleNode(ListNode left, ListNode right) {
        ListNode slow = left;
        ListNode fast = left;
        while (fast != right && fast.next != right) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 方法3：分治+中序遍历  【左闭右闭】left和right全部都在区间
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了55.62%的用户
     * 内存消耗：40 MB, 在所有 Java 提交中击败了5.13%的用户  【开辟了list容器】
     *
     * @param head
     * @return
     */
    private ArrayList<Integer> arrayList;

    public TreeNode sortedListToBST_03(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        arrayList = new ArrayList<>();
        while (head != null) {
            arrayList.add(head.val);
            head = head.next;
        }
        return help_03(0, arrayList.size() - 1);
    }

    private TreeNode help_03(int left, int right) {
        if (left > right) {
            return null;
        }
        // 寻找中点
        int middleIndex = findMiddleIndex(left, right);
        TreeNode root = new TreeNode(arrayList.get(middleIndex));
        // 左闭右闭原则==》左子树区间[left, middle-1]
        root.left = help_03(left, middleIndex - 1);
        // 右子树区间[middle+1, right]
        root.right = help_03(middleIndex + 1, right);
        return root;
    }

    /**
     * 寻找中点的方法, 使用不会超过int溢出的写法 位运算>>
     * 这里使用(left+right)/2或者(left+right+1)/2都可以
     *
     * @param left
     * @param right
     */
    private int findMiddleIndex(int left, int right) {

        return left + ((right - left) >> 1);
    }

    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);

        one.next = two;
        two.next = three;
        three.next = four;

        _11_sorted_list_to_bst_III sorted_list_to_bst_iii = new _11_sorted_list_to_bst_III();
        TreeNode treeNode = sorted_list_to_bst_iii.sortedListToBST_02(one);
        System.out.println(treeNode);


    }
}
