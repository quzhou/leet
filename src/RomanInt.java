/**
 * Created by qzhou on 9/28/16.
 * https://leetcode.com/problems/roman-to-integer/
 * https://leetcode.com/problems/integer-to-roman/
 * Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.
 * I, V, X, L, C(100), D, M(1000)
 *
 *
 * A smaller number in front of a larger number means subtraction, all else means addition. For example, IV means 4,
 * VI means 6.

 You would not put more than one smaller number in front of a larger number to subtract.
 For example, IIV would not mean 3.

 You must separate ones, tens, hundreds, and thousands as separate items. That means that 99 is XCIX, 90 + 9,
 but never should be written as IC. Similarly, 999 cannot be IM and 1999 cannot be MIM.
 */
public class RomanInt {
    public int romanToInt(String s) {
        int[] table = new int[256];
        table['I'] = 1;
        table['V'] = 5;
        table['X'] = 10;
        table['L'] = 50;
        table['C'] = 100;
        table['D'] = 500;
        table['M'] = 1000;
        int val = 0;
        int i = 0;
        while (i < s.length()) {
            char elem = s.charAt(i);
            if (i != s.length()-1 && table[elem] < table[s.charAt(i+1)]) {
                val += (table[s.charAt(i+1)] - table[elem]);
                i += 2;
            } else {
                val += table[elem];
                i++;
            }
        }
        return val;
    }

    public String intToRoman(int num) {
        String tmp, ret = "";
        int cur = num, rem;
        int factor = 0;
        char[][] table = new char[][] {
                {'I', 'V', 'X'},
                {'X', 'L', 'C'},
                {'C', 'D', 'M'},
                {'M', '0', '0'}
        };
        while (cur > 0 && factor < 4) {
            rem = cur % 10;
            tmp = "";
            if (0 < rem && rem < 4) {
                for (int i = 0; i < rem; i++) {
                    tmp += table[factor][0];
                }
            } else if (rem == 4) {
                tmp += table[factor][0];
                tmp += table[factor][1];
            } else if (5 <= rem && rem < 9) {
                tmp += table[factor][1];
                for (int i = 0; i < rem - 5; i++) {
                    tmp += table[factor][0];
                }
            } else if (rem == 9) {
                tmp += table[factor][0];
                tmp += table[factor][2];
            }
            ret = tmp + ret;
            cur = cur / 10;
            factor++;
        }
        return ret;
    }
}
