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

//        AlienDictionary ins = new AlienDictionary();
//        String[] words = new String[] {
//            "wrt",
//            "wrf",
//            "ef",
//            "at"
//        };

        Regex ins = new Regex();
        boolean match = ins.isMatch("", "c*c*");
        System.out.println(match);
    }
}
