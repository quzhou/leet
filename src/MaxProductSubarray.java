/**
 * Created by qzhou on 9/20/16.
 * https://leetcode.com/problems/maximum-product-subarray/
 *
 * Find the contiguous subarray within an array (containing at least one number) which has
 * the largest product.
 *
 For example, given the array [2,3,-2,4],
 the contiguous subarray [2,3] has the largest product = 6.
 */
public class MaxProductSubarray {
    // return largest product
    public int maxProduct(int[] nums) {
        int maxProd, minProd, ret;
        maxProd = minProd = ret = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int t = maxProd;
            maxProd = Math.max(Math.max(t * nums[i], minProd * nums[i]), nums[i]);
            minProd = Math.min(Math.min(t * nums[i], minProd * nums[i]), nums[i]);
            ret = Math.max(ret, maxProd);
        }
        return ret;
    }
}

