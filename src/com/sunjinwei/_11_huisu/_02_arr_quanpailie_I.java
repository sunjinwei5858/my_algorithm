package com.sunjinwei._11_huisu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 数组的全排列，数组中不包含重复的元素
 * <p>
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 */
public class _02_arr_quanpailie_I {

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> quanpailie(int[] arr) {

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
            // 剪枝
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            // 添加元素
            path.add(arr[i]);
            System.out.println("--first--" + arr[i]);
            backTrack(arr, path, visited);
            // 移除元素
            System.out.println("--last--"+path.getLast());
            path.removeLast();
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        _02_arr_quanpailie_I quanpailie_i = new _02_arr_quanpailie_I();
        List<List<Integer>> listList = quanpailie_i.quanpailie(new int[]{1, 2, 3});

        for (List<Integer> integers : listList) {
            System.out.println(integers.toString());
        }
    }
}
