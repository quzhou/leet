/**
 * Created by qzhou on 12/18/16.
 * https://leetcode.com/problems/number-of-islands-ii/
 *
 * Every position is an new land, if the new land connect two islands a and b, we combine them to form a whole.
 * The answer is then the number of the disjointed sets.
 *
 * O(k log mn), where k is the length of the positions
 */
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class NumIslands {
    public class UnionFind {
        public HashMap<Integer, Integer> id; //value is parent
        public HashMap<Integer, Integer> sz; //key is root

        public UnionFind() {
            this.id = new HashMap<Integer, Integer>();
            this.sz = new HashMap<Integer, Integer>();
        }

        private int root(int i) {
            while (i != id.get(i)) {
                id.put(i, id.get(id.get(i)));
                i = id.get(i);
            }
            return i;
        }

        public boolean find(int i, int j) {
            return root(i) == root(j);
        }

        public void unite(int i, int j) {
            int rootI = root(i);
            int rootJ = root(j);
            if (rootI != rootJ) {
                int szI = sz.get(rootI);
                int szJ = sz.get(rootJ);

                if (szI < szJ) {
                    id.put(rootI, rootJ);
                    sz.put(rootJ, szI + szJ);
                    sz.remove(rootI);
                } else {
                    id.put(rootJ, rootI);
                    sz.put(rootI, szI + szJ);
                    sz.remove(rootJ);
                }
            }
        }

        public void add(int i) {
            id.put(i, i);
            sz.put(i, 1);
        }

        public boolean exist(int i) {
            return (id.containsKey(i));
        }

        public int compSize() {
            return sz.size();
        }
    }

    private void addIsland(int m, int n, int i, int j, UnionFind uf) {
        int s, k = n * i + j;
        uf.add(k);

        if (i - 1 >= 0) {
            s = n * (i - 1) + j;
            if (uf.exist(s)) {
                uf.unite(k, s);
            }
        }

        if (i + 1 < m) {
            s = n * (i + 1) + j;
            if (uf.exist(s)) {
                uf.unite(k, s);
            }
        }

        if (j - 1 >= 0) {
            s = n * i + j - 1;
            if (uf.exist(s)) {
                uf.unite(k, s);
            }
        }

        if (j + 1 < n) {
            s = n * i + j + 1;
            if (uf.exist(s)) {
                uf.unite(k, s);
            }
        }
    }

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        UnionFind uf = new UnionFind();
        List<Integer> result = new ArrayList<Integer>();

        for (int i = 0; i < positions.length; i++) {
            addIsland(m, n, positions[i][0], positions[i][1], uf);
            result.add(uf.compSize());
        }

        return result;
    }

    public int numIslands(char[][] grid) {
        int m = grid.length;
        if (m == 0) {
            return 0;
        }
        int n = grid[0].length;
        if (n == 0) {
            return 0;
        }

        UnionFind uf = new UnionFind();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    int k = i * n + j;
                    uf.add(k);

                    if (i - 1 >= 0 && grid[i-1][j] == '1') {
                        uf.unite(k, k - n);
                    }
                    if (j - 1 >= 0 && grid[i][j-1] == '1') {
                        uf.unite(k, k - 1);
                    }
                }
            }
        }

        return uf.sz.size();
    }
}
