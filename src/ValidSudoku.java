/**
 * Created by qzhou on 8/21/17.
 */
import java.util.List;
import java.util.ArrayList;

public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            List<Integer> list = new ArrayList<Integer>();
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '.' && board[i][j] >= '1' && board[i][j] <= '9') {
                    list.add(board[i][j] - '0');
                }
            }
            if (!isValid(list)) {
                return false;
            }
        }

        for (int j = 0; j < board[0].length; j++) {
            List<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < board.length; i++) {
                if (board[i][j] != '.' && board[i][j] >= '1' && board[i][j] <= '9') {
                    list.add(board[i][j] - '0');
                }
            }
            if (!isValid(list)) {
                return false;
            }
        }

        int[][] diff = {{0, 0}, {0, 1}, {0, 2},
                {1, 0}, {1, 1}, {1, 2},
                {2, 0}, {2, 1}, {2, 2}};
        for (int i = 0; i < board.length; i += 3) {
            for (int j = 0; j < board[0].length; j += 3) {
                List<Integer> list = new ArrayList<Integer>();
                boolean skip = false;
                for (int k = 0; k < 9; k++) {
                    int x = i + diff[k][0];
                    int y = j + diff[k][1];
                    if (x < 0 || x >= board.length || y <0 || y >= board[0].length) {
                        skip = true;
                        break;
                    }
                    if (board[x][y] != '.' && board[x][y] >= '1' && board[x][y] <= '9') {
                        list.add(board[x][y] - '0');
                    }
                }
                if (!skip && !isValid(list)) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isValid(List<Integer> list) {
        int[] hash = new int[10];
        for (Integer i : list) {
            if (hash[i] != 0) {
                return false;
            }
            hash[i]++;
        }
        return true;
    }
}
