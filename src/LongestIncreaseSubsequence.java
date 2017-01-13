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
    /**
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    // O(N^2)
    public int LIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;

        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    /*
     * Let's have another array minLast[], minLast is pretty special, minLast[i] means:
     * the minimum value of the last element of the longest increasing sequence whose length is i.
     */
    // O(NlogN)
    // http://www.geeksforgeeks.org/longest-monotonically-increasing-subsequence-size-n-log-n/
    public int longestIncreasingSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] minLast = new int[nums.length + 1];
        int size = 1;
        minLast[1] = nums[0];

        for (int i = 2; i <= nums.length; i++) {
            if (nums[i-1] > minLast[size]) {
                minLast[size + 1] = nums[i-1];
                size++;
            } else {
                int index = binSearch(minLast, nums[i-1], 1, size);
                minLast[index] = nums[i-1];
            }
        }

        return size;
    }

    // Find first element >= num (ceiling), return index
    // arr is sorted array
    public int binSearch(int[] arr, int num, int start, int end) {
        int low = start, high = end, mid;
        while (low + 1 < high) {
            mid = low + (high - low) / 2;
            if (arr[mid] >= num) {
                high = mid;
            } else {
                low = mid;
            }
        }

        if (arr[low] >= num) {
            return low;
        } else if (arr[high] >= num) {
            return high;
        } else {
            return end + 1;
        }
    }

    // http://www.lintcode.com/en/problem/longest-common-subsequence/
    // 二维不用ending sub-array
    // choose it or not choose it
    public int longestCommonSubsequence(String A, String B) {
        int m = A.length(), n = B.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (A.charAt(i-1) == B.charAt(j-1)) {
                    dp[i][j] = Math.max(dp[i-1][j-1] + 1,
                            Math.max(dp[i-1][j], dp[i][j-1]));
                } else {
                    dp[i][j] = Math.max(dp[i-1][j-1],
                            Math.max(dp[i-1][j], dp[i][j-1]));
                }
            }
        }

        return dp[m][n];
    }
}
