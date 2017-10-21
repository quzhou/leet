import java.util.*;

/**
 * https://leetcode.com/problems/palindrome-pairs/description/
 *
 * (The word is palin, find empty string)
 * (The word's reverse is in the map)
 * 1. "ts" "llst", left is palin, right side has reverse
 * 2. left has reverse, right side is palin
 */
public class PalindromePairs {
    // O(n * k^2)
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        // string, index
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        for (int i = 0; i < words.length; i++) {
            int l = 0, r = 0;
            while (l <= r) {
                String sub = words[i].substring(l, r);
                String rev = new StringBuilder(sub).reverse().toString();
                Integer j = map.get(rev);

                // left has reverse, right side is palin
                if (j != null && i != j && l == 0 && isValid(words[i].substring(r))) {
                    List<Integer> pair = new ArrayList<Integer>();
                    pair.add(i);
                    pair.add(j);
                    res.add(pair);
                } else { // left is palin, right side has reverse
                    if (j != null && i != j && l != 0 && isValid(words[i].substring(0, l))) {
                        List<Integer> pair = new ArrayList<Integer>();
                        pair.add(j);
                        pair.add(i);
                        res.add(pair);
                    }
                }

                if (r == words[i].length()) {
                    l++;
                } else {
                    r++;
                }
            }
        }

        return res;
    }

    private boolean isValid(String str) {
        if (str.length() == 0) {
            return true;
        }
        int i = 0, j = str.length() - 1;
        while (i < j) {
            if (str.charAt(i++) != str.charAt(j--)) {
                return false;
            }
        }
        return true;
    }
}
