/**
 * Created by quzhou on 9/5/15.
 * https://leetcode.com/problems/add-binary/
 * Given two binary strings, return their sum (also a binary string).
 * For example,
 * a = "11"
 * b = "1"
 * Return "100".
 *
 * StringBuilder vs StringBuffer(synchronized)
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.HashSet;
import java.util.Arrays;

public class AddBinary {
    public static String add(String a, String b) {
        char[] ca = a.toCharArray();
        char[] cb = b.toCharArray();
        reverse(ca); reverse(cb);
        int len = Math.max(ca.length, cb.length);
        char[] res = new char[len];
        int carry = 0, i;
        for (i = 0; i < len; i++) {
            int temp = getNum(ca, i) + getNum(cb, i) + carry;
            carry = temp / 2;
            res[i] = (char)('0' + temp % 2);
        }
        reverse(res);
        String result = new String(res);
        if (carry > 0) {
            return ('1' + result);
        } else {
            return result;
        }
    }

    public static void reverse(char[] a) {
        int i = 0, j = a.length - 1;
        char temp;
        while (i <= j) {
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
            i++; j--;
        }
    }

    public static int getNum(char[] a, int idx) {
        if (idx < a.length) {
            return a[idx] - '0';
        } else {
            return 0;
        }
    }

    // For testing other problems
    public String countAndSay(int n) {
        String cur = "1", next;
        for (int i = 1; i < n; i++) {
            next = "";
            char digit = 0;
            int count = 0;
            for (int j = 0; j < cur.length(); j++) {
                if (cur.charAt(j) != digit) {
                    if (count != 0) {
                        next += count + "" + digit;
                    }
                    count = 1;
                    digit = cur.charAt(j);
                } else {
                    count++;
                }
            }
            next += count + "" + digit;
            cur = next;
        }
        return cur;
    }

    public boolean canJump(int[] A) {
        if (A == null || A.length == 0) {
            return true;
        }

        int farthest = A[0];
        for (int i = 1; i < A.length; i++) {
            if (i <= farthest && A[i] + i >= farthest) {
                farthest = A[i] + i;
            }
        }

        return (farthest >= A.length - 1);
    }

    // You can assume that you can always reach the last index.
    public int jump(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }

        int start = 0, end = 0, jumps = 0;

        while (end < A.length - 1) {
            jumps++;
            int farthest = end;
            for (int i = start; i <= end; i++) {
                if (A[i] + i > farthest) {
                    farthest = A[i] + i;
                }
            }
            start = end + 1;
            end = farthest;
        }
        return jumps;
    }

    // intersection of two arrays, using binary search method
    public int[] intersection(int[] nums1, int[] nums2) {
        // sort the smaller array

        if (nums1 == null || nums1.length == 0 || nums2 == null ||
                nums2.length == 0) {
            return new int[0];
        }

        int m = nums1.length, n = nums2.length;
        int[] arr1 = null, arr2 = null;
        if (m > n) {
            arr1 = nums1;
            arr2 = nums2;
        } else {
            arr1 = nums2;
            arr2 = nums1;
        }

        Arrays.sort(arr2);

        HashSet<Integer> cont = new HashSet<Integer>();
        for (int i = 0; i < arr1.length; i++) {
            if (found(arr2, arr1[i])) {
                cont.add(arr1[i]);
            }
        }

        int[] ret = new int[cont.size()];
        int idx = 0;
        for (Integer elm : cont) {
            ret[idx++] = elm.intValue();
        }
        return ret;
    }

    private boolean found(int[] arr, int target) {
        int low = 0, high = arr.length - 1, mid;
        while (low <= high) {
            mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return false;
    }

    public void partition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int i = 0, j = nums.length - 1;
        while (i <= j) {
            while (i < nums.length && nums[i] < 1) {
                i++;
            }
            while (j >= 0 && nums[j] >= 1) {
                j--;
            }

            if (i <= j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        }
    }

    // for fun
    public void sortColors2(int[] colors, int k) {
        if (colors == null || colors.length == 0 || k < 1) {
            return;
        }

        int start = 0, end = colors.length - 1;
        for (int i = 1; i <= k; i++) {
            start = partition(colors, i, start, end);
        }
    }

    private int partition(int[] colors, int color, int start, int end) {
        if (start + 1 > end) {
            return start + 1;
        }

        int i = start, j = end, temp;
        while (i <= j) {
            while (i <= end && colors[i] == color) {
                i++;
            }
            while (j >= start && colors[j] > color) {
                j--;
            }
            if (i <= j) {
                temp = colors[i];
                colors[i] = colors[j];
                colors[j] = temp;
                i++;
                j--;
            }
        }
        return i;
    }

    public void sortColors(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

        int pl = 0;
        int pr = a.length - 1;
        int i = 0;
        while (i <= pr) {
            if (a[i] == 0) {
                swap(a, pl, i);
                pl++;
                i++;
            } else if(a[i] == 1) {
                i++;
            } else {
                swap(a, pr, i);
                pr--;
            }
        }
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return (dividend > 0) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        }
        if (dividend == 0) {
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        boolean neg = (dividend > 0 && divisor < 0) ||
                (dividend < 0 && divisor > 0);
        long a = Math.abs((long)dividend);
        long b = Math.abs((long)divisor);

        int result = 0, shift;
        while (a >= b) {
            shift = 0;
            while (a >= b << shift) {
                shift++;
            }
            a -= b << (shift - 1);
            result += 1 << (shift - 1);
        }

        return neg? -result : result;
    }

    public static void main(String[] args) {
        String a = "111";
        String b = "111";
        //int val1 = Integer.parseInt(a, 2);
        //int val2 = Integer.parseInt(b, 2);
        String c = add(a, b);
        //System.out.println(val1);
        //System.out.println(val2);
        System.out.println(c);
        //System.out.println(Integer.parseInt(c, 2));
    }
}
