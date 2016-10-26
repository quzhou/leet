/**
 * Created by qzhou on 9/20/16.
 */
import java.util.List;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Serialize instance = new Serialize();
        //String str = "0 2 4 1 # 3 7 5 11 # 6 # 8 # # # # # # # #";
        String str = "5 14 # 1 # # #";
        TreeNode tree= instance.deserialize2(str);

        Permutation ins = new Permutation();
        int[] nums = new int[] {1, 2, 3};
        System.out.println(ins.permute(nums));

        Date date = new Date();
        long ts = date.getTime();
        System.out.println(ts);
    }
}
