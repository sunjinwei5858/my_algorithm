package com.sunjinwei._03_tree02;

import com.sunjinwei.domain.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Morris遍历--神级遍历方法 可以到达O(1)空间
 * 核心1：
 * 因为二叉树从上到下很简单，但是从下到上比较难，所以morries遍历就是想办法从下到上，所以利用了叶子节点大量的null指针，让其指向上一个节点。
 * 这样就可以让二叉树从下到上也有方法可以到达，然后再重新回到叶子节点，将指针恢复到null
 * 核心2：
 * 叶子节点的null指向哪一个上级节点？假设当前节点为curr，找到左子树的最右节点，将最右节点的right指针指向curr，而不是单纯的指向父节点
 */
public class _02_Morris {

    /**
     * Morris序：基本代码
     *
     * @param root
     */
    public void morris(TreeNode root) {

        if (root == null) {
            return;
        }
        // 声明最右变量
        TreeNode mostRight = null;
        // 当前遍历的节点
        TreeNode curr = root;
        // 开始Morries遍历
        while (curr != null) {
            // 找左子树的最右节点
            mostRight = curr.left;
            if (mostRight != null) {
                // 寻找最右节点的逻辑 还需要加上这个最右节点的right指针不指向curr
                while (mostRight.right != null && mostRight.right != curr) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    // 如果最右节点的right指针为null 将right指向curr 【进行调整】
                    mostRight.right = curr;
                    // 更新curr 处理下一个
                    curr = curr.left;
                    // 此时退出这一层的循环
                    continue;
                } else {
                    // 如果最右节点的right指针不为null 那么将right设置为null 【进行恢复】
                    mostRight.right = null;
                }
            }
            // 走到这里，分为两种情况
            // 情况1：curr没有左子树
            // 情况2：curr的左子树 已经遍历完 进行了恢复最右right指针
            curr = curr.right;
        }
    }

    /**
     * Morris序实现前序遍历: 根左右 只打印第一次来到的节点
     *
     * @param root
     */
    public List<Integer> morrisPre(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        // 声明最右变量
        TreeNode mostRight = null;
        // 当前遍历的节点
        TreeNode curr = root;
        // 开始Morries遍历
        while (curr != null) {

            // 找左子树的最右节点
            mostRight = curr.left;
            if (mostRight != null) {

                // 如果存在左子树 找到最右节点 bug 如何找到最右节点!!!! 并且right指针不等于curr
                while (mostRight.right != null && mostRight.right != curr) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    // 第一次来到节点
                    // 如果最右节点的right指针为null 将right指向curr 【进行调整 可以指回curr的指针】
                    mostRight.right = curr;

                    // 前序遍历第一次遇到根节点
                    res.add(curr.val);

                    // 此时处理curr的left
                    curr = curr.left;
                    // 此时退出这一层的循环
                    continue;
                } else {
                    // 第二次来到节点
                    // 如果最右节点的right指针不为null 那么将right设置为null 【进行恢复】
                    mostRight.right = null;
                }
            } else {

                // 说明curr是叶子节点了
                // 前序遍历 遇到左节点 右节点
                res.add(curr.val);
            }
            // 走到这里，分为两种情况
            // 情况1：curr没有左子树
            // 情况2：curr的左子树 已经遍历完 进行了恢复最右right指针
            curr = curr.right;
        }
        return res;
    }


    /**
     * Morris实现中序遍历
     *
     * @param root
     */
    public List<Integer> morrisMiddle(TreeNode root) {

        List<Integer> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        // 声明当前遍历的节点
        TreeNode curr = root;
        // 声明最右节点
        TreeNode mostRight = null;

        while (curr != null) {
            // 当前节点的左子树
            mostRight = curr.left;
            if (mostRight != null) {
                // 寻找左子树中最右的节点
                while (mostRight.right != null && mostRight.right != curr) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    // 第一次遇到 修改叶子节点的right指针
                    mostRight.right = curr;
                    // 更新curr
                    curr = curr.left;
                    // 跳出当前循环
                    continue;
                } else {

                    // 第二次遇到 恢复叶子节点的right指针
                    mostRight.right = null;

                    // 说明此时 curr是根节点 或者是右节点
                    res.add(curr.val);
                }
            } else {
                // 说明此时 curr是叶子节点
                res.add(curr.val);
            }
            // 1没有左子树 2curr的左子树已经遍历
            curr = curr.right;
        }
        return res;
    }


    public static void main(String[] args) {
        TreeNode a = new TreeNode(1);
        TreeNode b = new TreeNode(2);
        TreeNode c = new TreeNode(3);

        a.left = b;
        a.right = c;

        TreeNode d = new TreeNode(4);
        TreeNode e = new TreeNode(5);

        b.left = d;
        b.right = e;

        TreeNode f = new TreeNode(6);
        TreeNode g = new TreeNode(7);

        c.left = f;
        c.right = g;


        _02_Morris morris = new _02_Morris();

        List<Integer> res = morris.morrisMiddle(a);
        System.out.println(res.toString());


    }


}
