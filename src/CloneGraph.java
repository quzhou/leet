/**
 * Created by qzhou on 9/29/16.
 * https://leetcode.com/problems/clone-graph/
 *
 * Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 */
public class CloneGraph {
    // BFS
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        UndirectedGraphNode start = new UndirectedGraphNode(node.label);
//        if (node.neighbors.isEmpty()) return start;
//
//        for (UndirectedGraphNode n : node.neighbors) {
//            UndirectedGraphNode subGraph =
//        }
        return start;
    }
}
