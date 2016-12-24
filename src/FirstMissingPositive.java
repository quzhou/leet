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

    //FB http://www.lintcode.com/en/problem/remove-duplicates-from-sorted-array-ii/
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int start = 0, j = 1;
        // start is already copied
        while (start < nums.length) {
            int end = start + 1;
            while (end < nums.length && nums[end] == nums[start]) {
                end++;
            }

            if (end - start == 1) {
                if (end == nums.length) {
                    break;
                }

                nums[j++] = nums[end];
                start = end;
            } else {
                nums[j++] = nums[start + 1];

                if (end == nums.length) {
                    break;
                }

                nums[j++] = nums[end];
                start = end;
            }
        }

        return j;
    }

    //GOOG http://www.lintcode.com/en/problem/wiggle-sort/
    public void wiggleSort1(int[] nums) {
        // Write your code here
        for(int i=1; i<nums.length; i++) {
            if((i%2==1 && (nums[i] < nums[i-1]) ||
                    (i%2==0) && (nums[i] > nums[i-1]))) {
                swap(nums, i-1, i);
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // http://www.lintcode.com/en/problem/wiggle-sort-ii/
    // 这道题其实就是要找一个中间值，比中间值小的插入到偶数位上，比中间值大的插入到奇数位上。
    // Use extra array int[] temp
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return;
        }

        int n = nums.length;
        int k = (n + 1) / 2;
        int medium = helper(k, nums, 0, n - 1); // <= medium, at the bottom

        int s = 0, t = n - 1;
        int[] temp = new int[n];

        for (int i = 0; i < n; i++) {
            if (nums[i] < medium) {
                temp[s++] = nums[i];
            } else if (nums[i] > medium) {
                temp[t--] = nums[i];
            }
        }

        while (s < k) {
            temp[s++] = medium;
        }
        while (t >= k) {
            temp[t--] = medium;
        }

        // Now three parts: < m, == m, > m
        t = n;
        for (int i = 0; i < n; i++) {
            nums[i] = (i & 1) == 0 ? temp[--s] : temp[--t];
        }
    }

    private int partition(int[] nums, int start, int end, int pivot) {
        int low = start, high = end;

        while (low <= high) {
            while (low <= high && nums[low] < pivot) {
                low++;
            }

            while (low <= high && nums[high] > pivot) {
                high--;
            }

            if (low <= high) {
                swap(nums, low, high);
                low++;
                high--;
            }
        }

        return low;
    }

    private int helper(int k, int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }

        int pivot = nums[start + (end - start) / 2];
        int l = partition(nums, start, end, pivot);

        if (l - start >= k) {
            return helper(k, nums, start, l-1);
        } else {
            return helper(k - l + start, nums, l, end);
        }
    }
}
