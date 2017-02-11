/**
 * Created by qzhou on 2/13/17.
 */
import java.util.List;
import java.util.ArrayList;

public class DirectedGraphNode {
    public int label;
    public List<DirectedGraphNode> neighbors;
    public DirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<DirectedGraphNode>();
    }
}
