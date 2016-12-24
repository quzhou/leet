/**
 * Created by qzhou on 10/26/16.
 * https://leetcode.com/problems/n-queens/
 *
 * Backtracking
 *
 * [
 [".Q..",  // Solution 1
 "...Q",
 "Q...",
 "..Q."],

 ["..Q.",  // Solution 2
 "Q...",
 "...Q",
 ".Q.."]
 ]
 */

import java.util.List;
import java.util.ArrayList;

public class NQueen {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<List<String>>();
        if (n == 0) {
            return result;
        }

        List<String> subSolution = new ArrayList<String>();
        helper(n, 0, subSolution, result);

        return result;
    }

    private void helper(int n, int rowNumber, List<String> subSolution,
                        List<List<String>> result) {
        if (rowNumber == n) {
            result.add(new ArrayList<String>(subSolution));
            return;
        }

        for (int i = 0; i < n; i++) {
            boolean attack = false;
            int sz = subSolution.size();
            for (int k = 0; k < sz; k++) {
                String row = subSolution.get(k);

                if (row.charAt(i) == 'Q') {
                    attack = true;
                    break;
                }

                int distance = sz - k;
                if ((i - distance >= 0 && row.charAt(i - distance) == 'Q') ||
                        (i + distance < n && row.charAt(i + distance) == 'Q')) {
                    attack = true;
                    break;
                }
            }

            if (!attack) {
                String str = "";
                for (int j = i - 1; j >= 0; j--) {
                    str += ".";
                }
                str += "Q";
                for (int j = i + 1; j < n; j++) {
                    str += ".";
                }

                subSolution.add(str);
                helper(n, rowNumber + 1, subSolution, result);
                subSolution.remove(subSolution.size() - 1);
            }
        }
    }

    // http://www.lintcode.com/en/problem/n-queens-ii/
    public int totalNQueens(int n) {
        if (n <= 0) {
            return 0;
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> path = new ArrayList<Integer>();
        helper(n, 0, path, result);

        return result.size();
    }

    private void helper(int n, int row, ArrayList<Integer> path,
                        ArrayList<ArrayList<Integer>> result) {
        if (row == n) {
            result.add(new ArrayList<Integer>(path));
            return;
        }

        for (int j = 0; j < n; j++) {
            if (!attack(n, row, j, path)) {
                path.add(j);
                helper(n, row+1, path, result);
                path.remove(path.size() - 1);
            }
        }
    }

    private boolean attack(int n, int row, int col, ArrayList<Integer> path) {
        boolean[][] matrix = new boolean[n][n];
        for (int i = 0; i < path.size(); i++) {
            matrix[i][path.get(i)] = true;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == row && j == col) {
                    continue;
                }

                if (i == row && matrix[i][j]) {
                    return true;
                }
                if (j == col && matrix[i][j]) {
                    return true;
                }

                if (Math.abs(i - row) == Math.abs(j - col) && matrix[i][j]) {
                    return true;
                }
            }
        }

        return false;
    }
}
