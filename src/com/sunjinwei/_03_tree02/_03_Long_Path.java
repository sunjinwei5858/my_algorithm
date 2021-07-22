package com.sunjinwei._03_tree02;

import com.sunjinwei.domain.TreeNode;

import java.util.HashMap;

/**
 * @program: com.sunjinwei._03_tree02
 * @author: sun jinwei
 * @create: 2021-07-22 07:53
 * @description: 变种 二叉树中累加和为给定值的最长路径
 **/
public class _03_Long_Path {

    /**
     * 最长路径，也就是层的数量，这道题可以联想到数组中为给定值的最长子数组长度，然后又可以联想到两数之和
     *
     * @param root
     * @return
     */
    public int longPath(TreeNode root, int sum) {

        // 使用hashmap存储 key为：路径和 value：层数
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        // 初始化 重要!!!!
        hashMap.put(0, 0);
        // 头节点对应的level就是1
        return preOrder(root, 0, sum, 0, 1, hashMap);
    }

    /**
     * @param curr   当前遍历的节点
     * @param maxLen 最长路径
     * @param sum    题目中要求的sum
     * @param preSum 从head到curr父节点的sum
     * @param level  层数
     * @param map    记录sum对应的level
     * @return
     */
    private int preOrder(TreeNode curr, int maxLen, int sum, int preSum, int level, HashMap<Integer, Integer> map) {
        if (curr == null) {
            return maxLen;
        }
        // 计算head到curr的路径和
        int currSum = preSum + curr.val;
        // 判断是否存在
        if (!map.containsKey(currSum)) {
            map.put(currSum, level);
        }
        // 处理以curr结尾的情况下 累加和为指定长度sum 这就是两数之和的思想
        if (map.containsKey(sum - currSum)) {
            maxLen = Math.max(level - map.get(sum - currSum), maxLen);
        }
        // 递归处理左子树
        maxLen = preOrder(curr.left, maxLen, sum, currSum, level + 1, map);
        // 递归处理右子树
        maxLen = preOrder(curr.right, maxLen, sum, currSum, level + 1, map);
        // 别忘记 remove操作!!!!
        if (map.get(currSum) == level) {
            map.remove(currSum);
        }
        return maxLen;
    }

}