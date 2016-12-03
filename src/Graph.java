/**
 * Created by qzhou on 12/5/16.
 *
 * A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand
 * operation which turns the water at position (row, col) into a land. Given a list of positions to
 * operate, count the number of islands after each addLand operation. An island is surrounded by water
 * and is formed by connecting adjacent lands horizontally or vertically. You may assume all four
 * edges of the grid are all surrounded by water.
 */
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

public class Graph {
    // http://www.lintcode.com/en/problem/find-the-connected-component-in-the-undirected-graph/
    public List<List<Integer>> connectedSet(ArrayList<UndirectedGraphNode> nodes) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        HashSet<UndirectedGraphNode> set = new HashSet<UndirectedGraphNode>();

        for (UndirectedGraphNode node : nodes) {
            if (set.contains(node)) {
                continue;
            }

            List<Integer> comp = getConnected(node, set);
            Collections.sort(comp);
            result.add(comp);
        }

        return result;
    }
    private List<Integer> getConnected(UndirectedGraphNode node, HashSet<UndirectedGraphNode> set) {
        List<Integer> ans = new ArrayList<Integer>();

        Queue<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
        q.add(node);
        set.add(node);

        while (!q.isEmpty()) {
            UndirectedGraphNode cur = q.remove();
            ans.add(cur.label);

            for (UndirectedGraphNode n : cur.neighbors) {
                if (!set.contains(n)) {
                    q.add(n);
                    set.add(n);
                }
            }
        }

        return ans;
    }

    // http://www.lintcode.com/en/problem/six-degrees/
    public int sixDegrees(List<UndirectedGraphNode> graph,
                          UndirectedGraphNode s,
                          UndirectedGraphNode t) {
        if (s.label == t.label) {
            return 0;
        }

        Queue<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
        q.add(s);
        HashMap<UndirectedGraphNode, Integer> map = new HashMap<UndirectedGraphNode, Integer>();
        map.put(s, 0);

        while (!q.isEmpty()) {
            UndirectedGraphNode node = q.remove();

            for (UndirectedGraphNode n : node.neighbors) {
                if (!map.containsKey(n)) {
                    if (n.label == t.label) {
                        return map.get(node) + 1;
                    } else {
                        q.add(n);
                        map.put(n, map.get(node) + 1);
                    }
                }
            }
        }

        return -1;
    }

    // http://www.lintcode.com/en/problem/word-ladder/
    public int ladderLength(String start, String end, Set<String> dict) {
        if (start.equals(end)) {
            return 1;
        }

        int dis = 1;
        Queue<String> q = new LinkedList<String>();
        q.add(start);
        HashSet<String> set = new HashSet<String>();
        set.add(start);
        int size = 1;

        while (!q.isEmpty()) {
            String str = q.remove();
            size--;

            for (int i = 0; i < str.length(); i++) {
                for (char letter = 'a'; letter <= 'z'; letter++) {
                    if (letter == str.charAt(i)) {
                        continue;
                    }
                    String str2 = str.substring(0, i) + letter + str.substring(i+1);
                    if (set.contains(str2)) {
                        continue;
                    }

                    if (str2.equals(end)) {
                        return dis + 1;
                    }

                    if (dict.contains(str2)) {
                        q.add(str2);
                        set.add(str2);
                    }
                }
            }

            if (size == 0) {
                size = q.size();
                dis++;
            }
        }

        return 0;
    }
}
