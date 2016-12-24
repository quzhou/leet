/**
 * Created by qzhou on 9/20/16.
 */
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Stack;

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

        SequenceReconstruction ins = new SequenceReconstruction();
        int[] org = new int[] {1};
        int[][] seqs = new int[][] {{1}, {2, 3}, {3, 2}};

        boolean res = ins.sequenceReconstruction(org, seqs);
        System.out.println(res);
    }
}
