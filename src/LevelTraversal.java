/**
 * Created by qzhou on 9/24/16.
 * https://leetcode.com/problems/binary-tree-level-order-traversal/
 *
 * Given a binary tree, return the level order traversal of its nodes' values.
 * (ie, from left to right, level by level).
 * For example:
 Given binary tree [3,9,20,null,null,15,7],
 return its level order traversal as:
 [
    [3],
    [9,20],
    [15,7]
 ]
 */

import java.util.List;
import java.util.ArrayList;

public class LevelTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (root == null) {
            return ret;
        }
        List<TreeNode> cur, pre = new ArrayList<TreeNode>();
        pre.add(root);
        while (!pre.isEmpty()) {
            cur = new ArrayList<TreeNode>();
            List<Integer> level = new ArrayList<Integer>();
            for (TreeNode elem : pre) {
                level.add(elem.val);
                if (elem.left != null)
                    cur.add(elem.left);
                if (elem.right != null)
                    cur.add(elem.right);
            }
            ret.add(level);
            pre = cur;
        }
        return ret;
    }

    public List<Integer> levelSum(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();

        dfs(root, 0, res);
        return res;
    }
    private void dfs(TreeNode root, int level, List<Integer> res) {
        if (root == null) {
            return;
        }

        if (res.size() == level) {
            res.add(root.val);
        } else {
            res.set(level, res.get(level) + root.val);
        }

        dfs(root.left, level + 1, res);
        dfs(root.right, level + 1, res);
    }
}
