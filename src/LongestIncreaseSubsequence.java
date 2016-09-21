/**
 * Created by qzhou on 9/22/16.
 * https://leetcode.com/problems/longest-increasing-subsequence/
 *
 * Given an unsorted array of integers, find the length of longest increasing subsequence.

 For example, Given [10, 9, 2, 5, 3, 7, 101, 18], the longest increasing subsequence is
 [2, 3, 7, 101], therefore the length is 4.

 Note that there may be more than one LIS combination, it is only necessary for you to
 return the length.

 Your algorithm should run in O(n2) complexity. Could you improve it to O(n log n)
 time complexity?
 */
public class LongestIncreaseSubsequence {
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;
        int max = 1;
        // length ending at i
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int k = 0; k < i; k++) {
                if (nums[k] < nums[i] && (dp[k] + 1 > dp[i])) {
                    dp[i] = dp[k] + 1;
                }
            }
            if (dp[i] > max) {
                max = dp[i];
            }
        }
        return max;
    }
}
