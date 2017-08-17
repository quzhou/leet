/**
 * Created by qzhou on 8/14/17.
 * https://leetcode.com/problems/minimum-window-substring/description/
 */
public class MinWindowSubstring {
    public String minWindow(String s, String t) {
        String ans = "";
        int min = Integer.MAX_VALUE;
        int i = 0, j = 0;

        int[] sHash = new int[256];
        int[] tHash = new int[256];
        initTargetHash(tHash, t);

        for (i = 0; i < s.length(); i++) {
            while (j < s.length() && !valid(sHash, tHash)) {
                sHash[s.charAt(j)]++;
                j++;
            }

            if (valid(sHash, tHash)) {
                if (j - i < min) {
                    min = j - i;
                    ans = s.substring(i, j);
                }
            }

            sHash[s.charAt(i)]--;
        }

        return ans;
    }

    boolean valid(int[] sHash, int[] tHash) {
        for (int i = 0; i < 256; i++) {
            if (tHash[i] > sHash[i]) {
                return false;
            }
        }
        return true;
    }

    private void initTargetHash(int[] targetHash, String target) {
        for (char ch : target.toCharArray()) {
            targetHash[ch]++;
        }
    }
}
