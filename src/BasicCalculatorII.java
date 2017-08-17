/**
 * Created by qzhou on 8/1/17.
 * https://leetcode.com/problems/basic-calculator-ii/description/
 */
public class BasicCalculatorII {
    public int calculate(String s) {
        int result = 0;
        String[] arr = s.split("[+-]");

        int idx = 0;
        for (int i = 0; i < arr.length; i++) {
            int val = calc(arr[i]);

            if (i == 0) {
                result += val;
            } else {
                idx += arr[i-1].length();
                if (s.charAt(idx) == '+') {
                    result += val;
                } else {
                    result -= val;
                }
                idx++;
            }
        }

        if (arr.length == 0) {
            result += calc(s);
        }
        return result;
    }

    private int calc(String s) {
        int result = 0;
        String[] arr = s.split("[*/]");
        int idx = 0;
        for (int i = 0; i < arr.length; i++) {
            int val = Integer.parseInt(arr[i].trim());

            if (i == 0) {
                result = val;
            } else {
                idx += arr[i-1].length();
                if (s.charAt(idx) == '*') {
                    result *= val;
                } else {
                    result /= val;
                }
                idx++;
            }
        }
        return result;
    }
}
