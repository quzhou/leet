import java.util.*;

public class ServerName {
    // -2, -3, 5, 4, 1, 2

    public int firstMissingPositive(int[] nums) {
        
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length &&
                nums[i] != i+1 &&
                nums[i] != nums[nums[i] - 1]) {

                int tmp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = tmp;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i+1) {
                return i+1;
            }
        }
        return nums.length + 1;
    }
}