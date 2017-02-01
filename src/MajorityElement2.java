/**
 * Created by qzhou on 9/22/16.
 * http://www.lintcode.com/en/problem/majority-number-ii/
 * https://leetcode.com/problems/majority-element-ii/
 *
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * The algorithm should run in linear time and in O(1) space.
 */
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class MajorityElement2 {
    public int majorityNumber(ArrayList<Integer> nums) {
        int cnt1 = 0, cnt2 = 0;
        int candi1 = 0, candi2 = 0;

        for (Integer num : nums) {
            if (num == candi1) {
                cnt1++;
            } else if (num == candi2) {
                cnt2++;
            } else if (cnt1 == 0) {
                candi1 = num;
                cnt1 = 1;
            } else if (cnt2 == 0) {
                candi2 = num;
                cnt2 = 1;
            } else {
                cnt1--;
                cnt2--;
            }
        }

        cnt1 = cnt2 = 0;
        for (Integer num : nums) {
            if (num == candi1) {
                cnt1++;
            } else if (num == candi2) {
                cnt2++;
            }
        }
        return cnt1 > cnt2 ? candi1 : candi2;
    }

    //http://www.lintcode.com/en/problem/majority-number-iii/
    // O(n) time and O(k) extra space
    public int majorityNumber(ArrayList<Integer> nums, int k) {
        HashMap<Integer, Integer> cnts = new HashMap<Integer, Integer>();

        for (Integer num : nums) {
            if (cnts.containsKey(num)) {
                cnts.put(num, cnts.get(num) + 1);
            } else {
                cnts.put(num, 1);
            }

            if (cnts.size() >= k) {
                removeKey(cnts);
            }
        }

        if (cnts.size() == 0) {
            return Integer.MIN_VALUE;
        }

        for (Integer key : cnts.keySet()) {
            cnts.put(key, 0);
        }

        // all the candidates
        int maxCnt = 0, maxKey = 0;
        for (Integer num : nums) {
            if (cnts.containsKey(num)) {
                cnts.put(num, cnts.get(num) + 1);

                if (cnts.get(num) > maxCnt) {
                    maxCnt = cnts.get(num);
                    maxKey = num;
                }
            }
        }

        return maxKey;
    }

    private void removeKey(HashMap<Integer, Integer> cnts) {
        ArrayList<Integer> rmList = new ArrayList<Integer>();

        for (Integer key : cnts.keySet()) {
            cnts.put(key, cnts.get(key) - 1); //每K次出现1次
            if (cnts.get(key) == 0) {
                rmList.add(key);
            }
        }

        for (Integer key : rmList) {
            cnts.remove(key);
        }
    }
}
