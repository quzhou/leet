/**
 * Created by qzhou on 10/17/16.
 * https://leetcode.com/problems/course-schedule/
 *
 * http://blog.csdn.net/ljiabin/article/details/45846837 (BFS)
 *
 * Use DFS, or
 * 典型的拓扑排序。原理也很简单，在一个有向图中，每次找到一个没有前驱节点的节点（也就是入度为0的节点），
 * 然后把它指向其他节点的边都去掉，重复这个过程（BFS），直到所有节点已被找到，或者没有符合条件的节点
 * （如果图中有环存在）。回顾一下图的三种表示方式：边表示法（即题目中表示方法），邻接表法，邻接矩阵。
 * 用邻接表存储图比较方便寻找入度为0的节点。
 */
import java.util.ArrayList;
import java.util.HashSet;

public class CourseSchedule {
    // DFS, O(V+E)
    // todo: use iterative method
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // use array of HashSet for graph
        ArrayList<HashSet<Integer>> posts = new ArrayList<HashSet<Integer>>();
        for (int i = 0; i < numCourses; i++) {
            posts.add(new HashSet<Integer>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            posts.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }

        // number of prerequisite courses
        int[] preNums = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            HashSet<Integer> neighbors = posts.get(i);
            for (Integer elem : neighbors) {
                preNums[elem]++;
            }
        }

        return dfs(posts, preNums);
    }

    boolean dfs(ArrayList<HashSet<Integer>> graph, int[] preNums) {
        boolean empty = true;
        int idx = -1;
        for (int i = 0; i < preNums.length; i++) {
            if (preNums[i] == 0) {
                idx = i;
            }
            if (preNums[i] != -1) {
                empty = false;
            }
        }
        if (empty) return true;
        else if (idx == -1) {
            return false;
        }

        preNums[idx] = -1;
        HashSet<Integer> nbs = graph.get(idx);
        for (Integer nb : nbs) {
            preNums[nb]--;
        }

        return dfs(graph, preNums);
    }
}
