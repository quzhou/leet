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
    // O(n)
    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        int[] lastIdx = new int[256];
        for (int i = 0; i < 256; i++) {
            lastIdx[i] = -1;
        }
        int startIdx = 0;
        for (int i = 0; i < s.length(); i++) {
            if (lastIdx[s.charAt(i)] > 0 && startIdx <= lastIdx[s.charAt(i)]) {
                startIdx = lastIdx[s.charAt(i)] + 1;
            }
            lastIdx[s.charAt(i)] = i;
            max = Math.max(max, i - startIdx + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        String str1 = "abcabcdebb"; //5
        String str2 = "dvdf"; //3
        String str3 = "vqblqcb"; //4
        String str4 = "abba"; //2
        String str5 = "bbb"; //1
        System.out.println(lengthOfLongestSubstring(str4));
        //System.out.println(lengthOfLongestSubstring(str2));
        //System.out.println(lengthOfLongestSubstring(str3));
    }
}
