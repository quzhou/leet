/**
 * Created by qzhou on 10/3/16.
 * https://leetcode.com/problems/median-of-two-sorted-arrays/
 *
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 */
public class TwoSortedMedian {
    /* http://www.programcreek.com/2012/12/leetcode-median-of-two-sorted-arrays-java/ */

    /* (m + n) / 2 for odd, (m+n)/2 -1 and (m+n)/2 for even
     * k starts with 0
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return 0;
        int m = nums1.length, n = nums2.length;

        if ((n + m) % 2 == 0) {
            return (helper(nums1, nums2, (m + n) / 2, 0, m-1, 0, n-1) +
                    helper(nums1, nums2, (m + n) / 2 - 1, 0, m-1, 0, n-1)) / 2.0;
        } else {
            return helper(nums1, nums2, (m + n) / 2, 0, m-1, 0, n-1);
        }
    }

    double helper(int[] nums1, int[] nums2, int k, int l1, int r1, int l2, int r2) {
        int len1 = r1 - l1 + 1;
        int len2 = r2 - l2 + 1;

        if (len1 == 0) return nums2[l2 + k];
        if (len2 == 0) return nums1[l1 + k];
        if (k == 0) return Math.min(nums1[l1], nums2[l2]);

        int m1 = len1 * k / (len1 + len2);
        int m2 = k - m1 - 1;
        m1 += l1; m2 += l2;

        if (nums1[m1] > nums2[m2]) {
            k = k - (m2 - l2 + 1);
            r1 = m1;
            l2 = m2 + 1;
        } else {
            k = k - (m1 - l1 + 1);
            r2 = m2;
            l1 = m1 + 1;
        }

        return helper(nums1, nums2, k, l1, r1, l2, r2);
    }
}
