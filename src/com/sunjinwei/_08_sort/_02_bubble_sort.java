package com.sunjinwei._08_sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 思路：
 * 第一次：找[0,n]位置，从第一个元素和第二个元素相邻进行比较交换，到n的时候，就是最大值，此时最大值放在最右边，也就是[n]位置
 * 第二次：找[0,n-1]位置，和第一次步骤一样，以此类推，到n-1的时候，就是最大值，此时最大值放在最右边，也就是[n-1]位置
 */
public class _02_bubble_sort {

    public void bubbleSort(int[] arr) {
        // 鲁棒性
        if (arr == null || arr.length < 2) {
            return;
        }
        // 冒泡排序
        for (int i = arr.length - 1; i > 0; i--) {
            // 提前退出冒泡循环的标识 数组如果已经有序 那么就不需要进行遍历了 直接跳出循环
            boolean flag = false;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    System.out.println("冒泡....");
                    swap(arr, j, j + 1);
                    // 表示有数据交换
                    flag = true;
                }
            }
            // 没有数据交换 提前退出 【冒泡排序的优化】
            if (!flag) {
                break;
            }
        }
    }

    private void swap(int[] arr, int left, int right) {
        int temp = arr[right];
        arr[right] = arr[left];
        arr[left] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 3};
        _02_bubble_sort bubble_sort = new _02_bubble_sort();
        bubble_sort.bubbleSort(arr);

        System.out.println(Arrays.toString(arr));


    }

}
