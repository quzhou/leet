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
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        String ret = "";
        if (root != null) {
            ret += root.val;
            if (!ret.isEmpty())
                ret += " ";
            ret += serialize(root.left);
            if (!ret.isEmpty())
                ret += " ";
            ret += serialize(root.right);
        } else {
            ret += "#";
        }
        return ret;
    }

    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) {
            return null;
        }
        String[] vals = data.split(" ");
        int[] start = new int[1];
        return deserializeHelper(vals, start);
    }

    TreeNode deserializeHelper(String[] vals, int[] start) {
        if (start[0] >= vals.length) {
            return null;
        }
        if (vals[start[0]].equals("#")) {
            start[0] += 1;
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(vals[start[0]]));
        start[0] += 1;
        root.left = deserializeHelper(vals, start);
        root.right = deserializeHelper(vals, start);
        return root;
    }

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
