package com.sunjinwei.tree;

import com.sunjinwei.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 中序遍历：左子树 根节点 右子树
 * 方法：递归和非递归
 */
public class _01_order_II {

    private List<Integer> result = new ArrayList<>();

    /**
     * 递归
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.6 MB, 在所有 Java 提交中击败了68.71%的用户
     */
    public List<Integer> orderMiddle_01(TreeNode root) {
        if (root == null) {
            return result;
        }
        // 因为已经提前做了判空 所以这里不需要判断root.left是否为null
        orderMiddle_01(root.left);
        result.add(root.val);
        orderMiddle_01(root.right);
        return result;
    }

    /**
     * 非递归：使用栈
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了41.44%的用户
     * 内存消耗：36.5 MB, 在所有 Java 提交中击败了85.67%的用户
     */
    public List<Integer> orderMiddle_02(TreeNode head) {
        if (head == null) {
            return result;
        }
        // 使用栈

        Stack<TreeNode> stack = new Stack<>();
        while (head != null || !stack.isEmpty()) {
            if (head != null) {
                // 刚开始先是根节点，左节点进栈
                // 后面走了else代码逻辑之后 右节点进栈
                stack.push(head);
                head = head.left;
            } else {
                // 出栈
                // 刚开始是左节点 根节点出栈
                // 后面是右节点出栈
                head = stack.pop();
                // 先将左节点 根节点 添加到结果集
                result.add(head.val);
                // 然后开始右节点的处理
                head = head.right;
            }
        }
        return result;
    }


}
