package com.sunjinwei.domain;

/**
 * 判断是否是avl所需要的信息体
 */
public class AvlReturnType {

    /**
     * 子树是否平衡
     */
    public boolean balancedIs;

    /**
     * 高度
     */
    public int height;

    public AvlReturnType(boolean balancedIs, int height){
        this.balancedIs = balancedIs;
        this.height = height;
    }

}
