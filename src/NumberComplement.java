/**
 * Created by qzhou on 9/17/17.
 * https://leetcode.com/problems/number-complement/description/
 */
public class NumberComplement {
    public int findComplement(int num) {
        // find number of bits
        int len = 0, cur = num;
        while (cur > 0) {
            len++;
            cur >>= 1;
        }

        int mask = (1 << len) - 1;
        int res = num ^ mask;
        return res;
    }
}
