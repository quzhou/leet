/**
 * Created by qzhou on 12/1/16.
 */
import java.util.Queue;
import java.util.LinkedList;

public class LevelSerialize {
    public String serialize(TreeNode root) {
        String str = "";
        if (root == null) {
            return str;
        }

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        int queueSize = 1;
        int dataSize = 0;

        while (!q.isEmpty()) {
            if (!str.isEmpty()) {
                str += ",";
            }

            TreeNode node = q.remove();
            queueSize--;

            if (node == null) {
                str += "#";
            } else {
                str += node.val;

                q.add(node.left);
                q.add(node.right);
                if (node.left != null) {
                    dataSize++;
                }
                if (node.right != null) {
                    dataSize++;
                }
            }

            if (queueSize == 0) {
                if (dataSize == 0) {
                    break;
                }

                queueSize = q.size();
                dataSize = 0;
            }
        }

        return str;
    }
}
