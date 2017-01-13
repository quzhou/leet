/**
 * Created by qzhou on 1/10/17.
 * https://leetcode.com/problems/single-number-ii/
 *
 * Bit Operation
 */
public class SingleNumber {
    public int singleNumber(int[] nums) {
        int ans = 0;

        for (int i = 0; i < 32; i++) {
            int d = 0;

            // for every bit, it is 3(x1 + ... + xm) + y
            for (int j = 0; j < nums.length; j++) {
                d += (nums[j] >> i) & 1;
            }

            ans |= (d % 3) << i;
        }

        return ans;
    }

    // https://leetcode.com/problems/bitwise-and-of-numbers-range/
    /*
    public int rangeBitwiseAnd(int m, int n) {
        if (n == m) {
            return n;
        }
        if (n - m == 1) {
            return n & m;
        }
        return rangeBitwiseAnd(m / 2, n / 2) << 1;
    }
    */
    // 只需要求两个数字的二进制的最长公共前缀即可！
    public int rangeBitwiseAnd(int m, int n) {
        int ans = 0;

        for (int i = 31; i >= 0; i--) {
            int a = (m >> i) & 1;
            int b = (n >> i) & 1;
            if (a == b) {
                ans |= a << i;
            } else {
                break;
            }
        }

        return ans;
    }

    // https://leetcode.com/problems/single-number-iii/
    public int[] singleNumber3(int[] nums) {
        int bits = 0;
        for (int i = 0; i < nums.length; i++) {
            bits ^= nums[i];
        }

        // now have different bits "bits"
        int i = 0;
        while (((bits >> i) & 1) == 0) {
            i++;
        }
        int lastBit = 1 << i;

        // divide into 2 parts
        int group1 = 0, group2 = 0;
        for (i = 0; i < nums.length; i++) {
            if ((lastBit & nums[i]) == 0) {
                group1 ^= nums[i];
            } else {
                group2 ^= nums[i];
            }
        }

        int[] ans = new int[2];
        ans[0] = group1;
        ans[1] = group2;
        return ans;
    }

    // http://www.lintcode.com/en/problem/update-bits/
    public int updateBits(int n, int m, int i, int j) {
        int x = (n >> i) ^ m;

        int ans = n;
        for (int k = i; k <= j; k++) {
            int bit = (x >> (k-i)) & 1;
            ans ^= (bit << k);
        }

        return ans;
    }
}
