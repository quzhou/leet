/**
 * Created by quzhou on 9/5/15.
 * https://leetcode.com/problems/add-binary/
 * Given two binary strings, return their sum (also a binary string).
 * For example,
 * a = "11"
 * b = "1"
 * Return "100".
 *
 * StringBuilder vs StringBuffer(synchronized)
 */
public class AddBinary {
    public static String add(String a, String b) {
        char[] ca = a.toCharArray();
        char[] cb = b.toCharArray();
        reverse(ca); reverse(cb);
        int len = Math.max(ca.length, cb.length);
        char[] res = new char[len];
        int carry = 0, i;
        for (i = 0; i < len; i++) {
            int temp = getNum(ca, i) + getNum(cb, i) + carry;
            carry = temp / 2;
            res[i] = (char)('0' + temp % 2);
        }
        reverse(res);
        String result = new String(res);
        if (carry > 0) {
            return ('1' + result);
        } else {
            return result;
        }
    }

    public static void reverse(char[] a) {
        int i = 0, j = a.length - 1;
        char temp;
        while (i <= j) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i++; j--;
        }
    }

    public static int getNum(char[] a, int idx) {
        if (idx < a.length) {
            return a[idx] - '0';
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        String a = "111";
        String b = "111";
        //int val1 = Integer.parseInt(a, 2);
        //int val2 = Integer.parseInt(b, 2);
        String c = add(a, b);
        //System.out.println(val1);
        //System.out.println(val2);
        System.out.println(c);
        //System.out.println(Integer.parseInt(c, 2));
    }
}
