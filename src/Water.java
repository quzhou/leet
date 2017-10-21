/**
 * int[] arr is height
 * return int[] for heights of water
 */
import java.util.*;

public class Water {
    int[] getWaterLevel(int[] height, int position, int count) {
        int n = height.length;
        if (n == 0) {
            return new int[0];
        }

        int[] water = new int[n];

        for (int cnt = count; cnt > 0; cnt--) {
            int putLocation = position;
            int left = position, right = position;

            while (left >= 1) {
                if (height[left - 1] + water[left - 1] > height[left] + water[left]) {
                    break;
                }
                left--;
            }
            if (height[left] + water[left] < height[position] + water[position]) {
                putLocation = left;
            } else {
                while (right < n - 1) {
                    if (height[right + 1] + water[right + 1] > height[right] + water[right]) {
                        break;
                    }
                    right++;
                }
                if (height[right] + water[right] < height[position] + water[position]) {
                    putLocation = right;
                }
            }
            water[putLocation]++;
        }

        for (int i = 0; i < n; i++) {
            water[i] += height[i];
        }
        return water;
    }

    public void test() {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] res = getWaterLevel(height, 4, 3);
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }
}
