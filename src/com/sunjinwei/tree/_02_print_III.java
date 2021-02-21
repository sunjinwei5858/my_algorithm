package com.sunjinwei.tree;

import com.sunjinwei.domain.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 剑指offer32-III：从上到下打印二叉树III变种
 * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
 * 注意：第1行从左到右，第2行从右到左，第三行，从左到右...
 * <p>
 * 题目要求的二叉树的 从上至下 打印（即按层打印），又称为二叉树的 广度优先搜索（BFS）。
 * BFS 通常借助 队列 的先入先出特性来实现。
 */
public class _02_print_III {

    /**
     * 方法一：层序遍历
     * 注意：第1行从左到右，第2行从右到左，第三行，从左到右...
     * 也就是奇数行：从左到右
     * 偶数行：从右到左
     * <p>
     * 使用位运算 &，来判断奇偶性：
     * x & 1 = 0 代表x是偶数
     * x & 1 = 1 代表x是奇数
     * 也可以使用位运算 % 来判断奇偶
     * x % 2 = 0 表示x是偶数
     * x % 2 != 0 表示是奇数
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.72%的用户
     * 内存消耗：38.6 MB, 在所有 Java 提交中击败了65.09%的用户
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        // 声明是第几行
        int level = 0;
        while (!deque.isEmpty()) {
            level++;
            List<Integer> list = new ArrayList<>();
            int size = deque.size();
            // 使用位运算来判断奇偶性
            // 偶数：从右到左
            // 奇数：从左到右
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = deque.poll();
                list.add(treeNode.val);
                if (treeNode.left != null) {
                    deque.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    deque.offer(treeNode.right);
                }
            }
            if ((level & 1) == 0) {
                // 使用自己手写的反转集合方法
                list = reverse(list);
                // 或者使用jdk提供的工具类进行反转集合
                //Collections.reverse(list);
            }
            result.add(list);
        }
        return result;
    }

    /**
     * 反转集合的元素方法，jdk的Collections也提供reverse方法
     *
     * @param list
     */
    private List<Integer> reverse(List<Integer> list) {

        if (list == null || list.size() == 0) {
            return list;
        }
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            Integer leftVal = list.get(left);
            Integer rightVal = list.get(right);
            list.set(left, rightVal);
            list.set(right, leftVal);
            left++;
            right--;
        }
        return list;
    }

    public static void main(String[] args) {
        int a = 7;

        System.out.println((a >> 1));
    }
}
