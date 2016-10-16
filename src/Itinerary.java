/**
 * Created by qzhou on 10/16/16.
 * https://leetcode.com/problems/reconstruct-itinerary/
 * http://www.jiuzhang.com/qa/1756/
 *
 * 欧拉路径： O(M) where M is the number of edges -- Fleury Algorithm (0 或 2 个 奇数顶点）
 * Or use brutal force
 */

import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Collections;

public class Itinerary {
    public List<String> findItinerary(String[][] tickets) {
        HashMap<String, PriorityQueue<String>> map = new HashMap<String, PriorityQueue<String>>();
        List<String> ret = new ArrayList<String>();
        String cur = "JFK";

        // This is the graph data structure!
        for (int i = 0; i < tickets.length; i++) {
            String[] ti = tickets[i];
            if (!map.containsKey(ti[0])) {
                map.put(ti[0], new PriorityQueue<String>());
            }
            map.get(ti[0]).add(ti[1]);
        }

        dfsHelper(cur, map, ret);
        Collections.reverse(ret);
        return ret;
    }

    void dfsHelper(String start, HashMap<String, PriorityQueue<String>> map, List<String> list) {
        while (map.containsKey(start) && !map.get(start).isEmpty()) {
            String next = map.get(start).remove();
            dfsHelper(next, map, list);
        }
        list.add(start);
    }
}
