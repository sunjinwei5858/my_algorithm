package com.sunjinwei._03_tree;

import com.sunjinwei.domain.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 二叉树的最近公共祖先 进阶  左神
 * 进阶问题：如果查询两个节点的最近公共祖先的操作十分频繁，想法让单条查询的查询时间减少。
 */
public class _13_lowest_common_ancestor_II {

    /**
     * 方法：存储父节点
     * 思路：
     * 我们可以用哈希表存储所有节点的父节点，然后我们就可以利用节点的父节点信息从 p 结点开始不断往上跳，
     * 并记录已经访问过的节点，再从 q 节点开始不断往上跳，如果碰到已经访问过的节点，
     * 那么这个节点就是我们要找的最近公共祖先。
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    /**
     * 全局哈希表存储 key：节点的val值  value：最近的祖先node节点
     */
    private HashMap<Integer, TreeNode> hashMap = new HashMap();
    /**
     * 访问过的路径
     */
    private Set<Integer> path = new HashSet<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 从根节点开始遍历整棵二叉树，用哈希表记录每个节点的父节点指针。
        dfs(root);
        // 处理p：从p节点开始不断往它的祖先移动，并用数据结构记录已经访问过的祖先节点。
        while (p != null) {
            path.add(p.val);
            p = hashMap.get(p.val);
        }
        // 处理q：同样，我们再从q节点开始不断往它的祖先移动，如果有祖先已经被访问过，即意味着这是p和q的深度最深的公共祖先，即LCA节点。
        while (q != null) {
            if (path.contains(q.val)) {
                return q;
            }
            q = hashMap.get(q.val);
        }
        return null;
    }

    private void dfs(TreeNode root) {
        if (root.left != null) {
            hashMap.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            hashMap.put(root.right.val, root);
            dfs(root.right);
        }
    }
}
