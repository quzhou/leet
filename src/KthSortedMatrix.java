/**
 * Created by qzhou on 1/6/17.
 * http://www.lintcode.com/en/problem/kth-smallest-number-in-sorted-matrix/
 *
 * Solve it in O(k log n) time where n is the bigger one between row size and column size.
 */
import sun.jvm.hotspot.opto.MachIfNode;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Comparator;

public class KthSortedMatrix {
    public class Pair {
        public int x;
        public int y;
        public int val;

        public Pair(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    /**
     * @param matrix: a matrix of integers
     * @param k: an integer
     * @return: the kth smallest number in the matrix
     */
    public int kthSmallest(int[][] matrix, int k) {
        Comparator<Pair> myComp = new Comparator<Pair>() {
            public int compare(Pair a, Pair b) {
                return (a.val - b.val);
            }
        };
        PriorityQueue<Pair> minHeap = new PriorityQueue<Pair>(k, myComp);

        // the min is in this heap
        int sz = Math.min(matrix.length, k);
        for (int i = 0; i < sz; i++) {
            minHeap.add(new Pair(i, 0, matrix[i][0]));
        }

        for (int i = 0; i < k - 1; i++) {
            Pair curr = minHeap.remove();
            if (curr.y < matrix[0].length - 1) {
                minHeap.add(new Pair(curr.x, curr.y + 1, matrix[curr.x][curr.y + 1]));
            }
        }

        return minHeap.peek().val;
    }
}
