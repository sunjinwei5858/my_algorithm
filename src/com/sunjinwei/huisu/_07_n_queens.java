package com.sunjinwei.huisu;

import java.util.ArrayList;
import java.util.Arrays;
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

        List<char[]> board = new LinkedList<>();
        // 初始化
        for (int i = 0; i < n; i++) {
            // 创建char数组
            char[] arr = new char[n];
            Arrays.fill(arr, '.');
            board.add(arr);
        }
        solveNQueens_help(board, 0);
        return result;
    }

    /**
     * 从第一行开始回溯
     *
     * @param board
     * @param row
     */
    private void solveNQueens_help(List<char[]> board, int row) {

        if (row == board.size()) {
            // 把此时的二维数组添加到结果集
            List<String> list = transform(board);
            result.add(list);
            return;
        }
        // n叉树的遍历 每一行都是从第一列 到第n列
        for (int col = 0; col < board.size(); col++) {
            // 剪枝
            if (!valid(board, row, col)) {
                continue;
            }
            // 做选择
            board.get(row)[col] = 'Q';
            // 递归 进入下一行
            solveNQueens_help(board, row + 1);
            // 撤销选择
            board.get(row)[col] = '.';
        }
    }

    private List<String> transform(List<char[]> board) {
        LinkedList<String> newBoard = new LinkedList<>();
        for (char[] row : board) {
            newBoard.add(new String(row));
        }
        return newBoard;
    }

    /**
     * @param board
     * @param row   行
     * @return
     */
    private boolean valid(List<char[]> board, int row, int col) {
        // 1检查正上方是否有冲突 这里可以缩小范围 i<row即可 不需要遍历全部的行
        for (int i = 0; i < row; i++) {
            if (board.get(i)[col] == 'Q') {
                return false;
            }
        }
        // 2检查右斜方是否有冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < board.size(); i--, j++) {
            if (board.get(i)[j] == 'Q') {
                return false;
            }
        }
        // 3检查左斜方是否有冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board.get(i)[j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        _07_n_queens n_queens = new _07_n_queens();
        List<List<String>> lists = n_queens.solveNQueens(4);
        System.out.println(lists.toString());


    }
}
