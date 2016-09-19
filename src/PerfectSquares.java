/**
 * Created by qzhou on 9/17/15.
 * https://leetcode.com/problems/perfect-squares/
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 */
import java.util.HashMap;

public class PerfectSquares {
    public static int numSquares(int n) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        return getNumbers(n, map);
    }

    public static int getNumbers(int n, HashMap<Integer, Integer> results) {
        if (n == 0 || n == 1) {
            results.put(n, n);
            return n;
        }

        int min = 0; boolean first = true;
        for (int i = 1; i * i <= n; i++) {
            int left = n - (i * i);
            Integer leftNum = results.get(left);
            if (leftNum == null) {
                leftNum = getNumbers(left, results);
            }
            if (first) {
                min = leftNum + 1;
                first = false;
            } else {
                if (leftNum + 1 < min) {
                    min = leftNum + 1;
                }
            }
        }
        results.put(n, min);
        return min;
    }

    public static void main(String[] args) {
        int n = 199;
        System.out.println(numSquares(n));
    }
}
