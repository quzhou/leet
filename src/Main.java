/**
 * Created by qzhou on 9/20/16.
 */
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
//        Random rand = new Random();
//        int randNum = rand.nextInt(100);

//        int val = (2 << 30) - 1;
//        System.out.println(val);
//        System.out.println(Integer.MAX_VALUE);

//        BSTIterator ins = new BSTIterator(root);
//        while (ins.hasNext()) {
//            TreeNode cur = ins.next();
//            System.out.println(cur.val);
//        }


        LongestIncreaseSubsequence ins = new LongestIncreaseSubsequence();
        int[] arr = new int[] {10, 9, 2, 5, 3, 7, 101, 18};
        ins.longestIncreasingSubsequence(arr);

        FirstMissingPositive ins2 = new FirstMissingPositive();
        int[] nums = new int[] {3, 4, -1, 1};
        int number = ins2.firstMissingPositive(nums);
        System.out.println(number);
    }
}
