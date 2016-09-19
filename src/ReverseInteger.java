/**
 * Created by quzhou on 9/14/15.
 * https://leetcode.com/problems/reverse-integer/
 *
 * Reverse digits of an integer.
 * Example1: x = 123, return 321
 * Example2: x = -123, return -321
 */
public class ReverseInteger {
    public static int reverse(int x) {
        boolean neg = false;
        if (x < 0) {
            neg = true;
            x = -x;
        }
        int[] arr = new int[10];
        int i = 0, value = x, res = 0;
        while (value > 0) {
            arr[i] = value % 10;
            value = value / 10;
            if (i == 9 && (res > (Integer.MAX_VALUE - arr[i]) / 10)) { //overflow
                return 0;
            } else {
                res = res * 10 + arr[i];
            }
            i++;
        }
        if (neg) {
            res = -res;
        }
        return res;
    }

    public static void main(String[] args) {
        int x = -1000000003;
        System.out.println(reverse(x));
    }
}
