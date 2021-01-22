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
        String[][] visited = new String[n][n];
        // 初始化二维数组
        for (int i = 0; i < n; i++) {
            visited[i][i] = ".";
        }
        solveNQueens_help(path, n, visited);
        return result;
    }

    private void solveNQueens_help(LinkedList<String> path, int n, String[][] visited) {

        if (path.size() == n) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!valid(visited, i, j)) {
                    continue;
                }
                visited[i][j] = "Q";
                solveNQueens_help(path, n, visited);
                visited[i][j] = ".";
                path.removeLast();
            }
        }
    }

    /**
     * @param visited
     * @param row     行
     * @param col     列
     * @return
     */
    private boolean valid(String[][] visited, int row, int col) {
        // 1左斜方向 行遍历倒序
        for (int i = row, j = 0; i > j; i--, j++) {
            if (Math.abs((i - j)) == Math.abs((row - col))) {
                if (visited[i][j] == "Q") {
                    return false;
                }
            }
        }
        // 2水平方向 列是递增的 行是一样的
        for (int i = 0; i < col; i++) {
            if (visited[row][i] == "Q") {
                return false;
            }
        }
        // 3竖直方向 行是递增的
        for (int i = 0; i < row; i++) {
            if (visited[col][i] == "Q") {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        _07_n_queens n_queens = new _07_n_queens();
        List<List<String>> lists = n_queens.solveNQueens(4);
        for (List<String> list : lists) {
            System.out.println(list.toString());
        }


    }
}
