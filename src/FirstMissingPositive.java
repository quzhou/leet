/**
 * Created by qzhou on 12/14/16.
 */

/*
 * leetcode: first missing positive
 * https://oj.leetcode.com/problems/first-missing-positive/
 * http://www.binglu.me/leetcode-first-missing-positive/
 *
 * Given an unsorted integer array, find the first missing positive integer.
 * For example,
 * Given [1,2,0] return 3,
 * and [3,4,-1,1] return 2.
 * Your algorithm should run in O(n) time and uses constant space.
 *
 * Solution: in-place swapping
 * The solution is using the idea of counting sort and do it in-place to modify the array
 * in a way that A[i] == i+1. The counting sort should be specified a range; so is here.
 * It's upper bound is n = A.length here and the lower bound is 1, since we are looking
 * for first missing integer. For numbers smaller than 1 or greater than n, we just leave
 * it as is. Similarly, we will skip when the current number already satisfy A[i] == i+1
 * or the number at its swap destination is same as the to be swapped number.
 *
 * So, the condition of swapping is actually:
 * A[i] > 0 && A[i] <= n && A[i]-1 != i && A[A[i]-1] != A[i]
 *
 * After the array is modified, we check the array to find out the first occurrence
 * that A[i] != i+1, the i+1 is the positive number that we are looking for.
 * (e.g. [3,4,-1,1] would be [1,-1,3,4], the first missing integer is 2).
 * What if the whole sorted array satisfies A[i] == i+1? This means they are successive
 * positive integer from 1 to n; in this case, return n+1.
 *
 */

public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int i = 0;
        while (i < n) {
            if (nums[i] > 0 && nums[i] <= n && nums[i]-1 != i && nums[nums[i]-1] != nums[i]) {
                int temp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = temp;
            } else {
                i++;
            }
        }

        for (i = 0; i < n; i++){
            if(nums[i] != i+1) return i+1;
        }
        return n+1;
    }
}
