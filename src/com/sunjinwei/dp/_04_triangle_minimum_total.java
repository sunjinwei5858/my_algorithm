package com.sunjinwei.dp;

import java.util.Arrays;
import java.util.List;

/**
 * 力扣120 三角形最小路径和 triangle 经典的动态规划题目
 * <p>
 * 给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点上。
 * 相邻的结点在这里指的是下标[i,j]与上一层结点下标相同[i+1,j]或者等于上一层结点下标+1的[i+1，j+1]两个结点。
 * [
 * [2],
 * [3,4],
 * [6,5,7],
 * [4,1,8,3]
 * ]
 * 相邻结点：与(i,j)点相邻的结点为(i+1,j)和(i+1,j+1)。
 */
public class _04_triangle_minimum_total {


    /**
     * 解法一 暴力递归 【自顶向下】类似求二叉树的最小值或者最大值 也是使用递归的办法 将左子树和右子树分别求出来
     * <p>
     * 若定义f(i,j)为顶点到(i,j)的最小路径和，则易知递归求解式为:
     * <p>
     * f(i,j)=min(f(i+1,j),f(i+1,j+1))+triangle[i][j]
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        return dfs(triangle, 0, 0);
    }

    private int dfs(List<List<Integer>> triangle, int i, int j) {
        if (triangle.size() == i) {
            return 0;
        }
        // 自顶向下 当前[i,j]位置到顶点[0,0]的路径和+[i,j]位置到底部的路径最小值
        int total1 = dfs(triangle, i + 1, j);
        int total2 = dfs(triangle, i + 1, j + 1);
        Integer current = triangle.get(i).get(j);
        return Math.min(total1, total2) + current;
    }

    /**
     * 解法二：暴力递归+记忆化 【自顶向下】
     */
    public Integer[][] memory;

    public int minmumTotal_02(List<List<Integer>> triangle) {
        int length = triangle.size();
        memory = new Integer[length][length];
        return dfs_02(triangle, 0, 0);
    }

    private int dfs_02(List<List<Integer>> triangle, int i, int j) {
        if (i == triangle.size()) {
            return 0;
        }
        if (memory[i][j] != null) {
            return memory[i][j];
        }
        int total1 = dfs_02(triangle, i + 1, j);
        int total2 = dfs_02(triangle, i + 1, j + 1);
        Integer current = triangle.get(i).get(j);
        return Math.min(total1, total2) + current;
    }

    /**
     * 解法三：动态规划 【自底向上】
     * 定义二维dp数组 将解法二中的【自顶向下的递归】==》【自底向上的递推】
     */
    public int dp(List<List<Integer>> triangle) {
        int n = triangle.size();
        // 状态定义：dp[i][j]表示从[i.j]到底边的最小路径和
        int[][] dp = new int[n + 1][n + 1];
        // 从三角形的最后一行开始递推 自底向上 需要倒序遍历
        for (int i = n - 1; i >= 0; i--) {
            // 因为是三角形 所以内层循环的size可以是外循环的i，也可以是内层集合的size大小
            // 其实内层循环就是遍历triangle[i]对应的集合
            for (int j = 0; j < triangle.get(i).size(); j++) {
                // 状态方程
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }

    /**
     * 解法四：对解法三进行优化 巧妙的使用了一维数组 这种技术处理叫做状态压缩 多思考 提升代码功底
     */
    public int dp_02(List<List<Integer>> triangle) {
        int n = triangle.size();
        // 状态定义：dp[j]表示从[j]到底边的最小路径和
        int[] dp = new int[n + 1];
        // 从三角形的最后一行开始递推
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                // 状态方程
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        _04_triangle_minimum_total triangle_minimum_total = new _04_triangle_minimum_total();
        // [[2],[3,4],[6,5,7],[4,1,8,3]])
        int dp = triangle_minimum_total.dp(Arrays.asList(
                Arrays.asList(2),
                Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7),
                Arrays.asList(4, 1, 8, 3)
                )
        );
        System.out.println(dp);
    }

}
