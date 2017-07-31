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
/*
        DirectedGraphNode n1 = new DirectedGraphNode(1);
        DirectedGraphNode n2 = new DirectedGraphNode(2);
        DirectedGraphNode n4 = new DirectedGraphNode(4);
        n1.neighbors.add(n2);
        n1.neighbors.add(n4);

        n2.neighbors.add(n4);

        DirectedGraphNode n3 = new DirectedGraphNode(3);
        DirectedGraphNode n5 = new DirectedGraphNode(5);
        n3.neighbors.add(n5);

        DirectedGraphNode n6 = new DirectedGraphNode(6);
        n6.neighbors.add(n5);

        ArrayList<DirectedGraphNode> list = new ArrayList<DirectedGraphNode>();
        list.add(n1);
        list.add(n2);
        list.add(n3);
        list.add(n4);
        list.add(n5);
        list.add(n6);
*/
//        MinSizeSubarray ins = new MinSizeSubarray();
//        int[] arr = {1, 12, -5, -6, 50, 3};
//        double res = ins.maxAverage(arr, 3);

        ReversePairs ins = new ReversePairs();
        int[] arr = new int[6];
        for (int i = 0; i < 6; i++) {
            arr[i] = Integer.MAX_VALUE;
        }
        System.out.println(ins.reversePairs(arr));
    }
}
