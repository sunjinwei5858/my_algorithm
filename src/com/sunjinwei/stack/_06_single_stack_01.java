package com.sunjinwei.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * 单调栈:情景1：输入一个数组nums={2,1,2,4,3}, 返回[4,2,4,-1,-1]
 * 找到比当前元素更大的下一个元素
 */
public class _06_single_stack_01 {

    /**
     * 方法一：倒序遍历，先保存的是当前元素的对应关系
     */
    public int[] nextGreaterElement(int[] nums) {
        // 声明数组
        int[] result = new int[nums.length];
        // 声明栈
        Stack<Integer> stack = new Stack<>();
        // 倒着遍历 是为了迎合栈的先进后出 保证元素正着出栈
        for (int i = nums.length - 1; i >= 0; i--) {
            // 只要栈顶的元素小于当前元素的值 那么弹出
            while (!stack.isEmpty() && nums[i] >= stack.peek()) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                result[i] = -1;
            } else {
                result[i] = stack.peek();
            }
            stack.push(nums[i]);
        }
        return result;
    }

    /**
     * 方法二：正序遍历，先保存的是上一个元素的对应关系，存储值
     * <p>
     * 我们首先把第一个元素nums2[1] 放入栈，随后对于第二个元素nums2[2]，
     * 如果 nums2[2] > nums2[1]，那么我们就找到了 nums2[1] 的下一个更大元素nums2[2]
     * 此时就可以把nums2[1]出栈,并把 nums2[2] 入栈
     *
     * @param nums
     */
    public int[] nextGreaterElement_02(int[] nums) {

        Stack<Integer> stack = new Stack<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 1此时如果当前元素大于栈顶的元素，说明栈顶元素的下一个最大值就是此时的当前元素
            // 将对应关系存储到map中
            while (!stack.isEmpty() && nums[i] > stack.peek()) {
                map.put(stack.pop(), nums[i]);
            }
            // 2将当前元素进栈
            stack.push(nums[i]);
        }
        // 3判断此时的stack是否为空 不为空对应的元素 都是-1
        while (!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }
        int[] result = new int[nums.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = map.get(nums[i]);
            // 如果采取下面这种方式 那么就不需要上面的while判断处理了
            result[i] = map.getOrDefault(nums[i], -1);
        }
        return result;
    }

    /**
     * 写法三：直接存储索引，需要注意的是：防止索引越界
     *
     * @param nums
     */
    public int[] nextGreaterElement_03(int[] nums) {

        Stack<Integer> stack = new Stack<>();
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        for (int i = 0; i < nums.length; i++) {
            // 1此时如果当前元素大于栈顶的元素，说明栈顶元素的下一个最大值就是此时的当前元素
            // 将对应关系存储到数组中
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                result[stack.pop()] = nums[i];
            }
            // 2将当前元素的索引进栈
            stack.push(i);
        }
        return result;
    }


    public static void main(String[] args) {
        _06_single_stack_01 singleStack = new _06_single_stack_01();
        int[] arr = new int[]{2, 1, 2, 4, 3};
        int[] elements = singleStack.nextGreaterElement_03(arr);
        System.out.println(Arrays.toString(elements));
    }
}
