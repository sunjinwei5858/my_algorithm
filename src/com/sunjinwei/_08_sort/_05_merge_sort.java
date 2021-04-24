package com.sunjinwei._08_sort;

/**
 * 归并排序
 * 思想：
 * 1将数组拆成两半，各自对左边和右边进行排序，最后进行合并
 * 2合并的时候声明一个数组，哪个小就先优先放在数组
 * 总结：合并两个有序的数组
 */
public class _05_merge_sort {

    public void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 先提前创建好辅助数组
        // right传入right-1 处理边界问题
        mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * mergesort 处理数组为有序
     *
     * @param arr
     * @param left
     * @param right
     */
    private void mergeSort(int[] arr, int left, int right) {
        if (left == right) {
            return;
        }
        // 寻找中点
        int middle = left + (right - left) >> 1;
        // 处理左边有序
        mergeSort(arr, left, middle);
        // 处理右边有序
        mergeSort(arr, middle + 1, right);
        // 处理归并
        merge(arr, left, middle, right);
    }

    /**
     * merge合并两个有序数组
     *
     * @param arr
     * @param left
     * @param middle
     * @param right
     */
    private void merge(int[] arr, int left, int middle, int right) {
        int[] help = new int[right - left + 1];
        // 声明i变量 用于辅助数组
        int i = 0;
        // 左边开始
        int p1 = left;
        // 右边开始
        int p2 = middle + 1;
        // 归并的过程
        // 边界问题 这里p1可以等于middle p2可以等于right
        while (p1 <= middle && p2 <= right) {
            // 联想到合并两个有序链表 思想其实一样
            // help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
            if (arr[p1] < arr[p2]) {
                help[i] = arr[p1];
                p1++;
            } else {
                help[i] = arr[p2];
                p2++;
            }
            i++;
        }
        //将左边剩余元素填充进temp中
        while (p1 <= middle) {
            help[i] = arr[p1];
            i++;
            p1++;
        }
        //将右序列剩余元素填充进temp中
        while (p2 <= right) {
            help[i] = arr[p2];
            i++;
            p2++;
        }
        //将temp中的元素全部拷贝到原数组中
        for (int j = 0; j < help.length; j++) {
            arr[left + j] = help[j];
        }
    }

}
