import java.util.Arrays;

/**
 * Created by qzhou on 9/20/15.
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * Given a string, find the length of the longest substring without repeating characters. For example,
 * the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3.
 * For "bbbbb" the longest substring is "b", with the length of 1.
 */
public class LongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            boolean[] set = new boolean[256];
            int j;
            for (j = i; j < s.length(); j++) {
                if (set[s.charAt(j)]) {
                    break;
                } else {
                    set[s.charAt(j)] = true;
                }
            }
            if (j - i > max) {
                max = j - i;
            }
        }
        return max;
    }

    // O(N)
    public static int lengthOfLongestSubstring2(String s) {
        int maxLen = 0, endLen = 0, startIdx = 0, lastIdx;
        int[] set = new int[256];
        Arrays.fill(set, -1);
        for (int i = 0; i < s.length(); i++) {
            lastIdx = set[s.charAt(i)];
            if (lastIdx == -1) {
                set[s.charAt(i)] = i;
            } else {
                startIdx = lastIdx + 1;
                set[s.charAt(i)] = i;
            }
            endLen = i - startIdx + 1;
            if (endLen > maxLen) {
                maxLen = endLen;
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        String str1 = "abcabcdebb"; //5
        String str2 = "dvdf"; //3
        String str3 = "vqblqcb"; //4
        String str4 = "abba"; //2
        String str5 = "bbb"; //1
        System.out.println(lengthOfLongestSubstring2(str4));
        //System.out.println(lengthOfLongestSubstring2(str2));
        //System.out.println(lengthOfLongestSubstring2(str3));
    }
}
