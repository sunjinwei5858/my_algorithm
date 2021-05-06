package com.sunjinwei._08_sort;

import java.util.Arrays;

/**
 * 快排 【荷兰国旗】
 * 分界点应该随机选取，而不是取最后一个或者中间点
 * 思路：有了分界点，就可以把数组通过荷兰国旗问题分成三个部分
 * 左侧：小于分界点
 * 中间值：等于分界点
 * 右侧：大于分界点
 * <p>
 * 这篇博客写的不错
 * https://blog.csdn.net/weixin_42507756/article/details/84444728?utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.control&dist_request_id=&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromMachineLearnPai2%7Edefault-1.control
 */
public class _06_quick_sort_II {

    public void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * 快排逻辑处理：使用荷兰问题解法
     *
     * @param arr
     * @param left
     * @param right
     */
    private void quickSort(int[] arr, int left, int right) {
        // 终止条件
        if (left >= right) {
            return;
        }
        /**
         * 随机打乱arr[right]的值 这里使用arr[right]最后一个元素作为分界点 保证快排的性能为nlogn
         */
        int randomIndex = left + (int) (Math.random() * (right - left + 1));
        swap(arr, randomIndex, right);

        // 使用荷兰国旗寻找分界点范围数组
        int[] pivotArr = partition(arr, left, right);
        quickSort(arr, left, pivotArr[0] - 1);
        quickSort(arr, pivotArr[1] + 1, right);
    }

    /**
     * 荷兰国旗解法
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private int[] partition(int[] arr, int left, int right) {
        // 小于区
        int less = left - 1;
        // 大于区: 默认使用right元素作为分界点 保证了分界点在数组中
        // 默认使用最右边元素为分界点
        int more = right;
        // 当前遍历的索引
        int index = left;
        while (index < more) {
            /**
             * 小于区：arr[i]和小于区的下一个元素【less++】进行置换 index指针向前挪动一位
             * 大于区：arr[i]和大于区的前一个元素【more--】进行置换 ps：index指针不需要向前挪动 因为不确定置换过来的数在哪个区
             * 等于区：不需要操作 只需要index指针++即可
             */
            if (arr[index] < arr[right]) {
                less++;
                swap(arr, less, index);
                index++;
            } else if (arr[index] > arr[right]) {
                more--;
                swap(arr, more, index);
            } else {
                index++;
            }
        }
        /**
         * 处理：将right分界点放置在正确的位置 也就是扩大等于区，
         * 此时more就是大于区的第一个，扩大等于区相当于挤压大于区，也就是占领more的位置
         * 所以返回等于区的数组为{less+1, more}
         */
        swap(arr, more, right);
        return new int[]{less + 1, more};

    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {

        _06_quick_sort_II _06_quick_sort = new _06_quick_sort_II();
        int[] arr = new int[]{2, 3, 1, 9, 7, 6, 1, 4, 5};
        _06_quick_sort.quickSort(arr);
        System.out.println(Arrays.toString(arr));

        System.out.println(arr[arr.length / 2]);

    }

}
