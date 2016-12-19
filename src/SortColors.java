/**
 * Created by qzhou on 12/11/16.
 */
public class SortColors {
    public void sortColors2(int[] colors, int k) {
        if (colors == null || colors.length == 0 || k < 1) {
            return;
        }

        int start = 0, end = colors.length - 1;
        for (int i = 1; i <= k; i++) {
            start = partition(colors, i, start, end);
        }
    }

    private int partition(int[] colors, int color, int start, int end) {
        if (start + 1 > end) {
            return start + 1;
        }

        int i = start, j = end, temp;
        while (i <= j) {
            while (i <= end && colors[i] == color) {
                i++;
            }
            while (j >= start && colors[j] > color) {
                j--;
            }
            if (i <= j) {
                temp = colors[i];
                colors[i] = colors[j];
                colors[j] = temp;
                i++;
                j--;
            }
        }
        return i;
    }

    // NEED to MEMORIZE!!!
    //FB http://www.lintcode.com/en/problem/sort-colors/
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        // [0, left) is 0, (right, n-1] is 2
        int left = 0, right = nums.length - 1;
        int i = 0;
        while (i <= right) {
            if (nums[i] == 0) {
                swap(nums, left, i);
                left++;
                i++; //new number is either 0 or 1
            } else if (nums[i] == 1) {
                i++;
            } else {
                swap(nums, i, right);
                right--;
            }
        }
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
