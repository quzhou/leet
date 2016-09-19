/**
 * Created by qzhou on 10/5/15.
 * https://leetcode.com/problems/longest-palindromic-substring/
 *
 * Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000,
 * and there exists one unique longest palindromic substring.
 */
public class LongestPalindrome {
    public static String longestPalindrome(String s) {

    }

    public static String helper(String s, int start, int end) {
        if (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }
        return s.substring(start+1, end-1);
    }

    public static void main(String[] args) {
        String str1 = "abcabcdebb"; //5
        String str2 = "dvdf"; //3
        String str3 = "vqblqcb"; //4
        String str4 = "abba"; //2
        String str5 = "aabbb"; //1
        System.out.println(longestPalindrome(str5));
    }
}
