package com.sunjinwei._03_tree;

import com.sunjinwei.domain.TreeNode;

/**
 * 判断t1树是否包含t2树的全部拓扑结构 【左神】【剑指offer26 树的子结构】
 * 方法1：递归 时间复杂度O(N*M)
 * 进阶：时间复杂度优化到O(N+M)
 */
public class _09_tree1_contains_tree2_I {

    /**
     * 方法1：
     * 思路：递归，【左神写法】
     * 分为三种情况：
     * 第一种t1和t2头节点相同了；
     * 第二种t2可能在t1的左树中；
     * 第三种t2可能在t1的右树中
     * 所以需要抽取一个方法，用于判断第一种情况
     *
     * @param A
     * @param B
     * @return
     */
    public boolean isContains(TreeNode A, TreeNode B) {
        // 鲁棒性1:t2为空 返回true
        if (B == null) {
            return true;
        }
        // 鲁棒性2:t1为空 返回false
        if (A == null) {
            return false;
        }
        // 第一种情况：真正的比较方法
        // 第二种情况：t2在t1的左子树中
        // 第三种情况：t2在t1的右子树中
        return check(A, B) || isContains(A.left, B) || isContains(A.right, B);
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
        // 鲁棒性1:t2为空 返回true 必不可少的判断
        if (t2 == null) {
            return true;
        }
        // 鲁棒性2:t1为空 返回false 必不可少的判断
        if (t1 == null) {
            return false;
        }
        // 鲁棒性3:值不相等 只有上面都不为空 才可以进行值比较 否则会存在空指针异常的情况
        if (t1.val != t2.val) {
            return false;
        }
        // 否则 继续比较t1和t2的子节点
        return check(t1.right, t2.right) && check(t1.left, t2.left);
    }

    /**
     * 方法2：递归，【剑指offer写法】
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：40 MB, 在所有 Java 提交中击败了82.38%的用户
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        boolean result = false;
        // 如果都不为空树 那么进行比较
        if (A != null && B != null) {
            // 如果相等 直接进行比较
            if (A.val == B.val) {
                result = check(A, B);
            }
            // false 看是否在左子树中
            if (!result) {
                result = isSubStructure(A.left, B);
            }
            // false 看是否在右子树中
            if (!result) {
                result = isSubStructure(A.right, B);
            }
        }
        return result;
    }

}
