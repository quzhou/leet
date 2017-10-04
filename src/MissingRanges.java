/**
 * Created by qzhou on 9/22/17.
 * https://leetcode.com/problems/missing-ranges/description/
 */
import java.util.List;
import java.util.ArrayList;

public class MissingRanges {
    // the key here is corner cases
    // 1. empty array
    // 2. Integer out of range
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<String>();
        long start, end;
        if (nums.length == 0) {
            String str = lower + "";
            if (lower != upper) {
                str += "->" + upper;
            }
            result.add(str);
            return result;
        }

        if (lower < nums[0]) {
            if (lower == (long)nums[0] - 1) {
                result.add("" + lower);
            } else {
                result.add(lower + "->" + (nums[0] - 1));
            }
        }

        for (int i = 1; i < nums.length; i++) {
            if ((long)nums[i] > (long)nums[i-1] + 1) {
                start = nums[i-1] + 1;
                end = nums[i] - 1;
                if (start == end) {
                    result.add("" + start);
                } else {
                    result.add(start + "->" + end);
                }
            }
        }

        if (upper > nums[nums.length - 1]) {
            String str = ((long)nums[nums.length - 1] + 1 == upper) ?
                    ("" + upper) : ((nums[nums.length - 1] + 1) + "->" + upper);
            result.add(str);
        }

        return result;
    }
}
