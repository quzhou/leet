/**
 * Created by qzhou on 8/12/17.
 * https://leetcode.com/problems/jump-game-ii/description/
 */
public class JumpGameII {
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 0;

        for (int i = 1; i < nums.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j <= i - 1; j++) {
                if (nums[j] >= i - j) {
                    min = Math.min(min, dp[j] + 1);
                }
            }
            dp[i] = min;
        }

        return dp[nums.length - 1];
    }
}
