/**
 * Created by qzhou on 9/22/15.
 * https://leetcode.com/problems/binary-tree-inorder-traversal/
 */
import java.util.List;
import java.util.ArrayList;

public class BTreeTraversal {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int d) {
            this.val = d;
            this.left = null;
            this.right = null;
        }
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        if (root == null) {
            return ret;
        }
        ret.addAll(inorderTraversal(root.left));
        ret.add(root.val);
        ret.addAll(inorderTraversal(root.right));
        return ret;
    }

    public static List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        if (root == null) {
            return ret;
        }
        return ret;
    }

    public static void main(String[] args) {

    }
}
