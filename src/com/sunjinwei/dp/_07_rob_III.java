package com.sunjinwei.dp;

import com.sunjinwei.domain.TreeNode;

import java.util.*;

/**
 * 打家劫舍III 力扣337 难度：中等 二叉树 打家劫舍I的变种
 * 描述：
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
 * 这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 注意：
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你不触动警报装置的情况下，一夜之内能够偷窃到的最高金额。
 */
public class _07_rob_III {

    /**
     * 错误解法：力扣也有好多人开始采用这种思路 😂哈哈
     * 自己的思考：层序遍历 [[],[],[]] 但其实是错误解法 因为有些情况 不符合题意，不是层序遍历的做法
     * 比如[2,1,100,null,4] 这棵树，可以选100+4=104 ，而不是2+4=6
     *
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        // 边界条件1
        if (root == null) {
            return 0;
        }
        // 边界条件2 只有根节点
        if (root.left == null && root.right == null) {
            return root.val;
        }
        // 1层序遍历
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> valList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                valList.add(poll.val);
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
            result.add(valList);
        }
        // 2求和转成数组
        int[] nums = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            int sum = 0;
            for (Integer integer : result.get(i)) {
                sum += integer;
            }
            nums[i] = sum;
        }
        // 3得到了数组之后 使用动态规划求解即可
        int m = nums.length;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < m; i++) {
            // 不取
            int s1 = dp[i - 1];
            // 取
            int s2 = dp[i - 2] + nums[i];
            dp[i] = Math.max(s1, s2);
        }
        return dp[m - 1];
    }


    /**
     * 正确解法：
     * 递归+备忘录 二叉树不适合使用数组 这里使用哈希表map来记忆化
     *
     * @param root
     * @return
     */
    public int rob_02(TreeNode root) {
        Map<TreeNode, Integer> hashMap = new HashMap<>();
        return rob_02_helper(hashMap, root);
    }

    private int rob_02_helper(Map<TreeNode, Integer> hashMap, TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (hashMap.containsKey(node)) {
            return hashMap.get(node);
        }
        // 取当前的node
        int money01 = node.val;
        if (node.left != null) {
            money01 += rob_02_helper(hashMap, node.left.left) + rob_02_helper(hashMap, node.left.right);
        }
        if (node.right != null) {
            money01 += rob_02_helper(hashMap, node.right.left) + rob_02_helper(hashMap, node.right.right);
        }
        // 不取当前的node
        int money02 = rob_02_helper(hashMap, node.left) + rob_02_helper(hashMap, node.right);
        int value = Math.max(money01, money02);
        hashMap.put(node, value);
        return value;
    }

    /**
     * 正确解法2：终极解法
     */
    public int rob_03(TreeNode node) {

        int[] result = rob_03_helper(node);
        // 取
        int s1 = result[0];
        // 不取
        int s2 = result[1];
        return Math.max(s1, s2);
    }

    /**
     * 0: 取
     * 1：不取
     *
     * @param node
     * @return
     */
    private int[] rob_03_helper(TreeNode node) {
        if (node == null) {
            return new int[2];
        }
        int[] left = rob_03_helper(node.left);
        int[] right = rob_03_helper(node.right);
        // 声明一个int[]数组
        int[] array = new int[2];
        // 取: 儿子就不能取
        array[0] = node.val + left[0] + right[0];
        // 不取: 儿子可以取 也可以不取
        array[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        return array;
    }


}
