/**
 * Created by quzhou on 9/5/15.
 * https://leetcode.com/problems/add-binary/
 * Given two binary strings, return their sum (also a binary string).
 * For example,
 * a = "11"
 * b = "1"
 * Return "100".
 *
 * StringBuilder vs StringBuffer(synchronized)
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class AddBinary {
    public static String add(String a, String b) {
        char[] ca = a.toCharArray();
        char[] cb = b.toCharArray();
        reverse(ca); reverse(cb);
        int len = Math.max(ca.length, cb.length);
        char[] res = new char[len];
        int carry = 0, i;
        for (i = 0; i < len; i++) {
            int temp = getNum(ca, i) + getNum(cb, i) + carry;
            carry = temp / 2;
            res[i] = (char)('0' + temp % 2);
        }
        reverse(res);
        String result = new String(res);
        if (carry > 0) {
            return ('1' + result);
        } else {
            return result;
        }
    }

    public static void reverse(char[] a) {
        int i = 0, j = a.length - 1;
        char temp;
        while (i <= j) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i++; j--;
        }
    }

    public static int getNum(char[] a, int idx) {
        if (idx < a.length) {
            return a[idx] - '0';
        } else {
            return 0;
        }
    }

    // For testing other problems
    public String countAndSay(int n) {
        String cur = "1", next;
        for (int i = 1; i < n; i++) {
            next = "";
            char digit = 0;
            int count = 0;
            for (int j = 0; j < cur.length(); j++) {
                if (cur.charAt(j) != digit) {
                    if (count != 0) {
                        next += count + "" + digit;
                    }
                    count = 1;
                    digit = cur.charAt(j);
                } else {
                    count++;
                }
            }
            next += count + "" + digit;
            cur = next;
        }
        return cur;
    }

    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) {
            return result;
        }

        if (root.left == null && root.right == null) {
            if (root.val == target) {
                List<Integer> path = new ArrayList<Integer>();
                path.add(target);
                result.add(path);
            }
        } else if (root.left != null && root.right != null) {
            List<List<Integer>> leftResult =
                    binaryTreePathSum(root.left, target - root.val);
            List<List<Integer>> rightResult =
                    binaryTreePathSum(root.right, target - root.val);

            for (List<Integer> list : leftResult) {
                list.add(0, root.val);
                result.add(list);
            }
            for (List<Integer> list : rightResult) {
                list.add(0, root.val);
                result.add(list);
            }
        } else {
            TreeNode child = (root.left != null) ? root.left : root.right;
            List<List<Integer>> subResult =
                    binaryTreePathSum(child, target - root.val);

            for (List<Integer> list : subResult) {
                list.add(0, root.val);
                result.add(list);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String a = "111";
        String b = "111";
        //int val1 = Integer.parseInt(a, 2);
        //int val2 = Integer.parseInt(b, 2);
        String c = add(a, b);
        //System.out.println(val1);
        //System.out.println(val2);
        System.out.println(c);
        //System.out.println(Integer.parseInt(c, 2));
    }
}
