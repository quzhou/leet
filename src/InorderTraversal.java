/**
 * Created by qzhou on 9/23/16.
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 *
 * Given a binary tree, return the inorder traversal of its nodes' values.

 For example:
 Given binary tree [1,null,2,3], return [1,3,2].
 *
 * 非递归版本先将所有左子树入栈，之后将pop node 的右子树的所有左子树入栈。
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class InorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> nodes = new ArrayList<Integer>();
        Stack<TreeNode> s = new Stack<TreeNode>();
        TreeNode cur = root;
        while (cur != null) {
            s.push(cur);
            cur = cur.left;
        }
        while (!s.empty()) {
            cur = s.pop();
            nodes.add(cur.val);
            if (cur.right != null) {
                cur = cur.right;
                while (cur != null) {
                    s.push(cur);
                    cur = cur.left;
                }
            }
        }
        return nodes;
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        if (root == null) {
            return ret;
        }
        ret.addAll(inorderTraversal2(root.left));
        ret.add(root.val);
        ret.addAll(inorderTraversal2(root.right));
        return ret;
    }
}
