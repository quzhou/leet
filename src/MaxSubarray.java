/**
 * Created by qzhou on 9/21/16.
 * https://leetcode.com/problems/maximum-subarray/
 *
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

 For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
 the contiguous subarray [4,-1,2,1] has the largest sum = 6.
 */
public class MaxSubarray {
    // if every element is negative, return empty sub array.
    public int maxSubArray(int[] nums) {
        int endSum = 0, max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (endSum > 0) {
                endSum += nums[i];
            } else {
                endSum = nums[i];
            }
            max = Math.max(max, endSum);
        }
        return max;
    }

    // can return negative number
    public int maxSubArray2(int[] nums) {
        int endSum, maxSum;
        endSum = maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (endSum > 0) {
                endSum += nums[i];
            } else {
                endSum = nums[i];
            }
            maxSum = Math.max(maxSum, endSum);
        }
        return maxSum;
    }
}
