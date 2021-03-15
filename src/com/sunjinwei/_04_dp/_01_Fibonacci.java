package com.sunjinwei._04_dp;

/**
 * 一个入门的动态规划例子--斐波那契数列 力扣509
 * f(n) = f(n-1) + f(n-2)
 * 注意：严格意义上来说，斐波那契数列不算动态规划 因为不涉及最优子结构【不涉及求最值】
 * 递归+记忆化==》递推
 * 状态转移方程：都是相邻的状态递推出来的
 */
public class _01_Fibonacci {

    /**
     * 解法1：纯递归
     *
     * @param n
     * @return
     */
    public int fib_01(int n) {
        // 使用三元运算符 代码简洁
        return n <= 1 ? n : fib_01(n - 1) + fib_01(n - 2);
    }

    /**
     * 解法2：带备忘录的递归解法-sjw
     * 自己的方式：重复计算过的f(n) 使用一个数组记录下来 自顶向下 因为还是使用了递归
     *
     * @param n
     * @return
     */
    public int fib_sjw_02(int n) {
        // 1备忘录
        int[] memeory = new int[n + 1];
        // 2辅助函数 用于递归
        return fib_sjw_helper(n, memeory);
    }

    // 辅助函数
    private int fib_sjw_helper(int n, int[] memeory) {
        if (n <= 1) {
            return n;
        }
        if (memeory[n] == 0) {
            // 3递归 自顶向下的递归
            memeory[n] = fib_sjw_helper(n - 1, memeory) + fib_sjw_helper(n - 2, memeory);
        }
        return memeory[n];
    }

    /**
     * 解法3：真正意义上的动态规划解法：自底向上==》 迭代解法+dpTable
     * 使用自底向上 f(0) f(1) ===》 f(n)
     * 递推公式：f(i) = f(i-1)+f(i-2)
     */
    public int fib_dp_03(int n) {
        if (n <= 1) {
            return n;
        }
        // 1.dpTable
        int[] dpTable = new int[n + 1];
        dpTable[0] = 0;
        dpTable[1] = 1;
        for (int i = 2; i <= n; i++) {
            // 2.状态转移方程
            dpTable[i] = dpTable[i - 1] + dpTable[i - 2];
        }
        return dpTable[n];
    }

    /**
     * 解法4：动态规划：状态压缩+自底向上
     * 因为斐波那契数列只和n-1，n-2的值相关，也就是不需要dpTable那么长的表存储，将二维转化为一维即可
     */
    public int fib_dp_04(int n) {
        if (n <= 1) {
            return n;
        }
        // n=0的值
        int prev = 0;
        // n=1的值
        int current = 1;
        // 状态压缩：只需要记录上一个和当前值即可
        for (int i = 2; i <= n; i++) {
            int sum = prev + current;
            prev = current;
            current = sum;
        }
        return current;
    }


}
