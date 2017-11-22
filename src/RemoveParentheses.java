/**
 * Created by quzhou on 11/6/17.
 * https://leetcode.com/problems/remove-invalid-parentheses/description/
 */
import java.util.*;

public class RemoveParentheses {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<String>();

        int sz = 1;
        Queue<String> q = new LinkedList<String>();
        Set<String> set = new HashSet<String>();
        q.add(s);
        set.add(s);

        while (!q.isEmpty()) {
            String cur = q.remove();
            sz--;

            if (valid(cur)) {
                res.add(cur);
            } else {
                for (int i = 0; i < cur.length(); i++) {
                    if (cur.charAt(i) == '(' || cur.charAt(i) == ')') {
                        String newStr = cur.substring(0, i) + cur.substring(i + 1, cur.length());
                        if (!set.contains(newStr)) {
                            q.add(newStr);
                            set.add(newStr);
                        }
                    }
                }
            }

            if (sz == 0) {
                sz = q.size();
                if (res.size() > 0) {
                    return res;
                }
            }
        }

        return res;
    }

    public boolean valid(String s) {
        int cnt = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                cnt++;
            } else if (ch == ')') {
                cnt--;
            }

            if (cnt < 0) {
                return false;
            }
        }

        return (cnt == 0);
    }
}
