package com.sunjinwei._04_dp02;

/**
 * 斐波那契数列的几种解法 f(n) = f(n-1) + f(n-2)
 */
public class _02_Fibonacci {

    /**
     * 第一种：递归 o(2^n)内存
     *
     * @param n
     * @return
     */
    public int digui(int n) {

        if (n <= 1) {
            return n;
        }

        return digui(n - 1) + digui(n - 2);
    }

    /**
     * 第二种：使用数组进行缓存，o(n)内存
     */

    private int[] arr;

    public int method2(int n) {
        arr = new int[n + 1];
        arr[0] = 0;
        arr[1] = 1;
        return digui2(n);
    }

    private int digui2(int n) {
        if (arr[n] != 0) {
            return arr[n];
        }
        arr[n] = digui(n - 1) + digui(n - 2);
        return arr[n];
    }

    /**
     * 第三种：动态规划
     */
    public int method3(int n) {
        if (n <= 1) {
            return n;
        }
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


    /**
     * 第四种：状态压缩 o(1)内存
     */
    public int method4(int n) {
        if (n <= 1) {
            return n;
        }
        int prev = 0;
        int curr = 1;
        int res = 0;
        for (int i = 2; i < n; i++) {
            res = curr + prev;
            prev = curr;
            curr = prev;
        }
        return res;
    }


}
