/**
 * Created by qzhou on 9/21/16.
 * https://leetcode.com/problems/unique-paths/
 *
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

 The robot can only move either down or right at any point in time.
 The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

 How many possible unique paths are there?
 */
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m+1][n+1]; //paths for size
        for (int i = 1; i <= n; i++) {
            dp[1][i] = 1;
        }
        for (int i = 1; i <= m; i++) {
            dp[i][1] = 1;
        }
        for (int i = 2; i <= m; i++) {
            for (int j= 2; j <= n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m][n];
    }

    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m+1][n+1];
        return helper(dp, m, n);
    }

    int helper(int[][] dp, int m, int n) {
        if (dp[m][n] > 0) {
            return dp[m][n];
        }
        if (m == 1 || n == 1) {
            dp[m][n] = 1;
            return 1;
        }
        dp[m][n] = helper(dp, m-1, n) + helper(dp, m, n-1);
        return dp[m][n];
    }
}
