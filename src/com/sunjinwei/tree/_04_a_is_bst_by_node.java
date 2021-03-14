package com.sunjinwei.tree;


import com.sunjinwei.domain.TreeNode;

/**
 * 根据根节点验证二叉搜索树 力扣98
 * 思路一：
 * 1.首先要知道二叉搜索树有什么性质可以给我们利用，
 * 2.
 * 性质1：如果该二叉树的左子树不为空，则左子树上所有节点的值均小于根节点的值，
 * 性质2：如果该二叉树的右子树不为空，则右子树上所有节点的值均大于根节点的值
 * 根据性质，可以启示设计一个递归函数helper(root, lower, upper) 来递归判断
 * 3.根据二叉搜索树的性质，在递归调用左子树时，需要把上界upper改为root.val
 * 因为左子树里所有节点的值均小于它的根节点的值
 * <p>
 * 注意：如下自己写出了错误方法和正确方法，方便自己比较
 * <p>
 * 思路二：
 * 使用中序遍历
 */
public class _04_a_is_bst_by_node {

    /**
     * 错误写法：二叉搜索树的性质有：BST的每个节点应该小于右子树的所有节点
     * 这种写法不能保证剩下的子树 比如左子树所有节点的值小于根节点 右子树所有节点的值大于根节点
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left != null && root.val <= root.left.val) {
            return false;
        }
        if (root.right != null && root.val >= root.right.val) {
            return false;
        }
        return isValidBST(root.left) && isValidBST(root.right);
    }

    /**
     * 正确解法一： 使用辅助函数，其实就是除了当前节点 还需要传父节点 这样就可以保证左小于父，父小于右
     *
     * @param root
     * @return
     */
    public boolean isValidBST_01(TreeNode root) {

        return help(root, null, null);
    }

    /**
     * 辅助函数： 其实思想是前序遍历
     * 当前节点是左节点 就需要传入最大值root
     * 当前节点是右节点 就需要传入最小值root
     *
     * @param root
     * @param min  对于右子树来说，根节点是最小值
     * @param max  对于左子树来说，根节点是最大值
     * @return
     */
    private boolean help(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        // 如果min不为空 说明是右子树和根节点比较
        // 传入右子树和根节点 根节点作为min传入 如果根节点的值大于等于右子树的值 返回false
        if (min != null && min.val >= root.val) {
            return false;
        }
        // 如果max不为空 说明是左子树和根节点比较
        // 传入左子树和根节点 根节点作为max传入 如果根节点的值小于等于左子树的值 返回false
        if (max != null && max.val <= root.val) {
            return false;
        }
        // 前序遍历逻辑完 处理左子树和右子树
        return help(root.left, min, root) && help(root.right, root, max);
    }

    /**
     * 正确解法二：中序遍历
     * 判断一棵二叉树是否为搜索二叉树，只要改写一个二叉树中序遍历，在遍历的过程中看节点值是否都是递增的即可
     * 中序遍历时，判断当前节点是否大于中序遍历的前一个节点，如果大于，说明满足 BST，继续遍历；否则直接返回 false。
     * <p>
     * 思路：
     * 1。声明一个pre变量 记录上一个节点的值，用于比较当前节点和上一个节点的大小，为了方便 声明为全局变量
     * 2。中序遍历 先判断左子树 然后根节点 处理完之后将根节点的值赋值给pre变量，最后是右子树
     *
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38 MB, 在所有 Java 提交中击败了77.80%的用户
     */
    private Long pre = Long.MIN_VALUE;

    public boolean isValidBST_02(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 1 左子树
        boolean left = isValidBST_02(root.left);
        if (!left) {
            return false;
        }
        // 2 根节点
        if (root.val <= pre) {
            return false;
        }
        pre = Long.valueOf(root.val);
        // 3 右子树
        return isValidBST_02(root.right);
    }


    /**
     * 正确解法三：解法一的变种，将min和max换成值 而不是节点
     * <p>
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.2 MB, 在所有 Java 提交中击败了31.29%的用户
     *
     * @param root
     */
    public boolean isValidBST_03(TreeNode root) {

        // 使用辅助函数
        return help_03(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean help_03(TreeNode curr, long minValue, long maxValue) {
        // 鲁棒性
        if (curr == null) {
            return true;
        }
        // 这里合二为一的判断
        // min<curr<max 不符合 那么返回false
        if (curr.val <= minValue || curr.val >= maxValue) {
            return false;
        }
        boolean left = help_03(curr.left, minValue, curr.val);
        boolean right = help_03(curr.right, curr.val, maxValue);
        return left && right;
    }


    public static void main(String[] args) {
        _04_a_is_bst_by_node isBstByNode = new _04_a_is_bst_by_node();
        TreeNode node = new TreeNode(1);
        TreeNode left = new TreeNode(1);
        node.left = left;
        boolean bst = isBstByNode.isValidBST_03(node);
        System.out.println(bst);
    }


}
