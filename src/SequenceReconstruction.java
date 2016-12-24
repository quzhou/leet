/**
 * Created by qzhou on 12/31/16.
 * https://leetcode.com/problems/sequence-reconstruction/
 *
 * topological sorting
 */
import java.util.HashSet;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

public class SequenceReconstruction {
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        if (seqs.length == 0) {
            return (org.length == 0);
        }

        HashMap<Integer, HashSet<Integer>> map = new HashMap<Integer, HashSet<Integer>>();
        HashMap<Integer, HashSet<Integer>> output = new HashMap<Integer, HashSet<Integer>>();

        for (int i = 0; i < seqs.length; i++) {
            if (seqs[i].length == 0) {
                continue;
            }

            for (int j = 1; j < seqs[i].length; j++) {
                // incoming
                HashSet<Integer> prevs = map.get(seqs[i][j]);
                if (prevs == null) {
                    prevs = new HashSet<Integer>();
                }
                prevs.add(seqs[i][j-1]);
                map.put(seqs[i][j], prevs);

                //outgoing, this is like graph node neighbors
                HashSet<Integer> neighbors = output.get(seqs[i][j-1]);
                if (neighbors == null) {
                    neighbors = new HashSet<Integer>();
                }
                neighbors.add(seqs[i][j]);
                output.put(seqs[i][j-1], neighbors);
            }

            if (!output.containsKey(seqs[i][seqs[i].length - 1])) {
                output.put(seqs[i][seqs[i].length - 1], new HashSet<Integer>());
            }
        }

        // [1]
        // [[1],[2,3],[3,2]], there is loop
        if (org.length != output.size()) {
            return false;
        }

        Queue<Integer> q = new LinkedList<Integer>();
        for (Integer node : output.keySet()) {
            if (!map.containsKey(node)) {
                q.add(node);
            }
        }
        if (q.size() != 1) {
            return false;
        }

        int index = 0;
        while (!q.isEmpty()) {
            if (q.size() != 1) {
                return false;
            }

            Integer cur = q.remove();
            if (org.length <= index || cur != org[index++]) {
                return false;
            }

            for (Integer n : output.get(cur)) {
                map.get(n).remove(cur);
                if (map.get(n).isEmpty()) {
                    q.add(n);
                }
            }
        }

        if (index != org.length) {
            return false;
        }

        return true;
    }
}
