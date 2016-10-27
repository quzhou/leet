/**
 * Created by qzhou on 9/20/16.
 */
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Serialize instance = new Serialize();
        //String str = "37 -34 -48 # -100 -100 48 # # # # -54 # -71 -22 # # # 8 # #";
        String str = "2 1 # # #";
        TreeNode root = instance.deserialize2(str);

        Random rand = new Random();
        int randNum = rand.nextInt(100);

//        int val = (2 << 30) - 1;
//        System.out.println(val);
//        System.out.println(Integer.MAX_VALUE);

        BSTIterator ins = new BSTIterator(root);
        while (ins.hasNext()) {
            TreeNode cur = ins.next();
            System.out.println(cur.val);
        }
    }
}
