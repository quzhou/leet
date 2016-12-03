/**
 * Created by qzhou on 11/20/16.
 * http://www.lintcode.com/en/problem/largest-rectangle-in-histogram/
 *
 * https://leetcode.com/problems/maximal-rectangle/
 */
import java.util.Stack;

public class MaxArea {
    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int max = 0;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i <= height.length; i++) {
            int cur = (i == height.length) ? -1 : height[i];

            while (!stack.empty() && cur <= height[stack.peek()]) {
                int h = height[stack.pop()];
                int w = stack.empty() ? i : i - stack.peek() - 1;
                max = Math.max(max, h * w);
            }

            stack.push(i);
        }

        return max;
    }

    public int maximalRectangle(boolean[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int m = matrix.length;
        if (matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        int n = matrix[0].length;

        int[][] heights = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    heights[i][j] = matrix[i][j] ? 1 : 0;
                } else {
                    heights[i][j] = matrix[i][j] ? heights[i-1][j] + 1 : 0;
                }
            }
        }

        int max = 0;
        for (int i = 0; i < m; i++) {
            max = Math.max(max, maxArea(heights[i]));
        }

        return max;
    }

    private int maxArea(int[] height) {
        int max = 0;
        Stack<Integer> stack = new Stack<Integer>();

        for (int i = 0; i <= height.length; i++) {
            int cur = (i == height.length) ? -1 : height[i];

            while (!stack.empty() && cur <= height[stack.peek()]) {
                int h = height[stack.pop()];
                int w = stack.empty() ? i : i - stack.peek() - 1;
                max = Math.max(max, h * w);
            }

            stack.push(i);
        }

        return max;
    }
}
