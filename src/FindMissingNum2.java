/**
 * Created by qzhou on 12/24/16.
 * http://www.lintcode.com/en/problem/find-the-missing-number-ii/
 */
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class FindMissingNum2 {
    public int findMissing2(int n, String str) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        dfs(n, str, path, result);

        if (result.size() > 0) {
            List<Integer> ans = result.get(0);
            Collections.sort(ans);

            for (int i = 0; i < n - 1; i++) {
                if (ans.get(i) != i + 1) {
                    return i + 1;
                }
            }
            return n;
        }

        return -1;
    }

    private void dfs(int n, String str, List<Integer> path,
                     List<List<Integer>> result) {
        int len = str.length();
        if (path.size() == n - 1 && len == 0) {
            List<Integer> ans = new ArrayList<Integer>(path);
            result.add(ans);
            return;
        }

        if (len == 0 || str.charAt(0) == '0' || path.size() == n - 1) {
            return;
        }

        for (int i = 1; i <= len; i++) {
            String sub = str.substring(0, i);
            int num = Integer.parseInt(sub);
            if (num > n) {
                break;
            }

            if (path.contains(num)) {
                continue;
            }

            path.add(num);
            dfs(n, str.substring(i), path, result);
            path.remove(path.size() - 1);
        }
    }
}
