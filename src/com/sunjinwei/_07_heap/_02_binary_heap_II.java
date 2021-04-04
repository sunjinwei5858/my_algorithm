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
public class _02_binary_heap_II {

    private int[] arr = new int[10];

    private int size;

    /**
     * 1插入，往堆中插入元素，构造大顶堆
     * 基本思想：从最后一个元素开始，通过不断上浮操作不断调整位置，直到满足父节点的优先级必定大于子节点这个条件
     * 注意：
     * 1。先不考虑数组扩容的情况
     * 2。假设size为当前堆包含的元素个数【注意并不等于上面定义的10】
     */
    public void insert(int val) {
        // 将val放置最后一个位置
        arr[size] = val;
        heapInsert(size);
        // 这里控制size比当前元素更大一个 和heapify方法的right<heapSize呼应
        size++;
    }

    /**
     * 上浮的方法：代码简介 逻辑也简介【左神】
     * 基本思想：当前节点一直往上找父亲节点 比较值 如果比父亲节点更大 那么进行交换
     *
     * @param index
     */
    private void heapInsert(int index) {
        // 1 arr[index] > arr[(index - 1) / 2] 当前节点和父亲节点比较
        // 2 index=0 也会停止循环 当前节点到了父亲节点
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 交换元素的方法
     *
     * @param arr
     * @param left
     * @param right
     */
    private void swap(int[] arr, int left, int right) {
        int temp = arr[right];
        arr[right] = arr[left];
        arr[left] = temp;
    }


    /**
     * 2删除大根堆的最大值并且返回：剩余的数仍然需要构造成大根堆
     * 基本思想：从最后一个元素开始，交换索引p的元素，需要利用公式：父亲大于左孩子并且大于右孩子
     */
    public int pop() {
        // 数组第一个元素就是大根堆的顶部
        int res = arr[0];
        // 最后一个元素的索引是size-1 所以这里需要提前size--
        size--;
        // 将最后一个元素和顶部元素也就是索引0位置的元素进行交换
        swap(arr, 0, size);
        // 剩下的数依然保持大根堆
        heapify(arr, 0, size);
        return res;
    }


    /**
     * 下沉方法：一直往下寻找 比较父亲节点和左右孩子节点的值，哪个大就替换哪个
     *
     * @param arr
     * @param parentIndex 父亲节点的索引
     * @param heapSize    此时的heapSize就是已经剔除了一个元素的size 即剩余的元素需要继续维护大根堆
     */
    private void heapify(int[] arr, int parentIndex, int heapSize) {
        // 左孩子索引
        int left = 2 * parentIndex + 1;
        // 堆可以没有右孩子 但是必须有左孩子 否则就退出循环
        while (left < heapSize) {
            // 找出左右孩子哪个值更大的索引
            // 最大值是右孩子的条件：右孩子索引也必须小于heapSize 为什么不是等于 因为insert方法 我们让size比本身元素个数+1了
            int largeIndex = ((left + 1 < heapSize && arr[left] > arr[left + 1])) ? left : (left + 1);
            // 和父亲节点的值比较
            largeIndex = arr[largeIndex] > arr[parentIndex] ? largeIndex : parentIndex;
            if (largeIndex == parentIndex) {
                break;
            }
            // 否则交换元素
            swap(arr, largeIndex, parentIndex);
            // 继续下一轮循环
            parentIndex = largeIndex;
            left = 2 * parentIndex + 1;
        }
    }


    public static void main(String[] args) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(1);
        priorityQueue.offer(2);
        priorityQueue.offer(3);

        priorityQueue.poll();

    }

}
