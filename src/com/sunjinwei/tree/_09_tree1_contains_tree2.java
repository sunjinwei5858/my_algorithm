package com.sunjinwei.tree;

import com.sunjinwei.domain.TreeNode;

/**
 * 左神：
 * 判断t1树是否包含t2树的全部拓扑结构
 */
public class _09_tree1_contains_tree2 {

    /**
     * 方法1：
     * 思路：想到使用前序遍历的方式处理,
     *
     * @param t1
     * @param t2
     * @return
     */
    public boolean isContains(TreeNode t1, TreeNode t2) {
        // 鲁棒性1:t2为空 返回true
        if (t2 == null) {
            return true;
        }
        // 鲁棒性2:t1为空 返回false
        if (t1 == null) {
            return false;
        }
        // 第一种情况：真正的比较方法
        // 第二种情况：t2在t1的左子树中
        // 第三种情况: t2在t1的右子树中
        return check(t1, t2) || isContains(t1.left, t2) || isContains(t1.right, t2);
    }

    /**
     * 真正的比较方法
     * 思路：
     * 1。鲁棒性的判断
     * 2。比较值 不相等 返回true
     * 3。值相等 同步比较左子树和右子树 都必须相等
     *
     * @param t1
     * @param t2
     * @return
     */
    private boolean check(TreeNode t1, TreeNode t2) {
        // 鲁棒性1:t2为空 返回true
        if (t2 == null) {
            return true;
        }
        // 鲁棒性2:t1为空 返回false
        if (t1 == null) {
            return false;
        }
        // 鲁棒性3: 值不相等
        if (t1.val != t2.val) {
            return false;
        }
        // 否则 继续比较t1和t2的子节点
        return check(t1.right, t2.right) && check(t1.left, t2.left);
    }


}
