package com.sunjinwei._03_tree;


import com.sunjinwei.domain.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 统计二叉树节点个数
 * 方法1：递归
 * 方法2：迭代
 */
public class _06_get_node_num {

    /**
     * 方法1：递归解决，前序遍历
     *
     * @param root
     * @return
     */
    public int getNodeNum_01(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int count = 1;
        int leftNum = getNodeNum_01(root.left);
        int rightNum = getNodeNum_01(root.right);
        count = count + leftNum + rightNum;
        return count;
    }

    /**
     * 还可以简洁一点的写法
     */
    public int getNodeNum_02(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return getNodeNum_02(root.left) + getNodeNum_02(root.right) + 1;
    }


    /**
     * 方法2：迭代做法，思路：层序遍历
     *
     * @param root
     * @return
     */
    public int getNodeNum_03(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 声明队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // 声明节点数
        int count = 1;
        // 处理：非空节点才进入队列
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll.left != null) {
                count++;
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                count++;
                queue.offer(poll.right);
            }
        }
        return count;

    }


}
