/**
 * Created by qzhou on 1/24/17.
 * https://leetcode.com/problems/regular-expression-matching/
 */
public class Regex {
    // DP, O(n^2)
    // ending
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean dp[][] = new boolean[m+1][n+1];
        dp[0][0] = true;

        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (j > 1 && p.charAt(j-1) == '*') { //不出现或出现
                    dp[i][j] = dp[i][j - 2] ||
                            (i > 0 && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') && dp[i - 1][j]);
                } else {
                    dp[i][j] = i > 0 && dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.');
                }
            }
        }

        return dp[m][n];
    }
}
