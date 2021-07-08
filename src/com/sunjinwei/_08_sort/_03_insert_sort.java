package com.sunjinwei._08_sort;

import java.util.Arrays;

/**
 * 插入排序
 * 思路：
 * 第一次[0,0]: 不需要进行比较
 * 第二次[0,1]: 从1开始看，[1]和[0]进行比较，如果更小，进行交换，然后再和i-1进行比较
 * 第三次[0,2]: 从2开始看，[2]和[1]进行比较，如果更小，进行交换，然后[1]和[0]进行比较
 * <p>
 * 比如已经排好序的数组为[1,4,5] 现在要插入3
 * 那么让3和5进行比较，比5更小 那么进行交换，此时变成[1,4,3,5]，
 * 继续往前进行比较，3和4进行比较，进行交换，此时变成[1,3,4,5]
 */
public class _03_insert_sort {

    /**
     * 第一种写法
     *
     * @param arr
     */
    public void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 外循环：如果数组只有一个元素是不需要进行排序的 所以i从1开始
        for (int i = 1; i < arr.length; i++) {
            // 内循环：j需要从i-1开始 比较和j+1的大小，其实第一次j+1=i, 但是这里不能换成i，因为需要不断的往前看 往前调整
            for (int j = i - 1; j >= 0; j--) {
                // 利用j+1和j进行比较 不断的往前看
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    /**
     * 第二种写法
     *
     * @param arr
     */
    public void insertSort2(int[] arr) {
        // 鲁棒性：当数组只有一个元素时 不需要进行排序
        if (arr == null || arr.length < 2) {
            return;
        }
        // 外循环 直接从1开始
        for (int i = 1; i < arr.length; i++) {
            // 内循环不断的往前看
            for (int j = i; j > 0; j--) {
                if (arr[j - 1] > arr[j]) {
                    swap(arr, j, j - 1);
                }
            }
        }
    }

    private void swap(int[] arr, int left, int right) {
        int temp = arr[right];
        arr[right] = arr[left];
        arr[left] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 5, 4, 4};
        _03_insert_sort insertSort = new _03_insert_sort();
        insertSort.insertSort2(arr);
        System.out.println(Arrays.toString(arr));
    }


}
