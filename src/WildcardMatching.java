/**
 * Created by qzhou on 8/23/17.
 * https://leetcode.com/problems/wildcard-matching/description/
 */
public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        // ending
        // 找必要条件
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') { //不出现或出现
                    dp[i][j] = dp[i][j - 1] || (i > 0 && dp[i-1][j]);
                } else {
                    dp[i][j] = i > 0 && dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?');
                }
            }
        }

        return dp[m][n];
    }
}
