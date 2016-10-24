import java.util.ArrayList;
import java.util.List;

/**
 * Created by qzhou on 10/24/16.
 * https://leetcode.com/problems/permutations/
 */
public class Permutation {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums == null) {
            return result;
        }
        if (nums.length == 0) {
            result.add(new ArrayList<Integer>());
            return result;
        }

        List<Integer> list = new ArrayList<Integer>();
        helper(nums, list, result);
        return result;
    }

    private void helper(int[] nums, List<Integer> list,
                        List<List<Integer>> result) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<Integer>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!list.contains(nums[i])) {
                list.add(nums[i]);
                helper(nums, list, result);
                list.remove(list.size() - 1);
            }
        }
    }
}
