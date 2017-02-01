/**
 * Created by qzhou on 9/29/16.
 *
 * 这道题给了我们一个无向图，让我们来判断其是否为一棵树，我们知道如果是树的话，所有的节点必须是连接的，
 * 也就是说必须是连通图，而且不能有环，所以我们的焦点就变成了验证是否是连通图和是否含有环。
 */
import java.util.List;
import java.util.ArrayList;

public class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}
