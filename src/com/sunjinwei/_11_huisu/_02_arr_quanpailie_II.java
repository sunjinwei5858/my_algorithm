package com.sunjinwei._11_huisu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 数组的全排列II，数组中包含重复的元素
 * <p>
 * 输入: [2,2,3]
 * 输出:
 * [
 * [2,2,3],
 * [2,3,2],
 * [3,2,2]
 * ]
 */
public class _02_arr_quanpailie_II {

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> quanpailie(int[] arr) {
        // 有重复元素 先对元素进行排序 这样方便碰到重复元素就跳过处理
        Arrays.sort(arr);
        boolean[] visited = new boolean[arr.length];
        // 使用LinkedList 队列 方便移除元素
        LinkedList<Integer> path = new LinkedList<>();
        backTrack(arr, path, visited);
        return res;
    }

    private void backTrack(int[] arr, LinkedList<Integer> path, boolean[] visited) {
        if (arr.length == path.size()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            // 剪枝1: 该元素访问过 跳过
            if (visited[i]) {
                continue;
            }
            // 剪枝2：如果是相同元素&&并且上一个元素没有被访问过
            if ((i - 1) >= 0 && arr[i] == arr[i - 1] && visited[i - 1]) {
                continue;
            }
            visited[i] = true;
            // 添加元素
            path.add(arr[i]);
            backTrack(arr, path, visited);
            visited[i] = false;
            // 移除元素
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        _02_arr_quanpailie_II quanpailie_i = new _02_arr_quanpailie_II();
        List<List<Integer>> listList = quanpailie_i.quanpailie(new int[]{2, 2, 3});

        for (List<Integer> integers : listList) {
            System.out.println(integers.toString());
        }
    }
}
