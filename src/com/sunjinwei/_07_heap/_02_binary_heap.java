package com.sunjinwei._07_heap;

import java.util.PriorityQueue;

/**
 * 二叉堆的实现原理：参考PriorityQueue的源码
 * https://blog.csdn.net/qq_36186690/article/details/82505569
 * <p>
 * 堆是具有以下性质的完全二叉树
 * 大顶堆：每个节点的值都大于或等于左右孩子节点的值
 * 小顶堆：每个节点的值都小于或等于 左右孩子节点的值
 * 注意：兄弟节点也就是同一层的节点的值谁大谁小 不确定
 * ps：回顾以下完全二叉树的必须满足的条件 1有右孩子，但是没有左孩子，返回false 2当前节点并不都有左右孩子 那么下一个节点必须是叶子节点，否则返回false
 * <p>
 * 底层数据结构：数组实现，按层遍历!!!!!!
 * 简单的公式：如果父节点的索引为i
 * 大顶堆: arr[i]>=arr[2i+1] && arr[i]>=arr[2i+2]
 * 小顶堆: arr[i]<=arr[2i+1] && arr[i]<=arr[2i+2]
 * <p>
 * 插入复杂度：O(logn)
 * 删除复杂度：O(logn)
 * 构造复杂度: O(logn)
 */
public class _02_binary_heap {

    private Integer[] arr = new Integer[10];

    private int size;

    /**
     * 1插入，往堆中插入元素，构造大顶堆
     * 基本思想：从最后一个元素开始，通过不断上浮操作不断调整位置，直到满足父节点的优先级必定大于子节点这个条件
     * 注意：
     * 1。先不考虑数组扩容的情况
     * 2。假设size为当前堆包含的元素个数【注意并不等于上面定义的10】
     */
    public void insert(Integer val) {
        if (size == 0) {
            arr[0] = val;
        } else {
            // 数组元素个数为size 最后一个元素的索引就是size-1
            siftUp(size, val);
            size++;
        }
    }

    /**
     * 上浮：不断的构造大顶堆，父亲必定大于子节点
     * <p>
     * 基本思想：从最后一个元素开始，通过不断上浮操作不断调整位置，直到满足父节点的优先级必定大于子节点这个条件
     *
     * @param k   数组中元素的个数 并不等于数组的length
     * @param val 需要添加的元素
     */
    private void siftUp(int k, Integer val) {
        while (k > 0) {
            // 获取最后一个元素的父元素的位置 parent=(k-1)/2
            int parent = (k - 1) >>> 1;
            // 如果父元素的值大于当前元素 跳出循环
            if (arr[parent] >= val) {
                break;
            }
            // 如果父元素的值小于当前元素 那么进行交换
            // 父元素赋值到k的位置，更改k为父元素的位置
            // 继续循环
            arr[k] = arr[parent];
            // 将parent赋值给k 是为了比较父节点的父节点 一直往上比较 直到parent=0
            k = parent;
        }
        arr[k] = val;
    }


    /**
     * 2删除，这里实现的方法为根据索引删除元素, 删除也是需要构造大顶堆 也就是父亲大于左右孩子
     * 基本思想：从最后一个元素开始，交换索引p的元素，需要利用公式：父亲大于左孩子并且大于右孩子
     *
     * @param index
     */
    public void delete(int index) {
        // 如果p是最后一个元素
        if (index == size - 1) {
            size--;
            return;
        }
        // 获取最后一个元素的值
        Integer lastVal = arr[size - 1];
        // 如果p不是最后一个元素, 把p和最后一个元素进行下沉
        siftDown(index, lastVal);
        if (arr[index].equals(lastVal)) {
            // 上浮
            siftUp(index, lastVal);
        }
        size--;
    }

    /**
     * 下沉：不断构造大顶堆，公式：父亲大于左孩子并且父亲大于右孩子
     *
     * @param index   被删除元素的位置
     * @param lastVal 堆的最后一个元素
     */
    private void siftDown(int index, Integer lastVal) {
        // 1找到最后一个元素的父节点索引
        int parent = (size - 1) >>> 1;
        // 2判断删除节点是不是在最后一个元素父节点的上面 如果是 那么需要下沉
        while (index <= parent) {
            // 3获取k的左右孩子索引
            int left = 2 * index + 1;
            int right = left + 1;
            // 4在左右孩子中找最大
            int best = arr[left] > arr[right] ? left : right;
            // 5判断最后一个元素的值和best的哪个值大
            if (lastVal >= arr[best]) {
                // 如果最后一个元素更大 那么跳出循环
                // 直接赋值 因为此时lastVal可以做左右孩子的父节点
                break;
            }
            // 否则继续下沉
            // 交换父亲和左右孩子中较大的那个
            arr[index] = arr[best];
            index = best;
        }
        // 赋值
        arr[index] = lastVal;
    }


    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(1);
        priorityQueue.offer(2);
        priorityQueue.offer(3);

        priorityQueue.poll();

    }

}
