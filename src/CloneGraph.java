/**
 * Created by qzhou on 9/29/16.
 * https://leetcode.com/problems/clone-graph/
 *
 * {0,1,2#1,2#2,2}
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashSet;

public class CloneGraph {
    // BFS
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;

        Queue<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();

        q.add(node);
        UndirectedGraphNode head = new UndirectedGraphNode(node.label);
        map.put(node, head);

        while (!q.isEmpty()) {
            UndirectedGraphNode n = q.remove();
            UndirectedGraphNode nn = map.get(n);
            for (UndirectedGraphNode neigh : n.neighbors) {
                if (!map.containsKey(neigh)) {
                    q.add(neigh);
                    UndirectedGraphNode newNeigh = new UndirectedGraphNode(neigh.label);
                    map.put(neigh, newNeigh);

                    nn.neighbors.add(newNeigh);
                } else {
                    nn.neighbors.add(map.get(neigh));
                }
            }
        }

        return head;
    }

    // get unique nodes for the graph
    private ArrayList<UndirectedGraphNode> getNodes(UndirectedGraphNode node) {
        Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        HashSet<UndirectedGraphNode> set = new HashSet<UndirectedGraphNode>();
        queue.add(node);
        set.add(node);

        while (!queue.isEmpty()) {
            UndirectedGraphNode head = queue.remove();
            for (UndirectedGraphNode n : head.neighbors) {
                if (!set.contains(n)) {
                    set.add(n);
                    queue.add(n);
                }
            }
        }
        return new ArrayList<UndirectedGraphNode>(set);
    }

    // DFS
    public UndirectedGraphNode cloneGraphDfs(UndirectedGraphNode node) {
        HashMap<UndirectedGraphNode, UndirectedGraphNode> visited = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        return cloneHelper(node, visited);
    }

    UndirectedGraphNode cloneHelper(UndirectedGraphNode node, HashMap<UndirectedGraphNode, UndirectedGraphNode> vis) {
        if (node == null) return null;

        UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
        vis.put(node, copy);
        for (UndirectedGraphNode nb : node.neighbors) {
            if (vis.containsKey(nb)) {
                copy.neighbors.add(vis.get(nb));
            } else {
                copy.neighbors.add(cloneHelper(nb, vis));
            }
        }
        return copy;
    }
}
