/**
 * Created by qzhou on 10/29/16.
 * http://www.lintcode.com/en/problem/find-minimum-in-rotated-sorted-array-ii/
 */
public class FindMinSortedArray {
    public int findMin(int[] num) {
        if (num == null || num.length == 0) {
            return 0;
        }

        int low = 0, high = num.length - 1, mid;

        while (low + 1 < high) {
            mid = low + (high - low) / 2;

            if (num[mid] < num[high]) {
                high = mid;
            } else if (num[mid] > num[high]) {
                low = mid;
            } else {
                high--;
            }
        }

        if (num[low] <= num[high]) {
            return num[low];
        } else {
            return num[high];
        }
    }
}
