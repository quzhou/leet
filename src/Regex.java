/**
 * Created by qzhou on 1/24/17.
 * https://leetcode.com/problems/regular-expression-matching/
 */
public class Regex {
    // DP, O(n^2)
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m+1][n+1];
        dp[0][0] = true;

        for (int j = 1; j <= n; j++) {
            if (j == 1) {
                dp[0][j] = false;
            } else if (p.charAt(j-1) == '*') {
                dp[0][j] = dp[0][j-2];
            } else {
                dp[0][j] = false;
            }
        }

        // [i-1][j-1], [i][j-1], [i-1][j]
        // Only consider 1 char before *
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j-1) == '.') {
                    dp[i][j] = dp[i-1][j-1];
                } else if (p.charAt(j-1) == '*') {
                    if (j == 1) {
                        dp[i][j] = false;
                    } else {
                        char ch = p.charAt(j-2);
                        dp[i][j] |= dp[i][j-2];

                        for (int len = i-1; len >= 0; len--) {
                            if (ch != '.' && s.charAt(len) != ch) {
                                break;
                            }
                            dp[i][j] |= dp[len][j-2];
                        }
                    }
                } else if (p.charAt(j-1) == s.charAt(i-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[m][n];
    }
}
