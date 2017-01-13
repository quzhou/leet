/**
 * Created by qzhou on 1/12/17.
 * https://leetcode.com/problems/contains-duplicate-iii/
 *
 * bucket
 * 思想是分成t+1个桶，对于一个数，将其分到第num / (t + 1) 个桶中，我们只需要查找相同的和相邻的桶的元素就可以判断有无重复。
 * 比如t = 4，那么0~4为桶0，5~9为桶1，10~14为桶2
 */

import java.util.HashMap;

public class ContainsDuplicate3 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (t < 0) {
            return false;
        }

        // bucket_id, value
        HashMap<Long, Long> map = new HashMap<Long, Long>();
        long div = t + 1;
        for (int i = 0; i < nums.length; i++) {
            long idx = nums[i] / div;

            if (map.containsKey(idx)) { // in same bucket
                // [-3, 3], div is 5
                if (Math.abs(map.get(idx) - nums[i]) <= t) {
                    return true;
                }
            }

            if (map.containsKey(idx - 1)) {
                if (Math.abs(map.get(idx - 1) - nums[i]) <= t) {
                    return true;
                }
            }

            if (map.containsKey(idx+1)) {
                if (Math.abs(map.get(idx + 1) - nums[i]) <= t) {
                    return true;
                }
            }

            map.put(idx, (long)nums[i]);
            if (i >= k) {
                map.remove(nums[i-k] / div);
            }
        }

        return false;
    }
}
