/**
 * Created by qzhou on 10/3/16.
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 *
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order,
 * not the kth distinct element.
 For example, Given [3,2,1,5,6,4] and k = 2, return 5.
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class KthLargest {
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) return 0;
        return helper(nums, 0, nums.length - 1, nums.length - k + 1);
    }

    int helper(int[] nums, int l, int r, int k) {
        if (l == r) return nums[l];
        int p = partition(nums, l, r);
        if (p + 1 == k) {
            return nums[p];
        } else if (p + 1 < k) {
            return helper(nums, p+1, r, k);
        } else {
            return helper(nums, l, p-1, k);
        }
    }

    int partition(int[] nums, int l, int r) {
        int low = l, high = r;
        int pivot = nums[low];
        while (low < high) {
            while (low < high && nums[high] >= pivot) {
                high--;
            }
            nums[low] = nums[high];
            while (low < high && nums[low] <= pivot) {
                low++;
            }
            nums[high] = nums[low];
        }
        nums[low] = pivot;
        return low;
    }
}