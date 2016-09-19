/**
 * Created by qzhou on 9/28/15.
 */
public class NumPalindrome {
    public static boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x < 10) return true;
        int n = x, len = 1;
        while (n/10 > 0) {
            n = n / 10;
            len++;
        }

        int i = 0, j = len - 1, a, b;
        while (i < j) {
            a = (int)(x / Math.pow(10, i) % 10);
            b = (int)(x / Math.pow(10, j) % 10);
            if (a != b) return false;
            i++; j--;
        }
        return true;
    }

    public static boolean isPalindrome2(int x) {
        if (x < 0) return false;
        if (x < 10) return true;
        int value = x, reverse = 0;
        while (value/10 > 0) {
            reverse = reverse * 10 + value % 10;
            value = value / 10;
        }
        if (reverse  == ((double)(x - value)) / 10) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        int x = 1200021;
        System.out.println(isPalindrome2(x));
    }
}
