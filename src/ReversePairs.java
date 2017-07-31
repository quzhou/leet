/**
 * http://www.lintcode.com/en/problem/reverse-pairs/
 * https://leetcode.com/problems/reverse-pairs/
 */
public class ReversePairs {
    /**
     * @return total of reverse pairs
     */
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] temp = new int[nums.length];
        return mergeSort(nums, 0, nums.length - 1, temp);
    }

    private int mergeSort(int[] arr, int start, int end, int[] temp) {
        if (start >= end) {
            return 0;
        }

        int sum = 0;
        int mid = start + (end - start) / 2;
        sum += mergeSort(arr, start, mid, temp); //two points at left
        sum += mergeSort(arr, mid+1, end, temp); //two points at right
        sum += merge(arr, start, mid, end, temp); //one point at each side, each side is sorted
        return sum;
    }

    private int merge(int[] arr, int start, int mid, int end, int[] temp) {
        int sum = 0;
        int lIdx = start, rIdx = mid+1;

        while (lIdx <= mid && rIdx <= end) {
            if (arr[lIdx] / 2.0 <= arr[rIdx]) {
                lIdx++;
            } else {
                sum += mid - lIdx + 1;
                rIdx++;
            }
        }

        // now merge
        lIdx = start;
        rIdx = mid + 1;
        int i = start;
        while (lIdx <= mid && rIdx <= end) {
            if (arr[lIdx] <= arr[rIdx]) {
                temp[i++] = arr[lIdx++];
            } else {
                temp[i++] = arr[rIdx++];
            }
        }
        while (lIdx <= mid) {
            temp[i++] = arr[lIdx++];
        }
        while (rIdx <= end) {
            temp[i++] = arr[rIdx++];
        }
        for (i = start; i <= end; i++) {
            arr[i] = temp[i];
        }

        return sum;
    }
}

