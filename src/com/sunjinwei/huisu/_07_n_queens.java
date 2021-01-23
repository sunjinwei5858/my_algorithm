package com.sunjinwei.huisu;

import java.util.*;

/**
 * n皇后问题 51 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 输入：n = 4
 * 输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * 解释：如上图所示，4 皇后问题存在两个不同的解法。
 */
public class _07_n_queens {

    private List<List<String>> result = new ArrayList<>();

    /**
     * 方法一：
     * 执行用时：8 ms, 在所有 Java 提交中击败了20.77%的用户
     * 内存消耗：38.7 MB, 在所有 Java 提交中击败了55.23%的用户
     *
     * @param n
     * @return
     */
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
     * 判断能不能放置皇后的方法：分成三个方向，1正上方 2左斜 3右斜
     * 1正上：行倒序遍历 列索引保持不变
     * 2左斜：行倒序 列倒序
     * 3右斜：行倒序 列正序
     *
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


    /**
     * 方法二：
     * 使用string类型的二维数组，并且for循环中加入了对角线的条件
     * <p>
     * 执行用时：5 ms, 在所有 Java 提交中击败了52.73%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了44.81%的用户
     * <p>
     * for循环中加上对角线的条件的执行用时：
     * 执行用时：5 ms, 在所有 Java 提交中击败了52.73%的用户
     * 内存消耗：38.8 MB, 在所有 Java 提交中击败了49.58%的用户
     *
     * @param n
     */
    public List<List<String>> solveNQueens_02(int n) {

        String[][] arr = new String[n][n];
        // 初始化二维数组 全部填充 .
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = ".";
            }
        }
        huisu(arr, 0);
        return result;
    }

    private void huisu(String[][] arr, int start) {
        if (start == arr.length) {
            List<String> changeToStrList = changeToStrList(arr);
            result.add(changeToStrList);
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            // 剪枝 第start行 第i列
            if (!isValid(arr, start, i)) {
                continue;
            }
            // 做选择
            arr[start][i] = "Q";
            // 递归
            huisu(arr, start + 1);
            // 撤销选择
            arr[start][i] = ".";
        }
    }

    private List<String> changeToStrList(String[][] arr) {
        LinkedList<String> list = new LinkedList<>();
        for (String[] strings : arr) {
            StringBuffer stringBuffer = new StringBuffer();
            for (String string : strings) {
                stringBuffer.append(string);
            }
            list.add(stringBuffer.toString());
        }
        return list;
    }


    private boolean isValid(String[][] arr, int row, int col) {
        // 列的正上方 行倒序遍历 或者正序遍历也可以 从0到row-1
        for (int i = row - 1; i >= 0; i--) {
            if (arr[i][col] == "Q") {
                return false;
            }
        }
        // 列的左上 列倒序 行倒序 因为这里行row-1 列是col-1 保证了是对角线遍历
        // 如果 j = col 那么除了对角线 是整个右上方的遍历了!!!
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            // 对角线的这个条件也可以不加
            if ((i - j == row - col) && arr[i][j] == "Q") {
                return false;
            }
        }
        // 列的右对角线遍历 因为这里行row-1,列是col+1 保证了这是对角线的遍历
        for (int i = row - 1, j = col + 1; i >= 0 && j < arr.length; i--, j++) {
            if (arr[i][j] == "Q") {
                return false;
            }
        }
        return true;
    }

    /**
     * 方法三：还可以优化
     * 本题必须记录之前放皇后的位置，才能结合约束条件去做剪枝。
     * 我每次都调用 isValid 遍历一遍前面的格子，效率是不优的。
     * 最好是用三个数组或 Set 去记录出现过皇后的列们、正对角线们、反对角线们，用空间换取时间。
     *
     * @param n
     */
    private HashSet<Integer> colSet = new HashSet<>();
    private HashSet<Integer> leftSet = new HashSet<>();
    private HashSet<Integer> rightSet = new HashSet<>();

    public List<List<String>> solveNQueens_03(int n) {
        String[][] arr = new String[n][n];
        // 初始化二维数组 全部填充 .
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = ".";
            }
        }
        helper(arr, 0);

        return result;

    }

    private void helper(String[][] arr, int row) {

        if (row == arr.length) {
            List<String> changeToStrList = changeToStrList(arr);
            result.add(changeToStrList);
            return;
        }
        for (int col = 0; col < arr.length; col++) {
            if (!colSet.contains(col) && !leftSet.contains(row - col) && !rightSet.contains(row + col)) {
                // 做选择
                arr[row][col] = "Q";
                colSet.add(col);
                leftSet.add(row - col);
                rightSet.add(row + col);
                // 递归
                helper(arr, row + 1);
                // 撤销选择
                arr[row][col] = ".";
                colSet.remove(col);
                leftSet.remove(row - col);
                rightSet.remove(row + col);
            }
        }

    }


    public static void main(String[] args) {
        _07_n_queens n_queens = new _07_n_queens();
        List<List<String>> lists = n_queens.solveNQueens_03(4);
        System.out.println(lists.size());
        System.out.println(lists.toString());
        System.out.println("================");


    }
}
