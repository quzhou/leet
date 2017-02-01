/**
 * Created by qzhou on 10/3/16.
 * First problem quick select.
 * Second problem Heap.
 */
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 *
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order,
 * not the kth distinct element.
 For example, Given [3,2,1,5,6,4] and k = 2, return 5.
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class KthLargest {
    /*
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
        int pivot = nums[l];
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
    */

    public int kthLargestElement(int k, int[] nums) {
        return helper(k, nums, 0, nums.length - 1);
    }

    // like quick sort
    private int partition(int[] nums, int start, int end) {
        int l = start, r = end;
        int pivot = nums[start + (end -start) / 2];

        while (l <= r) {
            // because it is largest, not smallest
            while (l <= r && nums[l] > pivot) {
                l++;
            }

            while (l <= r && nums[r] < pivot) {
                r--;
            }

            if (l <= r) {
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
                l++;
                r--;
            }
        }

        return l;
    }

    private int helper(int k, int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }

        int l = partition(nums, start, end);

        if (l - start >= k) {
            return helper(k, nums, start, l-1);
        } else {
            return helper(k - l + start, nums, l, end);
        }
    }

    // http://www.lintcode.com/en/problem/kth-largest-in-n-arrays/
    // Kth Largest in N Arrays
    public int KthInArrays(int[][] arrays, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>();
        int sz = 0;

        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[i].length; j++) {
                if (sz < k) {
                    heap.add(arrays[i][j]);
                    sz++;
                } else {
                    if (heap.peek() < arrays[i][j]) {
                        heap.remove();
                        heap.add(arrays[i][j]);
                    }
                }
            }
        }

        return (int)heap.remove();
    }
}