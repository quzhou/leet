import java.util.HashMap;

/**
 * Created by quzhou on 9/3/15.
 * https://leetcode.com/problems/two-sum/
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 * The function twoSum should return indices of the two numbers such that they add up to the target,
 * where index1 must be less than index2. Please note that your returned answers (both index1 and index2)
 * are not zero-based.
 * You may assume that each input would have exactly one solution.
 * Input: numbers={2, 7, 11, 15}, target=9
 * Output: index1=1, index2=2
 */
public class TwoSum {
    // brutal force
    public static int[] findTwo(int[] a, int target) {
        int[] ret = {-1, -1};
        for (int i = 0; i < a.length; i++) {
            for (int j = i+1; j < a.length; j++) {
                if (a[i] + a[j] == target) {
                    ret[0] = i + 1;
                    ret[1] = j + 1;
                    return ret;
                }
            }
        }
        return ret;
    }

    // hash table
    public static int[] findTarget(int[] a, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] ret = new int[2];
        for (int i = 0; i < a.length; i++) {
            Integer idx = map.get(target - a[i]);
            if (idx != null) {
                ret[0] = Math.min(i+1, idx+1);
                ret[1] = Math.max(i+1, idx+1);
                break;
            } else {
                map.put(a[i], i);
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] a = {5, 6, 3, 2, 10, 7, 9, 3};
        int[] ret = findTwo(a, 17);
        System.out.println(ret[0]);
        System.out.println(ret[1]);
    }
}
