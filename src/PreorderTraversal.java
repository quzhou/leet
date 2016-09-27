/**
 * Created by qzhou on 9/25/16.
 * https://leetcode.com/problems/binary-tree-preorder-traversal/
 *
 * Given a binary tree, return the preorder traversal of its nodes' values.

 For example:
 Given binary tree {1,#,2,3}, return [1,2,3].
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

// Use stack. Put right to stack first.
public class PreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        if (root == null) return ret;
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);

        while (!s.empty()) {
            TreeNode cur = s.pop();
            ret.add(cur.val);
            if (cur.right != null)
                s.push(cur.right);
            if (cur.left != null)
                s.push(cur.left);
        }
        return ret;
    }
}
