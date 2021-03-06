/**
 * Created by qzhou on 9/24/16.
 * https://leetcode.com/problems/validate-binary-search-tree/
 *
 * Given a binary tree, determine if it is a valid binary search tree (BST).

 Assume a BST is defined as follows:

 The left subtree of a node contains only nodes with keys less than the node's key.
 The right subtree of a node contains only nodes with keys greater than the node's key.
 Both the left and right subtrees must also be binary search trees.
 */
public class ValidateBST { //or using in order traversal
    private static class ReturnType {
        public boolean valid;
        public int max;
        public int min;
        public ReturnType(boolean valid, int min, int max) {
            this.valid = valid;
            this.min = min;
            this.max = max;
        }
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return helper(root).valid;
    }

    private ReturnType helper(TreeNode root) {
        ReturnType ret = new ReturnType(false, 0, 0);

        if (root.left == null && root.right == null) {
            ret.valid = true;
            ret.min = root.val;
            ret.max = root.val;
            return ret;
        }

        int min = root.val, max = root.val;
        ReturnType left = null, right = null;

        if (root.left != null) {
            left = helper(root.left);
            if (!left.valid) {
                ret.valid = false;
                return ret;
            } else {
                if (left.max >= root.val) {
                    ret.valid = false;
                    return ret;
                }
                min = Math.min(min, left.min);
                max = Math.max(max, left.max);
            }
        }

        if (root.right != null) {
            right = helper(root.right);
            if (!right.valid) {
                ret.valid = false;
                return ret;
            } else {
                if (root.val >= right.min) {
                    ret.valid = false;
                    return ret;
                }
                min = Math.min(min, right.min);
                max = Math.max(max, right.max);
            }
        }

        ret.min = min;
        ret.max = max;
        ret.valid = true;

        return ret;
    }

    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;
        int[] min = new int[1];
        int[] max = new int[1];
        min[0] = root.val;
        max[0] = root.val;
        return helper(root, min, max);
    }

    boolean helper(TreeNode root, int[] min, int[] max) {
        if (root.left == null && root.right == null) {
            min[0] = root.val;
            max[0] = root.val;
            return true;
        }

        if (root.left != null) {
            int[] leftMin = new int[1];
            int[] leftMax = new int[1];
            leftMin[0] = root.left.val;
            leftMax[0] = root.left.val;
            boolean leftRet = helper(root.left, leftMin, leftMax);
            if (!leftRet) return false;
            if (leftMax[0] >= root.val) return false;
            min[0] = Math.min(min[0], leftMin[0]);
            max[0] = Math.max(max[0], leftMax[0]);
        }

        if (root.right != null) {
            int[] rightMin = new int[1];
            int[] rightMax = new int[1];
            rightMin[0] = root.right.val;
            rightMax[0] = root.right.val;
            boolean rightRet = helper(root.right, rightMin, rightMax);
            if (!rightRet) return false;
            if (rightMin[0] <= root.val) return false;
            min[0] = Math.min(min[0], rightMin[0]);
            max[0] = Math.max(max[0], rightMax[0]);
        }

        return true;
    }
}
