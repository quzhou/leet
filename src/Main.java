/**
 * Created by qzhou on 9/20/16.
 */
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Serialize instance = new Serialize();
        //String str = "0 2 4 1 # 3 7 5 11 # 6 # 8 # # # # # # # #";
        String str = "5 14 # 1 # # #";
        TreeNode tree= instance.deserialize2(str);

        int[] nums1 = new int[] {1, 3, 7, 17, 23};
        int[] nums2 = new int[] {2, 4, 6, 15, 16, 77};
        TwoSortedMedian ins = new TwoSortedMedian();
        System.out.println(ins.findMedianSortedArrays(nums1, nums2));
    }
}
