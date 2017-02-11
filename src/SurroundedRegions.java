/**
 * Created by qzhou on 2/13/17.
 * http://www.lintcode.com/en/problem/surrounded-regions/
 */
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class SurroundedRegions {
    public void surroundedRegions(char[][] board) {
        if (board.length == 0 || board[0].length == 0) {
            return;
        }

        HashSet<Integer> set = new HashSet<Integer>();
        int n = board[0].length;

        for (int i = 1; i < board.length - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                int k = i * n + j;
                // 0 that has not been processed
                if (!set.contains(k) && board[i][j] == 'O') {
                    helper(board, i, j, set, n);
                }
            }
        }
    }

    private void helper(char[][] board, int x, int y, HashSet<Integer> set, int n) {
        ArrayList<Integer> zeros = new ArrayList<Integer>();
        Queue<Integer> q = new LinkedList<Integer>();

        int[] dx = new int[] {1, 0, -1, 0};
        int[] dy = new int[] {0, 1, 0, -1};

        boolean toBound = false;
        int idx = x * n + y;

        q.add(idx);
        set.add(idx);
        zeros.add(idx);

        while (!q.isEmpty()) {
            int k = q.remove();
            int i = k / n, j = k % n;

            for (int s = 0; s < 4; s++) {
                int val = boundCheck(i+dx[s], j+dy[s], board.length, n);
                if (val == -1 || board[i+dx[s]][j+dy[s]] == 'X') {
                    continue;
                }

                if (val == 0) {
                    toBound = true;
                }

                val = (i + dx[s]) * n + j + dy[s];
                if (!set.contains(val)) {
                    q.add(val);
                    set.add(val);
                    zeros.add(val);
                }
            }
        }

        if (!toBound) {
            for (Integer i : zeros) {
                board[i/n][i%n] = 'X';
            }
        }
    }

    private int boundCheck(int x, int y, int m, int n) {
        if (x < 0 || x >= m || y < 0 || y >= n) {
            return -1;
        } else if (x == 0 || x == m - 1 || y == 0 || y == n - 1) {
            return 0;
        } else {
            return 1;
        }
    }
}
