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
}
