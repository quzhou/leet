/**
 * Created by qzhou on 9/21/16.
 * https://leetcode.com/problems/house-robber-iii/
 *
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area,
 * called the "root." Besides the root, each house has one and only one parent house.
 * After a tour, the smart thief realized that "all houses in this place forms a binary tree".
 * It will automatically contact the police if two directly-linked houses were broken into on
 * the same night.
 Determine the maximum amount of money the thief can rob tonight without alerting the police.
 */
/* Traverse down the tree recursively. We can use an array to keep 2 values: the maximum money when
 * a root is selected and the maximum value when a root if NOT selected.
 */
public class HouseRobber3 {
    public int rob(TreeNode root) {
        int[] dp = helper(root);
        return Math.max(dp[0], dp[1]);
    }

    // return [rootMax, noRootMax]
    public int[] helper(TreeNode root) {
        int[] dp = new int[2];
        if (root == null) return dp;
        if (root.left == null && root.right == null) {
            dp[0] = root.val;
            return dp;
        }
        int[] leftDp = helper(root.left);
        int[] rightDp = helper(root.right);
        dp[0] = root.val + leftDp[1] + rightDp[1];
        dp[1] = Math.max(leftDp[0], leftDp[1]) + Math.max(rightDp[0], rightDp[1]);
        return dp;
    }
}
