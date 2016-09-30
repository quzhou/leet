/**
 * Created by qzhou on 9/27/16.
 * https://leetcode.com/problems/unique-binary-search-trees/
 *
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 For example,
 Given n = 3, there are a total of 5 unique BST's.
 */
public class UniqueBST {
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        if (n == 0) return 0;
        dp[0] = 1;
        return helper(n, dp);
    }

    int helper(int n, int[] dp) {
        if (dp[n] != 0) return dp[n];
        if (n == 1) {
            dp[1] = 1;
            return 1;
        }
        for (int k = 1; k <= n; k++) {
            dp[n] += helper(k-1, dp) * helper(n-k, dp);
        }
        return dp[n];
    }

    public int numTrees2(int n) {
        int[] dp = new int[n+1];
        if (n == 0) return 0;
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] = 0;
            for (int k = 1; k <= i; k++) {
                dp[i] += dp[k-1] * dp[i-k];
            }
        }
        return dp[n];
    }
}
