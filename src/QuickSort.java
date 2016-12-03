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

    // For fun
    // Find index that is greater than pivot
    private int partition(int[] arr, int start, int end, int pivot) {
        int i = start, j = end;
        while (i <= j) {
            while (i <= j && arr[i] <= pivot) {
                i++;
            }
            while (i <= j && arr[j] > pivot) {
                j--;
            }
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }
        return i;
    }
}
