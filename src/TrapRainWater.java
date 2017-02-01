/**
 * Created by qzhou on 2/1/17.
 * http://www.lintcode.com/en/problem/trapping-rain-water/
 */
public class TrapRainWater {
    public int trapRainWater(int[] heights) {
        if (heights.length < 3) {
            return 0;
        }

        int i = 0, j = heights.length - 1;
        int res = 0;
        int left = heights[i], right = heights[j];

        while (i <= j) {
            if (left < right) {
                if (heights[i] < left) {
                    res += (left - heights[i]);
                } else {
                    left = heights[i];
                }
                i++;
            } else {
                if (heights[j] < right) {
                    res += right - heights[j];
                } else {
                    right = heights[j];
                }
                j--;
            }
        }

        return res;
    }
}
