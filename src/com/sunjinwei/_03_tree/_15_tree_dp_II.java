package com.sunjinwei._03_tree;

import com.sunjinwei.domain.MaxDistanceReturnType;
import com.sunjinwei.domain.TreeNode;

/**
 * 二叉树节点间的最大距离问题
 * 描述：从二叉树的节点A出发，可以向上或者向下走，但沿途的节点只能经过一次，当到达节点B时，路径上的节点数叫作A到B的距离，求整棵树上节点间的最大距离。
 * 树形dp：首先本题对于树形dp套路前提是满足的：依次求出每一个节点为头节点的子树上的最大距离，那么最终答案一定在其中。
 * 解决问题的重点：信息结构，信息
 */
public class _15_tree_dp_II {

    /**
     * 分析第一步：
     * 以某个节点x为头节点的子树中，分析答案有哪些可能性，并且这种分析是以X的左子树、X的右子树和X整棵树的角度来考虑可能性的。
     * 第一种：最大距离可能是左子树上的最大距离（A B 都在左树）
     * 第二种：最大距离可能是右子树上的最大距离（A B 都在右树）
     * 第三种：最大距离可能是从X的左子树离X最远的节点，先到达X，然后走到X的右子树离X最远的节点。也就是左子树高度+右子树高度+1。
     * （一个在左树 一个在右树 最大距离就是A到根节点的高度 B到根节点的高度 相加 最后再加1）
     * （并不是左树最大距离+右树最大距离，这一点要注意!!!）
     */

    /**
     * 分析第二步：
     * 根据第一步的可能性分析，列出所有可能的信息。
     * 第一种和第二种所需的信息：左树：最大距离 右树：最大距离
     * 第三种情况：左树高度 右树高度
     * ==》【最大距离 高度】
     */

    /**
     * 分析第三步：
     * 合并第二步的信息，写出信息结构，高度和最大距离
     * {
     *     int height;
     *     int maxDistance;
     * }
     */

    /**
     * 分析第四步：
     * 设计递归函数，递归函数是处理以x为节点的情况下的答案，包括base case，默认得到左树和右树的所有信息，以及把可能性整合
     * 并且返回第三步的结构
     */

    /**
     * 方法：_04_dp+后序遍历
     *
     * @param root
     * @return
     */
    private MaxDistanceReturnType process(TreeNode root) {
        // base case
        if (root == null) {
            return new MaxDistanceReturnType(0, 0);
        }
        // 默认得到左子树和右子树的信息
        MaxDistanceReturnType leftMaxDistanceReturnType = process(root.left);
        MaxDistanceReturnType rightMaxDistanceReturnType = process(root.right);
        // 三种情况最大距离的比较：左树 右树 root节点
        // 如果最大距离来源是root节点：最大距离=左树高度+右树高度+1
        int maxDistance = Math.max(
                leftMaxDistanceReturnType.height + rightMaxDistanceReturnType.height + 1,
                Math.max(leftMaxDistanceReturnType.maxDistance, rightMaxDistanceReturnType.maxDistance)
        );
        // 高度：左树和右树取最大 然后加上root节点
        int height = Math.max(leftMaxDistanceReturnType.height, rightMaxDistanceReturnType.height) + 1;
        return new MaxDistanceReturnType(height, maxDistance);
    }

    /**
     * @param root
     * @return
     */

    public int getMaxDistance(TreeNode root) {
        if (root == null) {
            return 0;
        }
        MaxDistanceReturnType process = process(root);
        return process.maxDistance;
    }

}
