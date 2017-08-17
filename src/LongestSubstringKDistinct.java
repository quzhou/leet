/**
 * Created by qzhou on 8/10/17.
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/description/
 */

import java.util.HashMap;

public class LongestSubstringKDistinct {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k <= 0) {
            return 0;
        }
        HashMap<Character, Integer> hash = new HashMap<Character, Integer>();
        int max = 0, j = 0;

        for (int i = 0; i < s.length(); i++) {
            while (j < s.length()) {
                hash.put(s.charAt(j), j);

                if (hash.size() > k) {
                    break;
                }
                j++;
            }
            max = Math.max(max, j - i);

            int idx = hash.get(s.charAt(i));
            if (idx <= i) {
                hash.remove(s.charAt(i));
            }
        }

        return max;
    }
}
