package com.sunjinwei._06_array;

/**
 * 数组中超过一半的数
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。 剑指39
 */
public class _02_majority_element {

    private int result;

    /**
     * 思路：
     * 荷兰国旗
     * 超过一半 如果将数组进行排序 那么该数字必然在中间
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {

        if (nums == null || nums.length == 0) {
            return -1;
        }

        quickSort(nums, 0, nums.length - 1);

        return nums[(nums.length) / 2];

    }

    private void quickSort(int[] nums, int left, int right) {

        if (left >= right) {
            return;
        }

        int randomIndex = left + (int) (Math.random() * (right - left + 1));

        swap(nums, randomIndex, right);

        int[] pivotArr = partition(nums, left, right);

        quickSort(nums, left, pivotArr[0] - 1);

        quickSort(nums, pivotArr[1] + 1, right);

    }

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

    public int majorityElement_02(int[] nums) {

        if (nums == null || nums.length == 0) {
            return -1;
        }

        int right = nums.length - 1;

        int middle = right >> 1;

        int left = 0;

        int randomIndex = left + (int) (Math.random() * (right - left + 1));

        swap(nums, randomIndex, right);

        int[] pivotArr = partition(nums, left, right);

        while (pivotArr[1] < middle || pivotArr[0] > middle) {
            if (pivotArr[0] > (nums.length / 2)) {
                // 左移
                pivotArr = partition(nums, left, pivotArr[0] - 1);
            } else if (pivotArr[1] < middle) {
                // 右移动
                pivotArr = partition(nums, pivotArr[1] + 1, right);
            }
        }
        return nums[(nums.length) / 2];

    }


    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2};
        _02_majority_element majorityElement = new _02_majority_element();
        int element = majorityElement.majorityElement_02(arr);
        System.out.println(element);
    }
}
