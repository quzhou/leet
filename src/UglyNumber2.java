/**
 * Created by qzhou on 11/23/16.
 * http://www.lintcode.com/en/problem/ugly-number-ii/
 */
import java.util.ArrayList;

public class UglyNumber2 {
    public int nthUglyNumber(int n) {
        ArrayList<Integer> uglys = new ArrayList<Integer>();
        uglys.add(1);

        int p2 = 0, p3 = 0, p5 = 0;

        for (int i = 1; i < n; i++) {
            int last = uglys.get(i-1);

            while (uglys.get(p2) * 2 <= last) {
                p2++;
            }
            while (uglys.get(p3) * 3 <= last) {
                p3++;
            }
            while (uglys.get(p5) * 5 <= last) {
                p5++;
            }

            uglys.add(Math.min(Math.min(uglys.get(p2) * 2, uglys.get(p3) * 3),
                    uglys.get(p5) * 5));
        }

        return uglys.get(n-1);
    }
}
