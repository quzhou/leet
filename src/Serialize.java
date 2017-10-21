/**
 * Created by qzhou on 9/25/16.
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 *
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be
 * stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the
 * same or another computer environment.
 *
 Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
 serialization/deserialization algorithm should work. You just need to ensure that a binary tree can
 be serialized to a string and this string can be deserialized to the original tree structure.
 */

// Use pre-order or level order (2n+1, 2n+2)

import java.util.List;
import java.util.ArrayList;

public class Serialize {
    private static final String sep = ",";
    private static final String NN = "#";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serHelper(root, sb);
        return sb.toString();
    }

    private void serHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NN).append(sep);
        } else {
            sb.append(root.val).append(sep);
            serHelper(root.left, sb);
            serHelper(root.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] arr = data.split(sep);
        int[] start = new int[1]; //to change value
        return buildTree(arr, start);
    }

    private TreeNode buildTree(String[] arr, int[] start) {
        int idx = start[0];
        if (idx >= arr.length || arr[idx].equals(NN)) {
            start[0]++;
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(arr[idx]));
        start[0]++;
        root.left = buildTree(arr, start);
        root.right = buildTree(arr, start);
        return root;
    }

    // This is using level traversal
    public String serialize2(TreeNode root) {
        String ret = "";
        List<TreeNode> pre = new ArrayList<TreeNode>();
        if (root != null)
            pre.add(root);
        while (!pre.isEmpty()) {
            List<TreeNode> cur = new ArrayList<TreeNode>();
            for (TreeNode elem : pre) {
                if (elem == null) {
                    ret += "# ";
                } else {
                    ret += elem.val + " ";
                    cur.add(elem.left);
                    cur.add(elem.right);
                }
            }
            pre = cur;
        }
        return ret;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize2(String data) {
        if (data == null || data.isEmpty())
            return null;
        String[] vals = data.split(" ");
        if (vals.length == 0)
            return null;
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        List<TreeNode> pre = new ArrayList<TreeNode>();
        pre.add(root);
        int i = 1;
        while (!pre.isEmpty()) {
            List<TreeNode> cur = new ArrayList<TreeNode>();
            for (TreeNode elem : pre) {
                if (vals[i].equals("#")) {
                    elem.left = null;
                } else {
                    elem.left = new TreeNode(Integer.parseInt(vals[i]));
                    cur.add(elem.left);
                }
                i++;
                if (vals[i].equals("#")) {
                    elem.right = null;
                } else {
                    elem.right = new TreeNode(Integer.parseInt(vals[i]));
                    cur.add(elem.right);
                }
                i++;
            }
            pre = cur;
        }
        return root;
    }
}
