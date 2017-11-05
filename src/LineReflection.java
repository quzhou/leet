/**
 * Created by quzhou on 10/24/17.
 * https://leetcode.com/problems/line-reflection/description/
 */
import java.util.*;

public class LineReflection {
    // two same points are considered as one
    private boolean symmetric(List<Integer> list, double mid) {
        int n = list.size();
        if (n % 2 == 1) {
            int m = (n - 1) / 2;
            if ((double)list.get(m) != mid) {
                return false;
            }
        }

        int i = 0, j = n - 1;
        while (i <= j) {
            double left = mid - list.get(i);
            double right = list.get(j) - mid;
            if (left != right) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public boolean isReflected(int[][] points) {
        Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();

        for (int i = 0; i < points.length; i++) {
            int x = points[i][0];
            int y = points[i][1];

            if (map.containsKey(y)) {
                map.get(y).add(x);
            } else {
                Set<Integer> set = new HashSet<Integer>();
                set.add(x);
                map.put(y, set);
            }
        }

        double mid = 0.0;
        boolean first = true;
        for (Set<Integer> set : map.values()) {
            List<Integer> list = new ArrayList(set);
            Collections.sort(list);

            if (first) {
                mid = (list.get(0) + list.get(list.size() - 1)) / 2.0;
                first = false;
            }

            if (!symmetric(list, mid)) {
                return false;
            }
        }

        return true;
    }
}
