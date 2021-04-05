package com.sunjinwei._07_heap;

/**
 * 判断字符数组中是否所有的字符都只出现过一次 【左神】
 * <p>
 * 给定一个字符类型数组chas[]，判断chas中是否所有的字符都只出现过一次，请根据以下不同的两种要求实现两个函数。
 * chas=['a'，'b'，'c']，返回true；chas=['1'，'2'，'1']，返回false。
 * <p>
 * 要求：空间复杂度为O(1)
 */
public class _04_is_unique {

    /**
     * 分析：要满足O(1)空间，那么只能使用堆排序，堆排序是满足O(1)空间的
     * 因为只有将数组进行排序 那么找到相同的字符是很好找的
     *
     * @param arr
     * @return
     */
    public boolean isUnique(char[] arr) {
        if (arr == null || arr.length == 0) {
            return true;
        }
        // 进行堆排序
        heapSort(arr);
        // 此时的arr已经是排序的了
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                return false;
            }
        }
        return true;
    }


    /**
     * 构造大顶堆的方式进行堆排序
     *
     * @param arr
     */
    private void heapSort(char[] arr) {
        // 1 上浮
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        // 2 下沉
        for (int i = arr.length - 1; i > 0; i++) {
            // 将最后一个元素和第一个元素进行交换
            swap(arr, 0, i);
            // 剩余元素继续维护大根堆
            heapify(arr, 0, i);
        }

    }

    private void heapify(char[] arr, int parentIndex, int size) {
        int left = 2 * parentIndex + 1;
        int right = left + 1;
        int largest = left;
        while (left < size) {
            // 首先比较left和right 哪个值更大
            largest = ((right < size && arr[right] > arr[left]) ? right : left);
            // 将较大值和父节点值进行比较
            largest = arr[largest] > arr[parentIndex] ? largest : parentIndex;
            // 如果父节点更大 那么退出循环
            if (largest == parentIndex) {
                break;
            }
            // 否则进行交换
            swap(arr, largest, parentIndex);
            parentIndex = largest;
            left = 2 * parentIndex + 1;
        }


    }

    private void heapInsert(char[] arr, int index) {

        int curr = index;
        int parentIndex = (curr - 1) / 2;
        while (arr[curr] > arr[parentIndex]) {
            // 交换
            swap(arr, curr, parentIndex);
            curr = parentIndex;
            parentIndex = (curr - 1) / 2;
        }
    }

    private void swap(char[] arr, int left, int right) {
        char temp = arr[right];
        arr[right] = arr[left];
        arr[left] = temp;
    }


}
