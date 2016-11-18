/**
 * Created by qzhou on 9/20/16.
 */
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Serialize instance = new Serialize();
        //String str = "37 -34 -48 # -100 -100 48 # # # # -54 # -71 -22 # # # 8 # #";
        //TreeNode root = instance.deserialize2(str);

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

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        LevelSerialize ins = new LevelSerialize();
        String str = ins.serialize(root);

        System.out.println(str);
    }
}
