package com.sunjinwei._03_tree02;

import com.sunjinwei.domain.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @program: com.sunjinwei._03_tree02
 * @author: sun jinwei
 * @create: 2021-07-22 07:53
 * @description: 变种
 * 二叉树中累加和为给定值的最长路径【左神】
 * 剑指offer34：二叉树中和为某一值的路径
 * 力扣113：二叉树路径总和
 **/
public class _03_Path_Sum {

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
        // 保证函数返回后 map不受污染
        // 因为递归调用返回到上层函数后，后续还可能preOrder()的调用 还要用到map
        // (可以类比"求所有排列组合"题目，也是需要在交换两元素后、返回前，恢复原状)回溯
        if (map.get(currSum) == level) {
            map.remove(currSum);
        }
        return maxLen;
    }

    /**
     * 剑指offer34：二叉树中和为某一值的路径
     * 方法：
     * 1dfs解法
     * 2回溯解法
     */
    public List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        if (root == null) {
            return res;
        }

        // 方法1： dfs
        dfs(root, target, new ArrayList<>());
        // 方法2： 回溯
        backTrack(root, target, new LinkedList<>());

        return res;
    }

    /**
     * dfs解法
     *
     * @param root 遍历的节点
     * @param sum  一直相减
     * @param path 走过的路径集合
     */
    private void dfs(TreeNode root, int sum, ArrayList<Integer> path) {
        // 终止条件1
        if (root == null) {
            return;
        }
        // 因为list是引用传递 为了防止递归的时候 分支污染 需要在每个路径中新建list
        // 因为每次都要新建list 消耗性能 所以可以使用回溯来解决
        ArrayList sub = new ArrayList(path);
        sub.add(root.val);
        // 终止条件2：如果到达叶子节点 也要return
        if (root.left == null && root.right == null) {
            // !!! 在减去叶子节点之前查看sum是否等于叶子节点，如果等于 说明我们找到了一组
            if (sum == root.val) {
                res.add(sub);
            }
            return;
        }
        // 递归处理左子树
        dfs(root.left, sum - root.val, sub);
        // 递归处理右子树
        dfs(root.right, sum - root.val, sub);
    }

    /**
     * 回溯写法1
     *
     * @param root
     * @param sum
     * @param path
     */
    private void backTrack(TreeNode root, int sum, LinkedList<Integer> path) {

        // 终止条件1
        if (root == null) {
            return;
        }
        // 做选择
        path.add(root.val);
        // 终止条件2
        if (root.left == null && root.right == null) {
            if (sum == root.val) {
                res.add(new ArrayList<>(path));
            }
            // 撤销选择
            path.removeLast();
            return;
        }
        backTrack(root.left, sum - root.val, path);
        backTrack(root.right, sum - root.val, path);
        // 撤销选择
        path.removeLast();
    }

    /**
     * 回溯写法2: 终止条件的另外一种写法 但是不建议这么写 因为要把终止条件写在开头比较好
     *
     * @param root
     * @param sum
     * @param path
     */
    private void backTrack2(TreeNode root, int sum, LinkedList<Integer> path) {

        // 做选择
        path.add(root.val);
        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                res.add(new ArrayList<>(path));
            }
            // 撤销选择
            path.removeLast();
            return;
        }

        if (root.left != null) {
            backTrack(root.left, sum - root.val, path);
        }
        if (root.right != null) {
            backTrack(root.right, sum - root.val, path);
        }

        // 我们要理解递归的本质，当递归往下传递的时候他最后还是会往回走，
        // 我们把这个值使用完之后还要把它给移除，这就是回溯
        // 撤销选择
        path.removeLast();
    }

    /**
     * 回溯写法3
     *
     * @param root
     * @param sum
     * @param path
     */
    public void backTrack3(TreeNode root, int sum, LinkedList<Integer> path) {
        // 终止条件1
        if (root == null) {
            return;
        }
        // 逻辑处理1
        path.add(root.val);
        sum -= root.val;
        // 终止条件
        if (root.left == null && root.right == null) {
            // 逻辑处理2
            if (sum == 0) {
                res.add(new ArrayList(path));
            }
            // 撤销选择
            path.removeLast();
            return;
        }
        backTrack3(root.left, sum, path);
        backTrack3(root.right, sum, path);
        // 撤销选择
        path.removeLast();
    }


}
