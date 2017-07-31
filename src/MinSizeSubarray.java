/**
 * Created by qzhou on 3/8/17.
 * http://www.lintcode.com/en/problem/minimum-size-subarray-sum/
 */
public class MinSizeSubarray {
    // 遍历从index i 开始的subarray, 找出最小长度
    // two points, first one that is ...
    public int minimumSize(int[] nums, int s) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int sum = 0;
        int i = 0, j = 0;
        int min = Integer.MAX_VALUE;

        for (i = 0; i < nums.length; i++) {
            while (j < nums.length && sum < s) {
                sum += nums[j];
                j++;
            }

            if (sum >= s) {
                min = Math.min(min, j - i);
            }

            sum -= nums[i];
        }

        if (min == Integer.MAX_VALUE) {
            return -1;
        }
        return min;
    }

    // http://www.lintcode.com/en/problem/maximum-subarray-iv/
    public int maxSubarray4(int[] nums, int k) {
        int n = nums.length;
        if (n == 0 || n < k || k <= 0) {
            return 0;
        }

        int result = 0;
        for (int i = 0; i < k; i++) {
            result += nums[i];
        }

        int minPre = 0;
        int[] sum = new int[n+1];
        sum[0] = 0;

        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i-1] + nums[i-1];
            if (i >= k) {
                result = Math.max(result, sum[i] - minPre);
                minPre = Math.min(minPre, sum[i - k + 1]);
            }
        }

        return result;
    }

    // http://www.lintcode.com/en/problem/maximum-average-subarray/
    // binary search
    /*
    first we do a binary search on the average and let it be x
    we decrease x from all of the array elements and if there exists a sub array with
    length more than k whose sum is more than zero then we can say that we have such a
    sub array whose average is more than x other wise we can say that there doesnt exist
    any such sub array
    how to find out if there is a sub array whose sum is more than zero and its
    length is more than k? we can say that a sub array [l, r) equals sum[1, r) — sum[1, l)
    so if we get the partial sums and fix the r of the sub array we just need an l
    which sum[1, r) >= sum[1, l) and l <= r — k this can be done with partial minimum of
    the partial sums
    */
    public double maxAverage(int[] nums, int k) {
        // find min value 'l' and max value 'r'
        double l = Integer.MAX_VALUE, r = Integer.MIN_VALUE;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            if (nums[i] < l)
                l = nums[i];
            if (nums[i] > r)
                r = nums[i];
        }

        double[] sum = new double[n + 1];
        sum[0] = 0;
        while (r - l >= 1e-6) {
            double mid = (l + r) / 2.0;

            double min_pre = 0;
            boolean check = false;
            for (int i = 1; i <= n; ++i) {
                sum[i] = sum[i - 1] + nums[i - 1] - mid;
                if (i >= k && sum[i] - min_pre >= 0) {
                    check = true;
                    break;
                }
                if (i >= k)
                    min_pre = Math.min(min_pre, sum[i - k + 1]);
            }
            if (check)
                l = mid;
            else
                r = mid;
        }

        return l;
    }
}
