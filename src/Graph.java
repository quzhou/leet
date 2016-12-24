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
import java.util.Map;
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

    // http://www.lintcode.com/en/problem/word-ladder-ii/
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> result = new ArrayList<List<String>>();
        Map<String, Integer> distance = new HashMap<String, Integer>();

        dict.add(start);
        dict.add(end);

        bfs(start, end, dict, distance);

        List<String> path = new ArrayList<String>();
        path.add(start);
        dfs(start, end, dict, distance, path, result);
        return result;
    }

    private List<String> getNeighbors(String node, Set<String> dict) {
        List<String> result = new ArrayList<String>();

        for (int i = 0; i < node.length(); i++) {
            char ch = node.charAt(i);
            for (char j = 'a'; j <= 'z'; j++) {
                if (j == ch) {
                    continue;
                }

                String trans = node.substring(0, i) + j + node.substring(i + 1);
                if (dict.contains(trans)) {
                    result.add(trans);
                }
            }
        }

        return result;
    }

    // save info while bfs
    private void bfs(String start, String end, Set<String> dict,
                     Map<String, Integer> distance) {
        Queue<String> q = new LinkedList<String>();
        q.add(start);
        distance.put(start, 1);

        while (!q.isEmpty()) {
            String str = q.remove();

            if (str.equals(end)) {
                continue;
            }

            List<String> neighbors = getNeighbors(str, dict);
            for (String next : neighbors) {
                if (distance.containsKey(next)) {
                    continue;
                }

                int dis = distance.get(str);
                distance.put(next, dis + 1);

                q.add(next);
            }
        }
    }

    private void dfs(String start, String end, Set<String> dict,
                     Map<String, Integer> distance,
                     List<String> path, List<List<String>> result) {
        int size = path.size();
        String last = path.get(size - 1);
        if (last.equals(end)) {
            result.add(new ArrayList<String>(path));
            return;
        }

        List<String> neighbors = getNeighbors(last, dict);
        for (String str : neighbors) {
            if (distance.get(str) == distance.get(last) + 1) {
                path.add(str);
                dfs(start, end, dict, distance, path, result);
                path.remove(path.size() - 1);
            }
        }
    }
}
