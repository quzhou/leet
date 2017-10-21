/**
 * Created by quzhou on 10/18/17.
 */
public class CompleteTree {
    private class ResType {
        public boolean full;
        public boolean complete;
        public int depth;
        public ResType(boolean full, boolean complete, int depth) {
            this.full = full;
            this.complete = complete;
            this.depth = depth;
        }
    }

    public boolean isValid(TreeNode root) {
        return helper(root).complete;
    }

    public ResType helper(TreeNode root) {
        ResType res = new ResType(false, false, 0);
        if (root == null) {
            res.complete = true;
            res.full = true;
            return res;
        }

        ResType left = helper(root.left);
        ResType right = helper(root.right);
        res.depth = 1 + Math.max(left.depth, right.depth);

        if (!left.complete || !right.complete) {
            res.complete = false;
            return res;
        }

        if ((left.depth == right.depth && left.full) ||
                (left.depth == right.depth + 1 && right.full)) {
            res.complete = true;
            res.full = (left.full && right.full && left.depth == right.depth);
        } else {
            res.complete = false;
        }

        return res;
    }
}
