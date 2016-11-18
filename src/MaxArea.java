/**
 * Created by qzhou on 11/20/16.
 * http://www.lintcode.com/en/problem/largest-rectangle-in-histogram/
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
}
