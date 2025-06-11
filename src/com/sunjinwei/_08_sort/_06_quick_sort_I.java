package com.sunjinwei._08_sort;

import java.util.Arrays;

/**
 * 快排：经典的做法 也需要进行打乱 不打乱 力扣也不通过 报超出时间限制!!!
 * https://blog.csdn.net/zyd196504/article/details/87954669
 * <p>
 * 小于等于分界点pivot的数放左边，大于分界点pivot的放右边
 * Partition方法返回一个int值
 * ps：快排的分治属于 前序遍历 ，因为一上来是先处理根节点的逻辑 然后才是左右子树
 */
public class _06_quick_sort_I {

    public void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        /**
         * 1随机打乱处理: 保证i在[left, right]的随机一个 这是为了概率分布
         */
        int i = left + (int) (Math.random() * (right - left + 1));
        // 这样，分区函数就可以像经典快排一样，以最后一个元素为枢轴进行操作
        swap(arr, i, right);
        /**
         * 2开始partition
         */
        int pivot = partition(arr, left, right);
        /**
         * 3分析：
         * 此时返回的pivot就是分界点 此时的数组 小于pivot的都在左边 大于pivot的都在右边
         * 所以处理左边：[left, pivot-1], 处理右边[pivot+1, right]
         */
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }

    /**
     * partition方法的思想：小于等于分界点的放左边，大于分界点的放右边
     * [low ... i]   :  <=pivot
     * [i+1 ... j-1] :  >pivot
     * [j ... high-1]:  未处理
     * [high] ：=pivot
     * <p>
     * i 指针：始终指向小于或等于枢轴元素的区域的最后一个元素。
     * j 指针：遍历未处理的元素。
     *
     * @param arr
     * @param low
     * @param high
     * @return
     */
    private int partition(int[] arr, int low, int high) {
        if (low > high) {
            return -1;
        }
        if (low == high) {
            return low;
        }
        // i指针用于追踪小于或等于枢轴元素的区域的右边界
        // 初始化为low - 1，表示一开始小于枢轴的区域是空的
        int i = low - 1;
        // 遍历从 low 到 high - 1 的所有元素 (不包括枢轴本身)
        for (int l = low; l < high; l++) {
            // 如果当前元素 arr[j] 小于或等于枢轴
            if (arr[l] <= arr[high]) {
                // 扩展小于等于枢轴的区域的右边界
                i++;
                // 将当前元素与小于区域的下一个元素交换
                swap(arr, i, l);
            }
        }
        // 循环结束后，i 指向了所有小于或等于枢轴元素的最后一个位置
        // 那么 i + 1 的位置就是枢轴应该放置的最终位置
        // 将枢轴 arr[high] 与 arr[i + 1] 交换
        swap(arr, i + 1, high);
        return i + 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        _06_quick_sort_I quickSortI = new _06_quick_sort_I();
        int[] arr = new int[]{18, 15, 13, 17, 6, 20, 15, 9};
        quickSortI.sort(arr);

        System.out.println(Arrays.toString(arr));
    }
}
