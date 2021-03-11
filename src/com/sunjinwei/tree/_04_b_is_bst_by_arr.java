package com.sunjinwei.tree;


/**
 * 左神：
 * 给定一个整型数组arr，已知其中没有重复值，判断arr是否可能是节点值类型为整型的搜索二叉树后序遍历的结果。
 * 进阶问题：如果整型数组arr中没有重复值，且已知是一棵搜索二叉树的后序遍历结果，通过数组arr重构二叉树。
 * <p>
 * 剑指offer：
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回true，否则返回false。假设输入的数组的任意两个数字都互不相同。
 */
public class _04_b_is_bst_by_arr {

    /**
     * 方法1：递归
     * 如果是bst的后序遍历，那么最后一个元素就是根节点，
     * 根据bst的性质，数组的左边会小于根节点的值，数组的右边会大于根节点的值。
     * 关键是找到左数组的区间[begin,m] 右数组的区间[m+1, end]
     *
     * @param postorder
     * @return
     */
    private int[] postorder;

    public boolean verifyPostorder(int[] postorder) {
        // 鲁棒性：输入空数组 返回true
        if (postorder == null || postorder.length == 0) {
            return true;
        }
        this.postorder = postorder;
        return isBst(0, postorder.length - 1);
    }

    /**
     * 比如，arr=[2，1，3，6，5，7，4]，比 4 小的部分为[2，1，3]，比 4 大的部分为[6，5，7]。
     * 如果不满足这种情况，则说明这个数组一定不可能是搜索二叉树后序遍历的结果。
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：36.1 MB, 在所有 Java 提交中击败了11.78%的用户
     *
     * @param begin
     * @param end
     * @return
     */
    private boolean isBst(int begin, int end) {
        // 终止条件1：如果只有一个节点 那么返回true
        if (begin == end) {
            return true;
        }
        // 找到左子树和右子树的区间
        int less = -1;
        int more = end;
        for (int i = begin; i < end; i++) {
            // 如果比根节点小 赋值给less 作用：给左子树找最大边界 需要不断的寻找
            if (postorder[end] > postorder[i]) {
                less = i;
            } else {
                // 如果比根节点更大 赋值给more 作用：给右子树找最小边界 因为是边界最小值 所以找一次就行了即赋值一次
                if (more == end) {
                    more = i;
                }
            }
        }
        // 终止条件2: 可能是右斜树或者左斜树
        // less = -1 : 说明是右斜树 没有左子树
        // more = end : 说明是左斜树 没有右子树
        // 因为只要是正常左右子树都有的 那么less和more肯定都会变
        if (less == -1 || more == end) {
            return isBst(begin, end - 1);
        }
        // 终止条件3: 左子树最大值和右子树最小值的索引相差1
        if (less != more - 1) {
            return false;
        }
        return isBst(begin, less) && isBst(more, end - 1);
    }

}
