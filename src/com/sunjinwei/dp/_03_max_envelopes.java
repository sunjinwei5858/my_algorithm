package com.sunjinwei.dp;

import java.util.Arrays;

/**
 * 力扣354 俄罗斯套娃信封问题【和最长子序列思想一样 这个是二维，这个是LIT的变种】
 * 思路：
 * 第一种：面积比大小，是不行的，因为1*10和2*3,前者就不能把后者放进去
 * 第二种：降维，重新排序，将二维将到一维，然后使用LIT的算法，将宽度按照从小到大顺序排列，如果宽度相等，那么按照高度从大到小顺序排列
 * 该问题是输入是按任意顺序排列的——我们不能直接套用标准的 LIS 算法，需要先对数据进行排序。我们如何对数据进行排序，以便我们的 LIS 算法总能找到最佳答案？
 *
 */
public class _03_max_envelopes {

    /**
     * 解法：排序+最长递增子序列
     * 思想：将二维-->一维
     *
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {
        // 二维数组的长度
        int length = envelopes.length;
        // 排序 按照宽度从小到大，如果宽度相同，高度从大到小
        // 简洁写法 本题的二维数组就只有两列
        Arrays.sort(envelopes, (a, b) -> {
            {
                // 宽度从小到大 二维数组 第一列是宽度
                if (a[0] != b[0]) {
                    return a[0] - b[0];
                }
                // 宽度相同 高度从大到小 第二列是高度
                return b[1] - a[1];
            }
        });

        // 降维 将高度 放入LIS算法中即可
        int[] high = new int[length];
        for (int i = 0; i < length; i++) {
            high[i] = envelopes[i][1];
        }
        // 进行LIS算法
        // 状态的确定
        int[] dpTable = new int[length];
        // 填充
        Arrays.fill(dpTable, 1);
        // 状态转移方程：数学归纳法思想
        int result = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < i; j++) {
                if (high[i] > high[j]) {
                    dpTable[i] = Math.max(dpTable[i], dpTable[j] + 1);
                }
            }
            result = Math.max(result, dpTable[i]);
        }
        return result;
    }


}
