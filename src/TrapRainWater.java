/**
 * Created by qzhou on 2/1/17.
 * http://www.lintcode.com/en/problem/trapping-rain-water/
 */
public class TrapRainWater {
    public int trapRainWater(int[] heights) {
        int n = heights.length;
        int sum = 0;
        int l = 0, r = n - 1;

        while (l < r) {
            if (heights[l] < heights[r]) {
                int i = l + 1;
                while (i < r && heights[i] < heights[l]) {
                    sum += heights[l] - heights[i];
                    i++;
                }
                l = i;
            } else {
                int j = r - 1;
                while (j > l && heights[j] < heights[r]) {
                    sum += heights[r] - heights[j];
                    j--;
                }
                r = j;
            }
        }

        return sum;
    }
}
