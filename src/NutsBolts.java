/**
 * Created by qzhou on 2/14/17.
 * http://www.lintcode.com/en/problem/nuts-bolts-problem/
 * Different Element!
 */
public class NutsBolts {
    public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
        if (nuts == null || bolts == null || nuts.length == 0 || bolts.length == 0 ||
                nuts.length != bolts.length) {
            return;
        }

        qsort(nuts, bolts, compare, 0, nuts.length - 1);
    }

    private void qsort(String[] nuts, String[] bolts, NBComparator compare,
                       int l, int u) {
        if (l >= u) {
            return;
        }

        // find the partition index for nuts with bolts[l]
        int partIdx = partition(nuts, bolts[l], compare, l, u);

        // partition bolts with nuts[partIdx]
        partition(bolts, nuts[partIdx], compare, l, u);

        qsort(nuts, bolts, compare, l, partIdx - 1);
        qsort(nuts, bolts, compare, partIdx + 1, u);
    }

    private int partition(String[] items, String pivot, NBComparator compare,
                          int l, int u) {
        for (int i = l; i <= u; i++) {
            if (compare.cmp(items[i], pivot) == 0 ||
                    compare.cmp(pivot, items[i]) == 0) {
                swap(items, i, l);
                break;
            }
        }

        String now = items[l];
        int left = l, right = u;

        while (left < right) {
            while (left < right && (compare.cmp(items[right], pivot) == -1 ||
                    compare.cmp(pivot, items[right]) == 1)) {
                right--;
            }
            items[left] = items[right]; //swap bigger to the left

            while (left < right && (compare.cmp(items[left], pivot) == 1 ||
                    compare.cmp(pivot, items[left]) == -1)) {
                left++;
            }
            items[right] = items[left]; //swap smaller to the right
        }

        items[left] = now;

        // now biggr -> smaller
        return left;
    }

    private void swap(String[] items, int i, int j) {
        String tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }
}
