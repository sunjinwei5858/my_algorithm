package com.sunjinwei._03_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: com.algorithm.week06_tree
 * @author: sun jinwei
 * @create: 2022-01-16 20:19
 * @description: N叉树的前中后序遍历
 **/
public class N_Tree {

    /**
     * N叉树的节点
     */
    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    /**
     * 前序遍历 递归解法1 计算机的执行逻辑
     *
     * @param root
     * @return
     */
    public List<Integer> preorder1(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        res.add(root.val);
        for (int i = 0; i < root.children.size(); i++) {
            List<Integer> subRes = preorder1(root.children.get(i));
            res.addAll(subRes);
        }
        return res;
    }

    /**
     * 前序遍历 递归解法2 定义全局变量 一个一个进行add
     *
     * @param root
     * @return
     */
    List<Integer> res = new ArrayList<>();

    public List<Integer> preorder2(Node root) {
        if (root == null) {
            return res;
        }
        res.add(root.val);
        for (int i = 0; i < root.children.size(); i++) {
            preorder2(root.children.get(i));
        }
        return res;
    }


}