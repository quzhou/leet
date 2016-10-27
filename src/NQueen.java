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
}
