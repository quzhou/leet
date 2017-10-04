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

    public int minCostII(int[][] costs) {
        int n = costs.length;
        if (n == 0) {
            return 0;
        }

        int k = costs[0].length;
        int[][] dp = new int[n][k]; //ending with color k

        for (int j = 0; j < k; j++) {
            dp[0][j] = costs[0][j];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < k; j++) {
                int min = Integer.MAX_VALUE;

                for (int l = 0; l < k; l++) {
                    if (l != j) {
                        min = Math.min(min, dp[i - 1][l]);
                    }
                }
                dp[i][j] = min + costs[i][j];
            }
        }

        int minCost = Integer.MAX_VALUE;
        for (int j = 0; j < k; j++) {
            minCost = Math.min(minCost, dp[n-1][j]);
        }
        return minCost;
    }

    // O(nk)
    /* 这题的解法的思路还是用DP，但是在找不同颜色的最小值不是遍历所有不同颜色，
     * 而是用min1和min2来记录之前房子的最小和第二小的花费的颜色，如果当前房子颜色和min1相同，
     * 那么我们用min2对应的值计算，反之我们用min1对应的值，这种解法实际上也包含了求次小值的方法，
     * 感觉也是一种很棒的解题思路
     */
    public int minCostII2(int[][] costs) {
        int n = costs.length;
        if (n == 0) {
            return 0;
        }
        int K = costs[0].length;
        if (K == 0) {
            return 0;
        }

        // this is saved for last level
        int min1 = 0, min2 = 0, idx = -1;

        for (int i = 0; i < n; i++) {
            int m1 = Integer.MAX_VALUE, m2 = m1, id1 = -1;

            for (int j = 0; j < K; j++) {
                int cost = costs[i][j] + (j == idx ? min2 : min1);
                if (cost < m1) {
                    m2 = m1;
                    m1 = cost;
                    id1 = j;
                } else if (cost < m2) {
                    m2 = cost;
                }
            }

            min1 = m1;
            min2 = m2;
            idx = id1;
        }

        return min1;
    }
}
