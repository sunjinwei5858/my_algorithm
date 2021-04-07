package com.sunjinwei._08_sort;

/**
 * 堆排序
 */
public class _07_heap_sort {

    public void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        // 第一步：构造大根堆 堆顶就是最大值
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }

        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, 0, i);
        }

    }

    /**
     * 下沉
     *
     * @param arr
     * @param parentIndex
     * @param heapSize
     */
    private void heapify(int[] arr, int parentIndex, int heapSize) {
        int left = 2 * parentIndex + 1;
        int right = 2 * parentIndex + 2;
        int large;
        while (left < heapSize) {
            // 左右节点比较找出较大者
            large = (right < heapSize) && arr[right] > arr[left] ? right : left;
            // 较大者和父节点比较
            large = arr[large] > arr[parentIndex] ? large : parentIndex;
            // 判断父点是不是比左右孩子节点大,如果父亲节点大 那么不需要交换
            if (large == parentIndex) {
                break;
            }
            // 否则进行交换
            swap(arr, large, parentIndex);
            // 重新赋值 父节点索引改为孩子节点索引
            parentIndex = large;
            left = 2 * parentIndex + 1;
        }

    }

    /**
     * 上浮 构造大根堆
     *
     * @param arr
     * @param index
     */
    private void heapInsert(int[] arr, int index) {
        int parentIndex = (index - 1) / 2;
        while (arr[index] > arr[parentIndex]) {
            swap(arr, index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }

    }

    private void swap(int[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }


}
