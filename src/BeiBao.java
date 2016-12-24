/**
 * Created by qzhou on 12/6/16.
 */
public class BeiBao {
    /* EBAY
     * Given a non-empty array of positive integers,
     * check if it can be broken into two subsets such that their sums equal.
     *
     * For example, [1, 5, 11, 15] => true
     */
    public boolean breakable(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
        }
        if (total % 2 == 1) {
            return false;
        }
        total /= 2;

        // Can find a non-empty combination whose sum is total, one element can be used at most once.
        // 这是最基础的背包问题，特点是：每种物品仅有一件，可以选择放或不放。

        boolean[][] dp = new boolean[nums.length+1][total+1];
        dp[0][0] = true;

        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= total; j++) {
                int remain = j - nums[i-1];
                if (remain >= 0) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][remain];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[nums.length][total];
    }

    // Use less memory
    public boolean breakable2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            total += nums[i];
        }
        if (total % 2 == 1) {
            return false;
        }
        total /= 2;

        // 这是最基础的背包问题，特点是：每种物品仅有一件，可以选择放或不放。

        boolean[] dp = new boolean[total+1];
        dp[0] = true;

        for (int i = 0; i < nums.length; i++) {
            for (int j = total; j >= nums[i]; j--) {
                dp[j] |= dp[j - nums[i]];
            }
        }

        return dp[total];
    }

    // http://www.lintcode.com/en/problem/backpack-vi/
    // [1, 1, 2] and [1, 2, 1] are two solutions
    public int backPackVI(int[] nums, int target) {
        /*
         * 之前几题能够用一维dp表示的本质其实是因为当前行的值只和上一行有关，因此用动态数组两行就行，
         * 如果直接在上一行更新当前行的状态则只需要一行即可，因此其本质就是还是二维dp。
         * 但是这道题真的是一维dp，即当前容量的填装数量只和之前容量填装的结果有关，
         * 只不过每次填装都要遍历整个nums数组来寻找相关的之前容量的状态，因此要用两重for循环。
         */
        int[] f = new int[target + 1];
        f[0] = 1;
        for (int i = 1; i <= target; ++i)
            for (int  j = 0; j < nums.length; ++j)
                if (i >= nums[j])
                    f[i] += f[i - nums[j]];

        return f[target];
    }

    // http://www.lintcode.com/en/problem/number-of-ways-to-represent-n-cents/
    // two dimension
    // dp[i][j] = sum(dp[i-1][j - k * nums[i]), 0 <= k <= C
    /*
     * 这道题物品可以重复选择，所以内层遍历j的时候从小到大遍历，这样物品可以重复选取。比如一开始在j的时候取了i，
     * 然后随着j的增大，在j'的时候又取了i，而恰好j = j' - A[i]，在这种情况下i就被重复选取。
     * 如果从大往小遍历则所有物品只能取一次
     * dp[j] = dp[j] + dp[j - nums[i]] (右边的dp[j]表示上一行中（即i－1件物品）能装满j容量的方法数)
     */
    public int waysNCents(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;

        int[] nums = new int[] {1, 5, 10, 25};
        for (int i = 0; i < nums.length; i++) {
            for (int j = nums[i]; j <= n; j++) {
                dp[j] += dp[j - nums[i]];
            }
        }

        return dp[n];
    }
}
