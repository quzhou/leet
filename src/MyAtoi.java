/**
 * Created by qzhou on 8/16/17.
 */
public class MyAtoi {
    public int myAtoi(String str) {
        str = str.trim();
        if (str.isEmpty()) {
            return 0;
        }

        boolean neg = false;
        int start = 0;
        char[] buf = str.toCharArray();
        if (buf[0] == '+') {
            start = 1;
        } else if (buf[0] == '-') {
            start = 1;
            neg = true;
        }

        if (start < buf.length && !Character.isDigit(buf[start])) {
            return 0;
        }

        long ans = 0;
        for (int i = start; i < buf.length; i++) {
            if (!Character.isDigit(buf[i])) {
                break;
            }

            ans = ans * 10 + buf[i] - '0';
            if (ans > Integer.MAX_VALUE && !neg) {
                return Integer.MAX_VALUE;
            } else if (ans > Integer.MAX_VALUE + (long)1 && neg) {
                return Integer.MIN_VALUE;
            }
        }

        if (neg) {
            ans = -ans;
        }
        return (int)ans;
    }
}
