/**
 * Created by qzhou on 9/27/16.
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 *
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 *
 Follow up:
 What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently?
 How would you optimize the kthSmallest routine?
 */
import java.util.Stack;
import java.util.ArrayList;

public class KthElement {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> s = new Stack<TreeNode>();
        int count = 0;
        TreeNode cur = root;
        while (cur != null) {
            s.push(cur);
            cur = cur.left;
        }
        while (!s.empty()) {
            cur = s.pop();
            count++;
            if (count == k) {
                return cur.val;
            }
            if (cur.right != null) {
                cur = cur.right;
                while (cur != null) {
                    s.push(cur);
                    cur = cur.left;
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    public int kthSmallest2(TreeNode root, int k) {
        Stack<TreeNode> s = new Stack<TreeNode>();
        ArrayList<TreeNode> list = new ArrayList<TreeNode>();
        TreeNode cur = root;
        while (cur != null) {
            s.push(cur);
            cur = cur.left;
        }
        while (!s.empty()) {
            cur = s.pop();
            list.add(cur);
            if (cur.right != null) {
                cur = cur.right;
                while (cur != null) {
                    s.push(cur);
                    cur = cur.left;
                }
            }
        }
        return list.get(k-1).val;
    }
}
