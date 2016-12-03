/**
 * Created by qzhou on 12/6/16.
 */
public class BeiBao {
    /* EBAY
     * Given a non-empty array of positive integers,
     * check if it can be broken into two subsets such that their sums equal.
     *
     * For example, [1, 5, 11, 15] => true
     */
    public boolean breakable(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
        }
        if (total % 2 == 1) {
            return false;
        }
        total /= 2;

        // Can find a non-empty combination whose sum is total, one element can be used at most once.

        boolean[][] dp = new boolean[nums.length+1][total+1];
        dp[0][0] = true;

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= total; j++) {
                int remain = j - nums[i-1];
                if (remain >= 0) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][remain];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[nums.length][total];
    }
}
