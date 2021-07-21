package com.sunjinwei._04_dp02;

/**
 * @program: com.sunjinwei._04_dp02
 * @author: sun jinwei
 * @create: 2021-07-20 23:16
 * @description: 不同的bst 力扣96
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
 **/
public class _01_NumBst {

    /**
     * 使用动态规划解决
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        // n=1 ; n=2 ; n=3

        if (n == 0 || n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int[] dp = new int[n + 1];
        // 初始化数据
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        // 从3开始
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                // 以j为根节点 那么左子树就是[j-1] 右子树就是[i-j]
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        _01_NumBst numBst = new _01_NumBst();
        int trees = numBst.numTrees(3);
        System.out.println(trees);
    }

}