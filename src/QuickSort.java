/**
 * Created by qzhou on 11/24/16.
 */
public class QuickSort {
    public void sortIntegers2(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }

        helper(A, 0, A.length -1);
    }

    private void helper(int[] A, int start, int end) {
        if (start >= end) {
            return;
        }

        int pivot = A[start + (end - start) / 2];
        int low = start, high = end;
        while (low <= high) {
            while (low <= end && A[low] < pivot) { //this can NOT be <= !!!
                low++;
            }
            while (high >= start && A[high] > pivot) {
                high--;
            }
            if (low <= high) {
                int temp = A[low];
                A[low] = A[high];
                A[high] = temp;
                low++;
                high--;
            }
        }

        helper(A, start, low - 1);
        helper(A, low, end);
    }
}
