package com.sunjinwei._07_heap;

/**
 * 实现堆排序
 */
public class _02_heap_sort {

    /**
     * 堆排序实现：
     * 思路：先将数组构造大顶堆，堆顶也就是数组第一个元素就是最大值，此时置换到数组的最后一个位置，固定住，然后将剩余的元素继续维护大顶堆
     * 所以这里
     * 第一次构造大顶堆：上浮
     * 第二次构造大顶堆：下沉
     *
     * @param arr
     */
    public void heapSort(int[] arr) {
        // 第一步：构造大顶堆
        heapInsert(arr);
        // 第二步：此时最顶部元素就是最大值 将它和数组最后一个元素进行交换 并且固定 调整剩余的元素
        int length = arr.length;
        while (length > 1) {
            // 固定最大值: 此时数组的第一个索引为最大值 和最后一个元素进行交换
            swap(arr, 0, length - 1);
            // 此时固定最大值，size-- 数组最后一个元素不参与
            length--;
            // 构造大顶堆
            heapify(arr, 0, length);
        }
    }

    /**
     * 第二步：第二次构造大顶堆【通过顶端的元素下沉】
     *
     * @param arr
     * @param parentIndex
     * @param length
     */
    private void heapify(int[] arr, int parentIndex, int length) {
        // 左孩子索引
        int left = 2 * parentIndex + 1;
        // 右孩子索引
        int right = 2 * parentIndex + 2;
        // 因为堆是完全二叉树 右孩子可以没有 但是左孩子一定要有
        while (left < length) {
            // 如果父亲节点大于左右孩子 已经是大顶堆 退出循环
            if (arr[parentIndex] > arr[left] && arr[parentIndex] > arr[right]) {
                break;
            }
            // 否则交换元素
            int largestIndex = ((arr[left] > arr[right]) && (right < length)) ? left : right;
            swap(arr, parentIndex, largestIndex);
            // 交换索引
            parentIndex = largestIndex;
            left = 2 * parentIndex + 1;
            right = 2 * parentIndex + 2;
        }

    }

    /**
     * 第一步：第一次构造大顶堆【通过顶端的元素上升】
     *
     * @param arr
     */
    private void heapInsert(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 当前索引
            int curr = i;
            // 父节点的索引
            int parent = (curr - 1) / 2;
            // 大顶堆条件：父节点的值大于当前索引的值
            while (arr[parent] < arr[curr]) {
                // 走到这里 说明需要交换
                swap(arr, parent, curr);
                // 更新索引
                curr = parent;
                parent = (curr - 1) / 2;
            }
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

}
