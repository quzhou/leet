/**
 * Created by qzhou on 8/2/17.
 */
import java.util.Stack;

public class BasicCalculator {
    public int calculate(String s) {
        int len = s.length();
        Stack<Integer> stack = new Stack<Integer>();

        int result = 0;
        int sign = 1;
        int num = 0;

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '+') {
                result += sign * num;
                num = 0;
                sign = 1;
            } else if (c == '-') {
                result += sign * num;
                num = 0;
                sign = -1;
            } else if (c == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
                num = 0;
            } else if (c == ')') {
                result += sign * num; //inside () value
                int tmpSign = stack.pop();
                result = stack.pop() + tmpSign * result;
                num = 0;
            }
        }

        if (num != 0) {
            result += sign * num;
        }
        return result;
    }
}
