/**
 * Created by qzhou on 9/28/16.
 * https://leetcode.com/problems/unique-binary-search-trees-ii/
 *
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1...n.
 */
import java.util.List;
import java.util.ArrayList;

public class UniqueBST2 {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            List<TreeNode> ret = new ArrayList<TreeNode>();
            return ret;
        }
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i+1;
        }
        return helper(nums, 0, n-1);
    }

    List<TreeNode> helper(int[] nums, int low, int high) {
        List<TreeNode> ret = new ArrayList<TreeNode>();
        if (low > high) {
            ret.add(null);
            return ret;
        }
        if (low == high) {
            ret.add(new TreeNode(nums[low]));
            return ret;
        }
        for (int i = low; i <= high; i++) {
            List<TreeNode> leftList = helper(nums, low, i - 1);
            List<TreeNode> rightList = helper(nums, i+1, high);

            for (TreeNode left : leftList) {
                for (TreeNode right : rightList) {
                    TreeNode root = new TreeNode(nums[i]);
                    root.left = left;
                    root.right = right;
                    ret.add(root);
                }
            }
        }
        return ret;
    }
}
