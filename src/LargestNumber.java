/**
 * Created by qzhou on 1/31/17.
 * http://www.lintcode.com/en/problem/largest-number/
 */
import java.util.Comparator;
import java.util.Arrays;

public class LargestNumber {
    public String largestNumber(int[] num) {
        // 放在前面的是小的, return -1
        Comparator<String> myComp = new Comparator<String>() {
            public int compare(String s1, String s2) {
                //return -str1.compareTo(str2); // This way does not work, 98 > 9, 3 & 39

                return (s2 + s1).compareTo(s1 + s2);
            }
        };

        String[] sorted = new String[num.length];
        for (int i = 0; i < num.length; i++) {
            sorted[i] = String.valueOf(num[i]);
        }
        Arrays.sort(sorted, myComp);

        if (sorted[0].equals("0")) {
            return "0";
        }

        String ans = "";
        for (int i = 0; i < sorted.length; i++) {
            ans += sorted[i];
        }

        return ans;
    }

    // http://www.lintcode.com/en/problem/string-to-integer-ii/
    public int atoi(String str) {
        if (str.isEmpty()) {
            return 0;
        }

        boolean neg = false;
        char[] s = str.toCharArray();

        int i = 0;
        while (s[i] == ' ') {
            i++;
        }
        if (s[i] == '-') {
            i++;
            neg = true;
        } else if (s[i] == '+') {
            i++;
        }

        // use double to store result
        double ans = 0;

        while (i < s.length && s[i] != '.' && s[i] != ' ' && Character.isDigit(s[i])) {
            ans = ans * 10 + (s[i] - '0');
            i++;
        }

        if (neg) {
            ans = -ans;
        }

        if (ans > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (ans < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else {
            return (int)ans;
        }
    }

    // http://www.lintcode.com/en/problem/binary-representation/
    public String binaryRepresentation(String n) {
        if (n.indexOf('.') == -1) {
            return parseInteger(n);
        }

        String[] arr = n.split("\\.");
        if (arr.length > 2) {
            return "ERROR";
        }

        String flt = parseFloat(arr[1]);
        if (flt == "ERROR") {
            return flt;
        }
        if (flt.equals("0") || flt.equals("")) {
            return parseInteger(arr[0]);
        }

        return parseInteger(arr[0]) + "." + flt;
    }

    private String parseInteger(String str) {
        if (str.isEmpty() || str.equals("0")) {
            return "0";
        }

        int num = Integer.parseInt(str);
        String ret = "";

        while (num > 0) {
            ret = (num & 1) + ret;
            num >>= 1;
        }

        return ret;
    }

    private String parseFloat(String str) {
        if (str.isEmpty()) {
            return "";
        }

        double[] arr = new double[32];
        double num = 0.5;
        for (int i = 0; i < 32; i++) {
            arr[i] = num;
            num /= 2;
        }

        String ret = "";
        double d = Double.parseDouble("0." + str);

        for (int idx = 0; idx < 32; idx++) {
            if (d == 0) {
                break;
            }

            if (d >= arr[idx]) {
                d -= arr[idx];
                ret += "1";
            } else {
                ret += "0";
            }
        }

        if (d != 0) {
            return "ERROR";
        }
        return ret;
    }

    // http://www.lintcode.com/en/problem/delete-digits/
    // Number can not start with 0
    public String DeleteDigits(String A, int k) {
        int n = A.length();
        if (k == n) {
            return "0";
        }
        if (k == 0) {
            return A;
        }

        char[] buf = A.toCharArray();
        int idx = -1;
        int len = n - k;
        String ans = "";

        for (int i = 0; i < n - k; i++) {
            idx = helper(buf, idx + 1, len);
            len--;

            if (!ans.isEmpty() || buf[idx] != '0') {
                ans += buf[idx];
            }
        }

        return ans;
    }

    // return first index of the smallest digit
    private int helper(char[] buf, int start, int strLen) {
        int idx = start;
        for (int i = start + 1; i <= buf.length - strLen; i++) {
            if (buf[i] < buf[idx]) {
                idx = i;
            }
        }
        return idx;
    }
}
