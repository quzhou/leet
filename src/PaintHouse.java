/**
 * Created by qzhou on 1/20/17.
 */

public class PaintHouse {
    // https://leetcode.com/problems/paint-house/
    public int minCost(int[][] costs) {
        int n = costs.length;
        if (n == 0) {
            return 0;
        }

        // min cost for ending color RGB
        int[][] dp = new int[n+1][3];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + costs[i-1][0];
            dp[i][1] = Math.min(dp[i-1][2], dp[i-1][0]) + costs[i-1][1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + costs[i-1][2];
        }

        return Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2]));
    }
}
