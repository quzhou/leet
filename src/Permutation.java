import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * Created by qzhou on 10/24/16.
 * https://leetcode.com/problems/permutations/
 */
public class Permutation {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums == null) {
            return result;
        }
        if (nums.length == 0) {
            result.add(new ArrayList<Integer>());
            return result;
        }

        List<Integer> list = new ArrayList<Integer>();
        helper(nums, list, result);
        return result;
    }

    private void helper(int[] nums, List<Integer> list,
                        List<List<Integer>> result) {
        if (list.size() == nums.length) {
            result.add(new ArrayList<Integer>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!list.contains(nums[i])) {
                list.add(nums[i]);
                helper(nums, list, result);
                list.remove(list.size() - 1);
            }
        }
    }

    // https://leetcode.com/problems/binary-watch/
    public List<String> readBinaryWatch(int num) {
        List<String> result = new ArrayList<String>();
        if (num == 0) {
            result.add("0:00");
            return result;
        }

        ArrayList<Integer> path = new ArrayList<Integer>();
        helper(num, 0, path, result);
        return result;
    }

    private void helper(int num, int pos, ArrayList<Integer> path, List<String> result) {
        if (path.size() == num) {
            String ans = myGetTime(path);
            if (!ans.isEmpty()) {
                result.add(ans);
            }
            return;
        }

        for (int i = pos; i < 10; i++) {
            path.add(i);
            helper(num, i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }

    private String myGetTime(ArrayList<Integer> path) {
        int sz = path.size();
        String ans = "";
        int hour = 0;
        int minute = 0;

        for (int i = 0; i < sz; i++) {
            int idx = path.get(i).intValue();
            switch (idx) {
                case 0:
                    hour += 8;
                    break;
                case 1:
                    hour += 4;
                    break;
                case 2:
                    hour += 2;
                    break;
                case 3:
                    hour += 1;
                    break;
                case 4:
                    minute += 32;
                    break;
                case 5:
                    minute += 16;
                    break;
                case 6:
                    minute += 8;
                    break;
                case 7:
                    minute += 4;
                    break;
                case 8:
                    minute += 2;
                    break;
                case 9:
                    minute += 1;
                    break;
                default:
                    break;
            }
        }

        if (minute >= 60 || hour >= 12) {
            return ans;
        }

        ans = ans + minute;
        if (ans.length() == 1) {
            ans = "0" + ans;
        }
        ans = hour + ":" + ans;
        return ans;
    }

    // http://www.lintcode.com/en/problem/string-permutation-ii/
    public List<String> stringPermutation2(String str) {
        List<String> result = new ArrayList<String>();
        char[] arr = str.toCharArray();

        if (arr.length == 0) {
            result.add(new String(""));
            return result;
        }

        Arrays.sort(arr);

        String path = "";
        int[] visited = new int[arr.length];
        helper(arr, visited, path, result);

        return result;
    }

    private void helper(char[] arr, int[] visited, String path,
                        List<String> result) {
        if (path.length() == arr.length) {
            result.add(new String(path));
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (visited[i] == 1) {
                continue;
            }

            // error pro!
            if (i != 0 && arr[i] == arr[i-1] && visited[i-1] == 0) {
                continue;
            }

            visited[i] = 1;
            path = path + arr[i];
            helper(arr, visited, path, result);
            visited[i] = 0;
            path = path.substring(0, path.length() - 1);
        }
    }

    // http://www.lintcode.com/en/problem/next-permutation/
    public int[] nextPermutation2(int[] num) {
        int n = num.length;
        boolean found = false;

        for (int len = 2; len <= n; len++) {
            int start = n - len;
            for (int j = n-1; j > start; j--) {
                if (num[start] < num[j]) {
                    swap(num, start, j);
                    reverse(num, start+1, n-1);
                    found = true;
                    break;
                }
            }

            if (found) {
                break;
            }
        }

        if (!found) {
            reverse(num, 0, n-1);
        }
        return num;
    }

    private void swap(int[] num, int i, int j) {
        int temp = num[i];
        num[i] = num[j];
        num[j] = temp;
    }

    private void reverse(int[] num, int start, int end) {
        int i = start, j = end;
        while (i < j) {
            swap(num, i, j);
            i++;
            j--;
        }
    }

    public void nextPermutation(int[] num) {
        // find the last increase index
        int index = -1;
        for (int i = num.length - 2; i >= 0; i--) {
            if (num[i] < num[i + 1]) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            reverse(num, 0, num.length - 1);
            return;
        }

        // find the first bigger one
        int biggerIndex = index + 1;
        for (int i = num.length - 1; i > index; i--) {
            if (num[i] > num[index]) {
                biggerIndex = i;
                break;
            }
        }
        while (biggerIndex - 1> index && num[biggerIndex] == num[biggerIndex -1]) {
            biggerIndex--;
        }

        // swap them to make the permutation bigger
        int temp = num[index];
        num[index] = num[biggerIndex];
        num[biggerIndex] = temp;

        // reverse the last part
        reverse(num, index + 1, num.length - 1);
    }

    // http://www.lintcode.com/en/problem/permutation-sequence/
    public String getPermutation(int n, int k) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }

        int curK = k - 1;
        while (curK > 0) {
            int fac = 1;
            int prod = 1, pre = 1;
            while (curK >= prod) {
                fac++;
                pre = prod;
                prod *= fac;
            }

            int left = n - fac;
            int right = curK / pre + left;
            swap(nums, left, right);

            // sort remaining
            // 1 2 3 4 5 -> 1 5 3 4 2, 3 4 2
            // 1 3 5 7 9 -> 1 7 5 3 9, 5 3 9
            for (int j = right; j > left + 1; j--) {
                swap(nums, j, j-1);
            }

            curK = curK % pre;
        }

        String ans = "";
        for (int i = 0; i < n; i++) {
            ans += nums[i];
        }
        return ans;
    }
}
