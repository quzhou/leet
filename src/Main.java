/**
 * Created by qzhou on 9/20/16.
 */
public class Main {
    public static void main(String[] args) {
        LongestIncreaseSubsequence instance = new LongestIncreaseSubsequence();
        int[] arr = new int[] {10,9,2,5,3,7,101,18};
        System.out.println(instance.lengthOfLIS(arr));
    }
}
