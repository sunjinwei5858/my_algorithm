package com.sunjinwei._06_array;

/**
 * 数组中超过一半的数
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。 剑指39 力扣169
 */
public class _02_majority_element {

    /**
     * 方法1：使用快排，但是不需要把所有元素排序
     * 超过一半 那么快排之后 中间位置的数肯定超过数组长度一半的数字
     * <p>
     * 执行用时：1 ms, 在所有 Java 提交中击败了99.98%的用户
     * 内存消耗：41.6 MB, 在所有 Java 提交中击败了72.98%的用户
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {

        if (nums == null || nums.length == 0) {
            return -1;
        }
        quickSort(nums, 0, nums.length - 1);
        return nums[nums.length >> 1];
    }

    private void quickSort(int[] nums, int left, int right) {

        // 终止条件1
        if (left >= right) {
            return;
        }
        int randomIndex = left + (int) (Math.random() * (right - left + 1));
        swap(nums, randomIndex, right);
        int[] pivotArr = partition(nums, left, right);
        // 终止条件2：如果已经发现排序后中间的数在中间的化 那么停止递归
        if (pivotArr[0] < (nums.length >> 1) && pivotArr[1] > (nums.length >> 1)) {
            return;
        }
        quickSort(nums, left, pivotArr[0] - 1);
        quickSort(nums, pivotArr[1] + 1, right);
    }

    /**
     * 荷兰国旗问题方法
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    private int[] partition(int[] nums, int left, int right) {
        int less = left - 1;
        int more = right;
        while (left < more) {
            if (nums[left] < nums[right]) {
                less++;
                swap(nums, left, less);
                left++;
            } else if (nums[left] > nums[right]) {
                more--;
                swap(nums, left, more);
            } else {
                left++;
            }
        }
        swap(nums, more, right);
        return new int[]{less - 1, more};
    }


    private void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2};
        _02_majority_element majorityElement = new _02_majority_element();
        int element = majorityElement.majorityElement(arr);
        System.out.println(element);
    }
}
