/**
 * Created by qzhou on 9/25/16.
 * https://leetcode.com/problems/binary-tree-postorder-traversal/
 *
 * Given a binary tree, return the postorder traversal of its nodes' values.
 For example:
 Given binary tree {1,#,2,3}, return [3,2,1].
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Collections;

// push left node to stack, then right node to stack, then reverse list
public class PostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        if (root == null) return ret;
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.push(root);
        while (!s.empty()) {
            TreeNode cur = s.pop();
            ret.add(cur.val);
            if (cur.left != null) {
                s.push(cur.left);
            }
            if (cur.right != null) {
                s.push(cur.right);
            }
        }
        Collections.reverse(ret);
        return ret;
    }
}
