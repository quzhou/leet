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
    public double findMedianSortedArrays(int[] A, int[] B) {
        if (A == null || B == null) {
            return 0;
        }

        int m = A.length, n = B.length;
        if (m + n == 0) {
            return 0;
        }

        if ((m + n) % 2 == 0) {
            return (findKth((m + n) / 2 - 1, A, 0, m-1, B, 0, n-1) +
                    findKth((m + n) / 2, A, 0, m-1, B, 0, n-1)) / 2;
        } else {
            return findKth((m + n) / 2, A, 0, m-1, B, 0, n-1);
        }
    }

    private double findKth(int k, int[] arr1, int start1, int end1,
                           int[] arr2, int start2, int end2) {
        double len1 = end1 - start1 + 1;
        double len2 = end2 - start2 + 1;

        // 3 exit conditions
        if (len1 == 0) {
            return arr2[start2 + k];
        }
        if (len2 == 0) {
            return arr1[start1 + k];
        }
        if (k == 0) {
            return Math.min(arr1[start1], arr2[start2]);
        }

        int m1 = (int)(len1 / (len1 + len2) * k);
        int m2 = k - 1 - m1;

        if (arr1[start1 + m1] < arr2[start2 + m2]) {
            return findKth(k - 1 - m1, arr1, start1 + m1 + 1, end1,
                    arr2, start2, start2 + m2);
        } else {
            return findKth(k - 1 - m2, arr1, start1, start1 + m1,
                    arr2, start2 + m2 + 1, end2);
        }
    }

    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return 0;
        int m = nums1.length, n = nums2.length;

        if ((n + m) % 2 == 0) {
            return (helper(nums1, nums2, (m + n) / 2, 0, m-1, 0, n-1) +
                    helper(nums1, nums2, (m + n) / 2 - 1, 0, m-1, 0, n-1)) / 2.0;
        } else {
            return helper(nums1, nums2, (m + n) / 2, 0, m-1, 0, n-1);
        }
    }

    // k is 0-based
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
