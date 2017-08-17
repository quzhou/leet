/**
 * Created by qzhou on 8/7/17.
 * https://leetcode.com/problems/longest-valid-parentheses/description/
 */
import java.util.Stack;

public class LongestParen {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        int left = -1;

        // ending
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.empty()) {
                    left = i;
                } else {
                    stack.pop();
                    if (stack.empty()) {
                        max = Math.max(max, i - left);
                    } else {
                        max = Math.max(max, i - stack.peek());
                    }
                }
            }
        }

        return max;
    }
}
