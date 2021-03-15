package com.sunjinwei._03_tree;

import com.sunjinwei.domain.TreeNode;

/**
 * 判断t1树是否包含t2树的全部拓扑结构
 * 进阶：时间复杂度优化到O(N+M) 【左神】
 * 思路：分别将两棵树序列化为字符串，然后使用kmp算法 判断字符串A是否包含字符串B
 */
public class _09_tree1_contains_tree2_II {

    /**
     * 方法：使用kmp 【左神】
     *
     * @param A
     * @param B
     * @return
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {

        // todo 使用kmp算法:
        // todo 只要验证t2Str是否是t1Str的子串即可，这个用KMP算法可以在线性时间内解决。
        // todo 因此，t1序列化的过程为O（N），t2序列化的过程为O（M），KMP解决t1Str和t2Str的匹配问题O（M+N），所以时间复杂度为O（M+N）

        return false;
    }

    /**
     * 将二叉树序列化为字符串
     */
    private String serialize(TreeNode node) {

        return "";
    }

}
