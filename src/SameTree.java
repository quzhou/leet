/**
 * Created by qzhou on 9/27/16.
 * https://leetcode.com/problems/same-tree/
 *
 * Given two binary trees, write a function to check if they are equal or not.
 Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
 */
public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        else if (p != null && q != null) {
            if (p.val != q.val) return false;
            return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
        } else
            return false;
    }
}
