package com.sunjinwei._03_tree;

import com.sunjinwei.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一棵二叉搜索树，请找出其中第k大的节点。【剑指offer54 二叉搜索树的第k大节点】
 */
public class _14_bst_kth_Largest {

    /**
     * 思路一：利用bst的性质 中序遍历保证顺序 将二叉树所有结果保存在集合中 获取第k大节点也就是顺数[list.size-k]位置
     *
     * @param root
     * @param k
     * @return
     */
    private List<Integer> result;

    public int kthLargest(TreeNode root, int k) {
        if (root == null || k <= 0) {
            return 0;
        }
        this.result = new ArrayList<>();
        help(root);
        return result.get(result.size() - k);
    }

    private void help(TreeNode root) {
        if (root == null) {
            return;
        }
        help(root.left);
        result.add(root.val);
        help(root.right);
    }

    /**
     * 思路二：利用bst性质，中序：左根右 得出来的顺序是从小到大，      【提前结束遍历】
     * 那么我们可以反过来，让中序变成右根左 那么得出来的顺序就是从大到小，
     * 这样获取第k大节点 就会变成索引[k]位置就行
     */
    private int value;
    // 计数器
    private int k;

    public int kthLargest_02(TreeNode root, int k) {
        this.k = k;
        help_02(root);
        return value;

    }

    private void help_02(TreeNode root) {
        if (root == null) {
            return;
        }
        // 先递归右子树
        help_02(root.right);
        k--;
        if (k == 0) {
            this.value = root.val;
            return;
        }
        // 再处理左子树
        help_02(root.left);

    }


}
