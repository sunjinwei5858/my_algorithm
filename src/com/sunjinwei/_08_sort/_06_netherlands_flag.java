package com.sunjinwei._08_sort;

/**
 * 荷兰国旗问题：
 * 问题1：
 * 给定一个数组arr，和一个数num，请把小于等于num的数放在数 组的左边，大于num的 数放在数组的右边。
 * 最终返回一个整数数组，其中只有两个值，分别是等于K的数组部分的左右两个下标值。
 * 要求额外空间复杂度O(1)，时间复杂度O(N)
 * 问题2：
 * 给定一个数组arr，和一个数num，请把小于num的数放在数组的 左边，等于num的数放 在数组的中间，大于num的数放在数组的 右边。
 * 最终返回一个整数数组，其中只有两个值，分别是等于K的数组部分的左右两个下标值。
 * 要求额外空间复杂度O(1)，时间复杂度 O(N)
 * <p>
 * 例如，给定数组：[2, 3, 1, 9, 7, 6, 1, 4, 5]，给定一个值4，
 * 那么经过处理原数组可能得一种情况是：[2, 3, 1, 1, 4, 9, 7, 6, 5]，
 * 需要注意的是，小于4的部分不需要有序，大于4的部分也不需要有序，返回等于4部分的左右两个下标，即[4, 4]
 * <p>
 */
public class _06_netherlands_flag {

    /**
     * 定义三个指针：
     * 1。一个指向小于区less,刚开始less指向-1处，因为可能不存在
     * 2。一个指向大于区more，刚开始more指向size+1处，因为可能不存在
     * 3。一个指向当前遍历数curr, curr=l
     * 过程：
     * 1.arr[curr]<p, 则将当前数和小于区的下一个数[less+1]进行交换, 当前数继续向右++
     * 2.arr[curr]>p, 则将当前数和大于区的前一个数[more-1]进行交换，当前数保持不动
     * 3.arr[curr]=p, 则当前数直接向右++即可
     *
     * @param arr
     * @param l   用于正在遍历的元素的下标
     * @param r
     * @param p   分界点的值 荷兰问题中p可能不存在数组中
     * @return 在返回less，more的数组中，其中[less+1,more-1]是值 = target的区间。
     */
    public int[] partition(int[] arr, int l, int r, int p) {
        // less 用于记录小于 p 的区域的右下标，初始为-1，代表不存在 【因为p可能不在原数组】
        int less = l - 1;
        // more 用于记录大于 p 区域的左下标，初始为9，代表不存在  【因为p可能不在原数组】
        int more = r + 1;
        // l 用于正在遍历的元素的下标，初始值为0
        while (l < more) {
            // 如果 arr[L] < p, 交换 arr[++ less] 和 arr[L++] 的值
            // 即：当前数和小于区的下一个数进行交换，然后当前数继续向右移动
            if (arr[l] < p) {
                less++;
                swap(arr, less, l);
                l++;
            } else if (arr[l] > p) {
                // 如果 arr[L] > p, 交换 arr[--more] 和 arr[L] 的值
                // 即：当前数和大于区的前一个数进行交换，当前数保持不动
                more--;
                swap(arr, more, l);
            } else {
                // 如果 arr[L] = p, 不交换，L++，直接遍历下一个值
                // 即：此时arr[l]=p 直接进行l++，跳过
                l++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {

    }


}
