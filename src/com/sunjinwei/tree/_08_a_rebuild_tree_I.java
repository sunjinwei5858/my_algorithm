package com.sunjinwei.tree;

import com.sunjinwei.domain.TreeNode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 根据前序和中序结果重建二叉树
 * 思路：
 * 递归+区间分治
 * 根据前序特点，第一个是根节点，接下来是左子树，然后是右子树
 * 根据中序特点，找到根节点的位置，那么根节点左边就是左子树的全部节点，根节点的右边就是右子树的全部节点
 * 左子树节点个数的计算：根节点索引减去起始索引即可
 */
public class _08_a_rebuild_tree_I {

    /**
     * 方法1：简单粗暴 每次递归都重新查找中序根节点的位置
     * 1根据前序找到头节点，
     * 2根据中序结果，头节点的左边是左子树，右边是右子树
     * <p>
     * 根据前、中序遍历的特点，（根左右、左根右），
     * 先根据前序遍历确定根节点，然后在中序遍历知道该根节点的左右树的数量，
     * 反推出前序遍历中左子树的结点有哪些。根据该思路进行递归即可完成二叉树的重建。
     *
     * @param preArr
     * @param inArr
     * @return
     */
    public TreeNode arr2Tree(int[] preArr, int[] inArr) {
        // 鲁棒性
        if (preArr == null || preArr.length == 0 || inArr == null || inArr.length == 0) {
            return null;
        }
        // 1根据前序遍历结果定位根节点
        int rootVal = preArr[0];
        // 2生成根节点
        TreeNode root = new TreeNode(rootVal);
        // 3在中序遍历结果中查找根节点
        for (int i = 0; i < inArr.length; i++) {
            if (inArr[i] == rootVal) {
                // 构建左子树
                // 将左子树的前中序遍历分割开
                root.left = arr2Tree(Arrays.copyOfRange(preArr, 1, i + 1), Arrays.copyOfRange(inArr, 0, i));
                // 构建右子树
                // 将右子树的前中序遍历分割开
                root.right = arr2Tree(Arrays.copyOfRange(preArr, i + 1, preArr.length), Arrays.copyOfRange(inArr, i + 1, inArr.length));
            }
        }
        return root;
    }

    /**
     * 方法2：使用hashmap存储中序结果 优化时间和空间复杂度
     * 执行用时：3 ms, 在所有 Java 提交中击败了65.11%的用户
     * 内存消耗：39.1 MB, 在所有 Java 提交中击败了10.60%的用户
     *
     * @param preArr
     * @param inArr
     */
    public TreeNode arr2Tree_02(int[] preArr, int[] inArr) {
        // 1使用hashmap存储中序遍历根节点位置
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < inArr.length; i++) {
            hashMap.put(inArr[i], i);
        }
        return help_01(preArr, 0, preArr.length - 1, 0, hashMap);
    }

    /**
     * 思路：先处理根，再处理左子树，再处理右子树
     *
     * @param preArr  前序数组 根左右
     * @param begin   前序数组起始索引
     * @param end     前序数组结束索引
     * @param start   中序数组开始索引 左根右
     * @param hashMap 中序结果
     * @return
     */
    private TreeNode help_01(int[] preArr, int begin, int end, int start, HashMap<Integer, Integer> hashMap) {
        if (begin > end) {
            return null;
        }
        // 1前序第一个元素就是根节点的值
        int rootVal = preArr[begin];
        // 处理根节点
        TreeNode root = new TreeNode(rootVal);
        // 2中序根节点的索引
        Integer rootIndex = hashMap.get(rootVal);
        // 3左子树结点个数可以通过中序根节点的位置与中序起始位置确定
        // 处理左子树
        // start: 中序根节点的左边就是左子树 所以start为数组的第一个
        root.left = help_01(preArr, begin + 1, begin + rootIndex - start, start, hashMap);
        // 处理右子树
        // start: 中序根节点的右边就是右子树 所以start为根节点的索引+1
        root.right = help_01(preArr, begin + rootIndex - start + 1, end, rootIndex + 1, hashMap);
        return root;
    }


    /**
     * 方法3：只传入数组的指针，将哈希表和前序数组声明为全局变量
     *
     * @param args
     */
    private HashMap<Integer, Integer> hashMap;
    private int[] preOrder;

    public TreeNode arr2Tree_03(int[] preOrder, int[] inOrder) {
        this.preOrder = preOrder;
        this.hashMap = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            hashMap.put(inOrder[i], i);
        }
        return help_03(0, preOrder.length - 1, 0, inOrder.length - 1);
    }

    private TreeNode help_03(
            int preBegin,
            int preEnd,
            int inBegin,
            int inEnd
    ) {
        if (preBegin > preEnd || inBegin > inEnd) {
            return null;
        }
        // 根据前序创建根节点 根左右
        TreeNode root = new TreeNode(preOrder[preBegin]);
        // 找到这个根节点在中序的索引
        Integer index = hashMap.get(root.val);
        // 左子树开始索引
        // 左子树结束索引 其中(index-inBegin)为左子树节点个数
        // 通过画图确定 preEnd索引(左子树最后一个索引)：preBegin+(index-inBegin)
        root.left = help_03(preBegin + 1, preBegin + (index - inBegin), inBegin, index - 1);
        // 右子树开始索引
        // 右子树结束索引
        root.right = help_03(preBegin + (index - inBegin) + 1, preEnd, index + 1, inEnd);
        return root;
    }


    public static void main(String[] args) {

        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;

        TreeNode left1 = new TreeNode(4);
        TreeNode right1 = new TreeNode(5);
        left.left = left1;
        right.right = right1;


        int[] preArr = new int[]{1, 2, 4, 5, 3};
        int[] inArr = new int[]{4, 2, 5, 1, 3};

        _08_a_rebuild_tree_I rebuildTreeI = new _08_a_rebuild_tree_I();
        TreeNode treeNode = rebuildTreeI.arr2Tree_03(preArr, inArr);
        System.out.println(treeNode);


    }


}
