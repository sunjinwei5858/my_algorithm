package com.sunjinwei._02_linkedlist02;

import com.sunjinwei.domain.ListNode;

/**
 * 一种怪异的删除节点的方式
 * 要求：时间复杂度为O(1)
 */
public class _09_Special_Delete_Node_Method {

    /**
     * 该方式存在的问题：
     * 1无法删除最后一个节点 因为没有下一个节点来代替最后一个节点，因为我们无法找到上一个节点
     * 2这种删除方式在本质就不是删除node节点，而是把node节点的值改变，然后删除node的下一个节点，在实际工程中可能会带来很大问题
     *
     * @param node
     */
    public void removeNode(ListNode node) {

        if (node == null) {
            return;
        }
        // 1判断是不是末尾节点 末尾节点不能删除
        if (node.next == null) {
            throw new RuntimeException("can not remove last node");
        }
        // 2否则直接更新节点的val
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
