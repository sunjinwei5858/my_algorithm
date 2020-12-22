package com.sunjinwei.bfs_dfs_huisu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 全排列 回溯算法（dfs）
 * <p>
 * 请大家做了一些回溯算法的问题以后顺便思考一下：深度优先遍历、递归、栈，它们三者的关系，
 * 我个人以为它们背后统一的逻辑都是「后进先出」。完成练习有助于我们深刻理解算法思想，我们加油！
 */
public class _02_quan_pailie {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> list = new LinkedList<>();
        huisu(nums, list);
        return result;
    }

    private void huisu(int[] nums, List<Integer> list) {
        // 递归终止条件
        if (nums.length == list.size()) {
            /**
             * 在 Java 中，参数传递是 值传递，对象类型变量在传参的过程中，复制的是变量的地址。
             * 这些地址被添加到 res 变量，但实际上指向的是同一块内存地址，因此我们会看到 6 个空的列表对象。
             * 解决的方法很简单，在 res.add(path); 这里做一次拷贝即可。
             *
             */
            System.out.println("===="+list.toString());
            result.add(new ArrayList<>(list));
            return;
        }
        for (int num : nums) {
            // 做选择
            if (list.contains(num)) {
                continue;
            }
            list.add(num);
            huisu(nums, list);
            // 撤销选择 撤销的是list的最后一个元素
            list.remove(list.size() - 1);
        }
    }


    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 3};
        _02_quan_pailie quanPailie = new _02_quan_pailie();
        List<List<Integer>> permute = quanPailie.permute(ints);
        System.out.println(permute.toString());
    }

}
