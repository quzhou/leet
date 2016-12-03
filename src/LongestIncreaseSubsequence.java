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
     * Let's have another array c[], c is pretty special, c[i] means:
     * the minimum value of the last element of the longest increasing sequence whose length is i.
     */
    // O(NlogN)
    //dp[i] 为 以 A[i]结尾的LIS ， g(i) = min ( A[j]) ( dp[j] = i) 即g(i)表示上升子序列为i，结尾最小的值。
    //比如，1，2，4，3中A[3] = 3
    //那么显然， g(1) <= g(2) <=……g(n)
    //我们可以用二分搜索查找满足g(k) >= A[i]的第一个下标k，则dp[i] = k ，此时 A[i] <= g(k)  ，
    //而dp[i] =k，所以更新g(k) = A[i]
    public int longestIncreasingSubsequence(int[] nums) {
        int[] minLast = new int[nums.length + 1];
        minLast[0] = Integer.MIN_VALUE;
        for (int i = 1; i <= nums.length; i++) {
            minLast[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < nums.length; i++) {
            // find the first number in minLast > nums[i]
            int index = binSearch(minLast, nums[i]);
            minLast[index] = nums[i];
        }

        for (int i = nums.length; i >= 1; i--) {
            if (minLast[i] != Integer.MAX_VALUE) {
                return i;
            }
        }

        return 0;
    }

    // Find first element > num, return index
    // arr is sorted array
    public int binSearch(int[] arr, int num) {
        int low = 0, high = arr.length - 1, mid;
        while (low + 1 < high) {
            mid = low + (high - low) / 2;
            if (arr[mid] <= num) {
                low = mid;
            } else {
                high = mid;
            }
        }
        if (arr[low] > num) {
            return low;
        }
        return high;
    }
}
