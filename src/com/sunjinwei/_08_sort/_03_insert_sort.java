package com.sunjinwei._08_sort;

/**
 * 插入排序
 * 思路：
 * 第一次[0,0]: 不需要进行比较
 * 第二次[0,1]: 从1开始看，[1]和[0]进行比较，如果更小，进行交换，然后再和i-1进行比较
 * 第三次[0,2]: 从2开始看，[2]和[1]进行比较，如果更小，进行交换，然后[1]和[0]进行比较
 */
public class _03_insert_sort {

    public void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 外循环：如果数组只有一个元素是不需要进行排序的 所以i从1开始
        for (int i = 1; i < arr.length; i++) {
            // 内循环：j需要从i-1开始 比较和j+1的大小，其实第一次j+1=i, 但是这里不能换成i，因为需要不断的往前看 往前调整
            for (int j = i - 1; j >= 0; j++) {
                // 利用j+1和j进行比较 不断的往前看
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    private void swap(int[] arr, int left, int right) {
        int temp = arr[right];
        arr[right] = arr[left];
        arr[left] = temp;
    }


}
