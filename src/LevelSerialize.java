/**
 * Created by qzhou on 12/1/16.
 */
import java.util.Queue;
import java.util.LinkedList;

public class LevelSerialize {
    /*
     * Not a complete tree, for example, 1,2,#,3,#,4
     */
    public String serialize(TreeNode root) {
        String str = "";
        if (root == null) {
            return str;
        }

        LinkedList<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        str += root.val;

        while (!q.isEmpty()) {
            TreeNode cur = q.remove();

            if (cur.left == null) {
                str += ",#";
            } else {
                q.add(cur.left);
                str += "," + cur.left.val;
            }
            if (cur.right == null) {
                str += ",#";
            } else {
                q.add(cur.right);
                str += "," + cur.right.val;
            }
        }

        String[] arr = str.split(",");
        int i = arr.length - 1;
        while (i >= 0 && arr[i].equals("#")) {
            i--;
        }
        String newStr = arr[0];
        for (int j = 1; j <= i; j++) {
            newStr += "," + arr[j];
        }
        return newStr;
    }

    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }

        String[] arr = data.split(",");
        if (arr.length == 0 || arr[0].equals("#")) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        boolean left = true;

        for (int i = 1; i < arr.length; i++) {
            TreeNode parent = q.element();
            TreeNode node = null;

            if (!arr[i].equals("#")) {
                node = new TreeNode(Integer.parseInt(arr[i]));
                q.add(node);
            }

            if (left) {
                parent.left = node;
            } else {
                parent.right = node;
                q.remove();
            }
            left = !left;
        }

        return root;
    }

    //===========================================
    // add element to a complete binary tree
    public TreeNode addToComplete(TreeNode root, TreeNode last) {
        if (root == null) {
            return last;
        }
        helper(root, last);
        return root;
    }

    private void helper(TreeNode root, TreeNode last) {
        if (root.left == null && root.right == null) {
            root.left = last;
            return;
        } else if (root.left != null && root.right == null) {
            root.right = last;
            return;
        }

        // has left and right child
        /* Three cases:
         * 1. left complete + right full, not same depth -- add to left subtree
         * 2. left full, right complete, same depth -- add to right subtree
         * 3. full tree, add under left-most node
         */
        int leftLeftDepth = leftDepth(root.left);
        int leftRightDepth = rightDepth(root.left);
        int rightLeftDepth = leftDepth(root.right);
        int rightRightDepth = rightDepth(root.right);
        boolean leftFull = (leftLeftDepth == leftRightDepth);
        boolean rightFull = (rightLeftDepth == rightRightDepth);

        if (leftFull && rightFull && leftRightDepth == rightLeftDepth) {
            TreeNode cur = root;
            while (cur.left != null) {
                cur = cur.left;
            }
            cur.left = last;
            return;
        }

        if (leftFull) {
            helper(root.right, last);
        } else {
            helper(root.left, last);
        }
    }

    private int leftDepth(TreeNode root) {
        int depth = 0;
        TreeNode cur = root;
        while (cur != null) {
            depth++;
            cur = cur.left;
        }
        return depth;
    }

    private int rightDepth(TreeNode root) {
        int depth = 0;
        TreeNode cur = root;
        while (cur != null) {
            depth++;
            cur = cur.right;
        }
        return depth;
    }

    /*
     * (FB) Given a binary tree, find sum of all the left leaves.
     */
    public int leftLeafSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int sum = 0;
        if (root.left != null) {
            if (root.left.left == null && root.left.right == null) {
                sum += root.left.val;
            } else {
                sum += leftLeafSum(root.left);
            }
        }
        if (root.right != null) {
            if (root.right.left != null || root.right.right != null) {
                sum += leftLeafSum(root.right);
            }
        }

        return sum;
    }

    // https://leetcode.com/problems/invert-binary-tree/
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode right = root.right;
        root.right = invertTree(root.left);
        root.left = invertTree(right);

        return root;
    }

    // http://www.lintcode.com/en/problem/remove-node-in-binary-search-tree/
    public TreeNode removeNode(TreeNode root, int value) {
        if (root == null) {
            return root;
        }

        if (root.val == value) {
            if (root.left == null && root.right == null) {
                root = null;
                return root;
            } else if (root.right != null) {
                TreeNode cur = root.right;
                while (cur.left != null) {
                    cur = cur.left;
                }
                // change root value to next largest
                root.val = cur.val;
                root.right = removeNode(root.right, cur.val);
                return root;
            } else {
                TreeNode cur = root.left;
                while (cur.right != null) {
                    cur = cur.right;
                }
                root.val = cur.val;
                root.left = removeNode(root.left, cur.val);
                return root;
            }
        } else {
            root.left = removeNode(root.left, value);
            root.right = removeNode(root.right, value);
            return root;
        }
    }
}
