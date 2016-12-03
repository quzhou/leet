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

    public void sortColors(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        int pl = 0;
        int pr = a.length - 1;
        int i = 0;
        while (i <= pr) {
            if (a[i] == 0) {
                swap(a, pl, i);
                pl++;
                i++;
            } else if(a[i] == 1) {
                i++;
            } else {
                swap(a, pr, i);
                pr--;
            }
        }
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
