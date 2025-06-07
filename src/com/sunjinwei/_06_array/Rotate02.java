package com.sunjinwei._06_array;

/**
 * 189. 轮转数组
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 */
public class Rotate02 {

    /**
     * 这道题和Rotate01很像，这个是数组而已
     * 右旋转 k 位，应该是后 k 位移到前面，前 n-k 位顺序后移。
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        // 声明一个新的数组
        int n = nums.length;
        // 防止k大于n
        k = k % n;
        int[] temp = new int[n];
        // 把后k个元素放到前面
        for (int i = 0; i < k; i++) {
            temp[i] = nums[n - k + i];
        }
        // 把前n-k个元素后移
        for (int i = k; i < n; i++) {
            temp[i] = nums[i - k];
        }
        // 拷贝回原数组
        System.arraycopy(temp, 0, nums, 0, n);
    }

    /**
     * 进阶：原地轮转，空间复杂度o(1)
     * 1。先将整个数组反转一遍
     * 2。这时候，需要轮转的数组就跑到最前面了
     * 3。此时将原数组拆分成两个子数组，从第k处进行拆分，各自进行反转 这样就实现了
     */
    public void rotate2(int[] nums, int k) {
        // 对k进行取模处理
        k = k % nums.length;
        int right = nums.length - 1;
        int left = 0;
        //1 整个数组反转
        reverse(nums, left, right);
        //2 左边前k个反转，为什么这里是k-1，因为数组索引是从0开始
        reverse(nums, left, k - 1);
        // 3右边后k个反转
        reverse(nums, k, right);
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
