/**
 * Created by qzhou on 9/25/16.
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 Note:
 You may assume that duplicates do not exist in the tree.
 */
public class ConstructPreorderInorder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length != inorder.length || preorder.length == 0) return null;
        return helper(preorder, 0, preorder.length-1, inorder, 0, preorder.length-1);
    }

    TreeNode helper(int[] pre, int preLow, int preHigh, int[] in, int inLow, int inHigh) {
        if (preLow > preHigh || inLow > inHigh) return null;
        TreeNode root = new TreeNode(pre[preLow]);
        if (preLow == preHigh) return root;
        int rootIdx = -1;
        for (int i = inLow; i <= inHigh; i++) {
            if (in[i] == pre[preLow]) {
                rootIdx = i;
                break;
            }
        }
        if (rootIdx == -1) return null;
        root.left = helper(pre, preLow+1, rootIdx - inLow + preLow, in, inLow, rootIdx - 1);
        root.right = helper(pre, rootIdx - inLow + preLow + 1, preHigh, in, rootIdx + 1, inHigh);
        return root;
    }
}
