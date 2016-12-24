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
    private class UnionFind {
        private HashMap<Long, Long> id;
        private HashMap<Long, Long> sz;

        public UnionFind() {
            this.id = new HashMap<Long, Long>();
            this.sz = new HashMap<Long, Long>();
        }

        private Long root(Long i) {
            while (i != id.get(i)) {
                id.put(i, id.get(id.get(i)));
                i = id.get(i);
            }
            return i;
        }

        public boolean find(Long i, Long j) {
            return (root(i) == root(j));
        }

        public void unite(Long i, Long j) {
            Long rootI = root(i);
            Long rootJ = root(j);
            if (rootI != rootJ) {
                Long szI = sz.get(rootI);
                Long szJ = sz.get(rootJ);

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

        public void add(Long i) {
            id.put(i, i);
            sz.put(i, (long)1);
        }

        public boolean exist(Long i) {
            return (id.containsKey(i));
        }

        public int compSize() {
            return sz.size();
        }
    }

    private void addIsland(int m, int n, int i, int j, UnionFind uf) {
        long s, k = n * i + j;
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
}
