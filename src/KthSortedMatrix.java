/**
 * Created by qzhou on 1/6/17.
 * http://www.lintcode.com/en/problem/kth-smallest-number-in-sorted-matrix/
 * Solve it in O(k log n) time where n is the bigger one between row size and column size.
 *
 * http://www.lintcode.com/en/problem/kth-smallest-sum-in-two-sorted-arrays/
 * O(k log min(n, m, k)). where n is the size of A, and m is the size of B.
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

    // second
    private class Point {
        public int x; //A index
        public int y; //B index
        public int val;
        public Point(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    public int kthSmallestSum(int[] A, int[] B, int k) {
        Comparator<Point> myComp = new Comparator<Point>() {
            public int compare(Point a, Point b) {
                return a.val - b.val;
            }
        };
        PriorityQueue<Point> minHeap = new PriorityQueue<Point>(k, myComp);
        minHeap.add(new Point(0, 0, A[0] + B[0]));

        int[] dx = new int[]{0, 1};
        int[] dy = new int[]{1, 0};
        boolean[][] hash = new boolean[A.length][B.length];

        for(int i = 0; i < k - 1; i ++) {
            Point cur = minHeap.poll();
            for(int j = 0; j < 2; j ++){
                int next_x = cur.x + dx[j];
                int next_y = cur.y + dy[j];
                Point next_Pair = new Point(next_x, next_y, 0);
                if (next_x < A.length && next_y < B.length && !hash[next_x][next_y]) {
                    hash[next_x][next_y] = true;
                    next_Pair.val = A[next_x] + B[next_y];
                    minHeap.add(next_Pair);
                }
            }
        }

        return minHeap.peek().val;
    }
}
