package com.sunjinwei.bfs_dfs_huisu;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 */
public class _07_n_queens {

    private List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {

        LinkedList<String> path = new LinkedList<>();
        boolean[][] visited = new boolean[n + 1][n + 1];
        solveNQueens_help(path, n, visited);
        return result;
    }

    private void solveNQueens_help(LinkedList<String> path, int n, boolean[][] visited) {
        if (path.contains("Q")) {

        }
        if (path.size() == n) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
              if (visited[i][j+1]){

              }
            }
        }
    }

    public static void main(String[] args) {
        _07_n_queens n_queens = new _07_n_queens();
        List<List<String>> lists = n_queens.solveNQueens(4);
        for (List<String> list : lists) {
            System.out.println(list.toString());
        }
    }
}
