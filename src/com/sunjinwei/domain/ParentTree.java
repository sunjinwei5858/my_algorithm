package com.sunjinwei.domain;

/**
 * 除了有左右指针，还有父指针
 */
public class ParentTree {
    public int val;
    public ParentTree left;
    public ParentTree right;
    public ParentTree parent;

    public ParentTree(int val) {
        this.val = val;
    }

}
