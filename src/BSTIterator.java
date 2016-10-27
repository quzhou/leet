/**
 * Created by qzhou on 11/2/16.
 * http://www.lintcode.com/en/problem/binary-search-tree-iterator
 */
import java.util.Stack;

public class BSTIterator {
    private Stack<TreeNode> stack;

    //@param root: The root of binary tree.
    public BSTIterator(TreeNode root) {
        stack = new Stack<TreeNode>();
        TreeNode cur = root;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
    }

    //@return: True if there has next node, or false
    public boolean hasNext() {
        if (!stack.isEmpty()) {
            return true;
        }
        return false;
    }

    //@return: return next node
    public TreeNode next() {
        if (stack.isEmpty()) {
            return null;
        }

        TreeNode ret = stack.pop();

        TreeNode cur = ret.right;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }

        return ret;
    }
}
