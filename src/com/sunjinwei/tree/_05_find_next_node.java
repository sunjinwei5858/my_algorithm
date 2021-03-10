package com.sunjinwei.tree;

import com.sunjinwei.domain.ParentTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 在二叉树中找到一个节点的后继节点
 * 注意：后继节点和前驱节点都是指的中序遍历，左根右
 */
public class _05_find_next_node {

    /**
     * 方法1：时间复杂度和空间复杂度都为O(L): node到node后继节点这条路径上的节点 【最优解法】
     *
     * @param node
     * @return
     */
    public ParentTree findNextNode(ParentTree node) {
        // 鲁棒性1
        if (node == null) {
            return null;
        }
        // 情况1: right不为空 那么后继节点就是右子树中最左的节点 后继节点并不是直接就是右孩子节点!!!! 考虑node为根节点的情况
        if (node.right != null) {
            return getMostLeft(node.right);
        }
        // 情况2: right为空，根据parent指针找到parent节点，然后判断node是parent的左孩子还是右孩子
        // 如果是左孩子节点，那么此时node的父节点就是node的后继节点；
        // 如果是右孩子节点，就向上寻找node的后继节点，
        // 假设向上移动到的节点记为s，s的父节点记为p，【如果发现s是p的左孩子节点，那么节点p就是node节点的后继节点】，否则就一直向上移动。
        ParentTree parent = node.parent;
        // 这段代码已经考虑了上面的两种情况 非常好
        // node要么是parent的左孩子 要么就是右孩子
        // 如果是左孩子 那么直接返回parent
        // 如果是右孩子 进入while循环 一直向上找 规律：
        while (parent != null && parent.right == node) {
            node = parent;
            parent = node.parent;
        }
        return parent;
    }

    private ParentTree getMostLeft(ParentTree node) {
        if (node == null) {
            return null;
        }
        // 一直继续找左节点
        while (node.left != null) {
            ParentTree left = node.left;
            node = left;
        }
        return node;
    }

    /**
     * 方法2：常规解法，根据parent指针一路找到头节点 然后中序遍历生成数组，在数组里面找到后继节点
     * 时间和空间复杂度都为O(n)
     */
    public ParentTree findNextNode_02(ParentTree node) {
        // 鲁棒性1
        if (node == null) {
            return null;
        }
        int target = node.val;
        // 寻找根节点
        ParentTree root = node;
        while (root.parent != null) {
            root = root.parent;
        }
        // 使用迭代完成中序遍历
        List<Integer> arr = new ArrayList<>();
        Stack<ParentTree> parentTreeStack = new Stack<>();
        while (root != null || !parentTreeStack.isEmpty()) {
            if (root != null) {
                // 第一次遇见
                parentTreeStack.push(root);
                root = root.left;
            } else {
                // 第二次遇见 处理
                ParentTree pop = parentTreeStack.pop();
                arr.add(pop.val);
                root = pop.right;
            }
        }
        int index = -1;
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) == target) {
                index = i + 1;
            }
        }
        if (index < arr.size()) {
            return new ParentTree(arr.get(index));
        }
        return null;
    }


}
