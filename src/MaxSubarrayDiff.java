/**
 * Created by qzhou on 12/30/16.
 * http://www.lintcode.com/en/problem/maximum-subarray-difference/
 */
public class MaxSubarrayDiff {
    public int maxDiffSubArrays(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] subMin = new int[nums.length];
        int[] subMax = new int[nums.length];
        int sub1 = nums[0], sub2 = nums[0];
        subMin[0] = sub1;
        subMax[0] = sub2;

        for (int i = 1; i < nums.length; i++) {
            if (sub1 > 0) {
                sub1 = nums[i];
            } else {
                sub1 += nums[i];
            }
            subMin[i] = Math.min(subMin[i-1], sub1);

            if (sub2 < 0) {
                sub2 = nums[i];
            } else {
                sub2 += nums[i];
            }
            subMax[i] = Math.max(subMax[i-1], sub2);
        }

        int[] subMinR = new int[nums.length];
        int[] subMaxR = new int[nums.length];
        sub1 = nums[nums.length - 1];
        sub2 = nums[nums.length - 1];
        subMinR[nums.length - 1] = sub1;
        subMaxR[nums.length - 1] = sub2;

        for (int i = nums.length - 2; i >= 0; i--) {
            if (sub1 > 0) {
                sub1 = nums[i];
            } else {
                sub1 += nums[i];
            }
            subMinR[i] = Math.min(subMinR[i+1], sub1);

            if (sub2 < 0) {
                sub2 = nums[i];
            } else {
                sub2 += nums[i];
            }
            subMaxR[i] = Math.max(subMaxR[i+1], sub2);
        }

        int result = 0;
        // max - min, min - max
        for (int i = 0; i < nums.length - 1; i++) {
            int temp1 = Math.abs(subMax[i] - subMinR[i+1]);
            int temp2 = Math.abs(subMin[i] - subMaxR[i+1]);
            result = Math.max(result, Math.max(temp1, temp2));
        }

        return result;
    }
}
